package com.example.e_commerce_selfapp

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Content(val productList : List<ProductCardViewState>) : ProductListViewState()
    data class Error(val errorMsg : String) : ProductListViewState()

}