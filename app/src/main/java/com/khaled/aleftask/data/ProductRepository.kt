package com.khaled.aleftask.data

import com.khaled.aleftask.data.remote.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getFirstPageProducts()=
        apiService.getFirstPageProducts()

}