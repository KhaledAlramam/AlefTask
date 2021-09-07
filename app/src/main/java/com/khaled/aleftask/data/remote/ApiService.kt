package com.khaled.aleftask.data.remote

import com.khaled.aleftask.data.model.ProductsResponse
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val BASE_URL = "http://productivefamiliesservices.alefsoftware.com/"
    }

    @GET("/client/products?page=1")
    suspend fun getFirstPageProducts(): ProductsResponse

}