package com.example.devicecatalog.repository

import com.example.devicecatalog.api.ApiService
import com.example.devicecatalog.model.ProductModel
import com.example.devicecatalog.utils.ApiState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getProduct(): ApiState<ArrayList<ProductModel>> = try {
        ApiState.Success(apiService.getProduct())
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }

    override suspend fun getProductDetails(): ApiState<ProductModel> = try {
        ApiState.Success(apiService.getProductDetails())
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }
}