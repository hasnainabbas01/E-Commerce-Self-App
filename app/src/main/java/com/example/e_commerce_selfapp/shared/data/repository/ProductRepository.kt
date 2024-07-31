package com.example.e_commerce_selfapp

import com.example.e_commerce_selfapp.product_list.presentation.ProductCardViewState

interface ProductRepository {
    suspend fun getProductList() : List<ProductCardViewState>
}