package com.example.e_commerce_selfapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce_selfapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ProductCardListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewProductList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewProductList.adapter = adapter
        updateUI(ProductListViewState.Content((1..3).map {
            ProductCardViewState("Playstation $it","This is a nice console! Check it out","200 US$")
        }))
//        updateUI(ProductListViewState.Error("Currently showing error"))
    }

    private fun updateUI(viewState: ProductListViewState) {
        when (viewState) {
            is ProductListViewState.Content -> {
                binding.loadingView.isVisible = false
                binding.errorView.isVisible = false
                adapter.setData(viewState.productList)
            }

            is ProductListViewState.Error -> {
                binding.loadingView.isVisible = false
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = false
                binding.errorText.isVisible = true
                binding.errorText.text = viewState.errorMsg
            }
            ProductListViewState.Loading -> {
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible  = false
                binding.loadingView.isVisible = true
            }
        }

    }
}