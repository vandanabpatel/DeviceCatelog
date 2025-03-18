package com.example.devicecatalog.repository

import com.example.devicecatalog.api.ApiService
import com.example.devicecatalog.model.ErrorModel
import com.example.devicecatalog.model.ProductModel
import com.example.devicecatalog.utils.ApiState
import com.example.devicecatalog.utils.Utils.jsonToPojo
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getProduct(): ApiState<ArrayList<ProductModel>> = try {
        ApiState.Success(apiService.getProduct())
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        if (errorBody.isNullOrEmpty()) {
            ApiState.Error(errorMsg = e.message.toString())
        } else {
            val errorResponse = jsonToPojo(errorBody, ErrorModel::class.java) as ErrorModel
            ApiState.Error(errorMsg = errorResponse.error)
        }
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }

    override suspend fun getProductDetails(id: Int): ApiState<ProductModel> = try {
        ApiState.Success(apiService.getProductDetails(id))
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        if (errorBody.isNullOrEmpty()) {
            ApiState.Error(errorMsg = e.message.toString())
        } else {
            val errorResponse = jsonToPojo(errorBody, ErrorModel::class.java) as ErrorModel
            ApiState.Error(errorMsg = errorResponse.error)
        }
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }
}