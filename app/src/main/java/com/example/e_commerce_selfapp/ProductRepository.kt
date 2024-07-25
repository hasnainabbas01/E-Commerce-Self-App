package com.example.e_commerce_selfapp

interface ProductRepository {
    suspend fun getProductList() : List<ProductCardViewState>
}