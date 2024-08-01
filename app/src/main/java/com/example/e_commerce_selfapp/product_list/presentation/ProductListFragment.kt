package com.example.e_commerce_selfapp.product_list.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce_selfapp.ProductListFragmentDirections
import com.example.e_commerce_selfapp.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {
   private lateinit var binding : FragmentProductListBinding;
    private val viewModel= viewModels<ProductListViewModel>()
    private var adapter = ProductCardListAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentProductListBinding.inflate(layoutInflater)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewProductList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.viewProductList.adapter = adapter
        viewModel.value.viewState.observe(viewLifecycleOwner) { viewState ->
            updateUI(viewState)
        }
        viewModel.value.loadProductList()
    }
    private fun updateUI(viewState: ProductListViewState) {
        when (viewState) {
            is ProductListViewState.Content -> {
                binding.viewProductList.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                adapter.setData(viewState.productList)
            }
            ProductListViewState.Error -> {
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
            }
            ProductListViewState.Loading -> {
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }
    private fun onItemClicked(viewState: ProductCardViewState){
        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment())
    }

}