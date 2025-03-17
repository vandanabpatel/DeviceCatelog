package com.example.devicecatalog.api

import com.example.devicecatalog.model.ProductModel
import retrofit2.http.GET

interface ApiService {
    @GET("objects")
    suspend fun getProduct(): ArrayList<ProductModel>

    @GET("objects/1")
    suspend fun getProductDetails(): ProductModel
}