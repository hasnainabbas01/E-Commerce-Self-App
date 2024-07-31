package com.example.e_commerce_selfapp

import com.example.e_commerce_selfapp.product_list.presentation.ProductCardViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryAPI @Inject constructor(private val service: ProductService) : ProductRepository {
    override suspend fun getProductList() : List<ProductCardViewState>{
           return withContext(Dispatchers.IO){
               delay(2000)
               (1..3).map {
                   ProductCardViewState("Playstation $it","This is a nice console! Check it out","200 US$",
                       "https://firebasestorage.googleapis.com/v0/b/androidecommercesample.appspot.com/o/playstation_1.png?alt=media&token=1414f40e-23cf-4f44-b922-e12bfcfca9f3")
               }
                /*service.getProductList().map {
                    ProductCardViewState(
                        it.title,
                        it.description,
                        "US $ ${it.price}",
                        it.imageUrl
                    )
                }*/
           }

    }
}