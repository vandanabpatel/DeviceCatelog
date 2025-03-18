package com.example.devicecatalog.repository

import com.example.devicecatalog.model.ProductModel
import com.example.devicecatalog.utils.ApiState

interface Repository {
    suspend fun getProduct(): ApiState<ArrayList<ProductModel>>
    suspend fun getProductDetails(id: Int): ApiState<ProductModel>
}