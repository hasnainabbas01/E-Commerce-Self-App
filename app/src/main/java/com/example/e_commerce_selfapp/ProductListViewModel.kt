package com.example.e_commerce_selfapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductListViewModel : ViewModel() {

    private val _viewState = MutableLiveData<ProductListViewState>()
    val viewState : LiveData<ProductListViewState> get() = _viewState

    fun loadProductList(){
        _viewState.postValue(ProductListViewState.Loading)
        // do something for API call
        _viewState.postValue(ProductListViewState.Content((1..3).map {
            ProductCardViewState("Playstation $it","This is a nice console! Check it out","200 US$")
        }))
    }
}