package com.khaled.aleftask.data.model

data class ProductsResponse(
    val data: PageData,
    val error: String?,
    val isSuccessed: Boolean
)

object ProductsMapper{
    fun getProductsFromResponse(response: ProductsResponse) =
        response.data.data
}