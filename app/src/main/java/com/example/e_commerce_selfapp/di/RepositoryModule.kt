package com.example.e_commerce_selfapp.di

import com.example.e_commerce_selfapp.shared.data.repository.api.ApiClient
import com.example.e_commerce_selfapp.shared.data.repository.ProductRepository
import com.example.e_commerce_selfapp.shared.data.repository.api.ProductRepositoryAPI
import com.example.e_commerce_selfapp.shared.data.repository.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesProductRepository(productRepositoryAPI: ProductRepositoryAPI) : ProductRepository = productRepositoryAPI

    @Provides
    fun providesProductRepositoryAPI(service: ProductService) : ProductRepositoryAPI = ProductRepositoryAPI(service)

    @Provides
    fun providesProductService() : ProductService = ApiClient.getService()
}