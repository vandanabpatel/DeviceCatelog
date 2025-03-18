package com.example.devicecatalog.api

import com.example.devicecatalog.model.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("objects")
    suspend fun getProduct(): ArrayList<ProductModel>

    @GET("objects/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): ProductModel
}