package com.example.e_commerce_selfapp.shared.data.repository.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class ApiClient {
    companion object {
        fun getService(): ProductService {
            return Retrofit.Builder()
                .baseUrl("https://us-central1-android-course-api.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build()
                .create(ProductService::class.java)
        }

        fun getUnsafeOkHttpClient(): OkHttpClient? {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts =
                    arrayOf<TrustManager>(
                        object : X509TrustManager {
                            @Throws(CertificateException::class)
                            override fun checkClientTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                            ) {
                            }

                            @Throws(CertificateException::class)
                            override fun checkServerTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                            ) {
                            }

                            override fun getAcceptedIssuers(): Array<X509Certificate> {
                                return arrayOf()
                            }
                        }
                    )

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory)
                builder.hostnameVerifier { hostname, session -> true }
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}