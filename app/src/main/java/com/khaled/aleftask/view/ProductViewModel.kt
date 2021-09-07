package com.khaled.aleftask.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.khaled.aleftask.data.ProductRepository
import com.khaled.aleftask.data.model.ProductsMapper
import com.khaled.aleftask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){

    fun getFirstPageProducts() = liveData{
        emit(Resource.Loading)
        try {
            val response = productRepository.getFirstPageProducts()
            emit(Resource.Success(ProductsMapper.getProductsFromResponse(response)))
        }catch (exception: Exception){
            emit(Resource.Error("Error happened ${exception.localizedMessage}"))
        }
    }
}