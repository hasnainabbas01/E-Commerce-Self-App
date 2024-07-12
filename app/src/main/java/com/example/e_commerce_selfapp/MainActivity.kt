package com.example.e_commerce_selfapp

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce_selfapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = ProductCardListAdapter()

    private val viewModel : ProductListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewProductList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewProductList.adapter = adapter

        viewModel.viewState.observe(this){viewState ->
            updateUI(viewState)
        }
        viewModel.loadProductList()

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
            }
            ProductListViewState.Loading -> {
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible  = false
                binding.loadingView.isVisible = true
            }
        }

    }
}