package com.example.e_commerce_selfapp

import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProductList():List<ProductEntity>

}