package com.example.e_commerce_selfapp.shared.data.repository.api

import com.example.e_commerce_selfapp.product_list.data.ProductEntity
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProductList():List<ProductEntity>

}