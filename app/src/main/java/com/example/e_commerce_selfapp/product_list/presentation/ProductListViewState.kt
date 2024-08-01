package com.example.e_commerce_selfapp.product_list.presentation

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Content(val productList : List<ProductCardViewState>) : ProductListViewState()
    object Error : ProductListViewState()

}