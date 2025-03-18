package com.example.devicecatalog.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicecatalog.model.ProductModel
import com.example.devicecatalog.repository.Repository
import com.example.devicecatalog.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id")

    private val _productDetailsState: MutableStateFlow<ApiState<ProductModel>> =
        MutableStateFlow(ApiState.Loading)
    val productDetailsState = _productDetailsState.asStateFlow()

    init {
        getProductDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("ProductDetailViewModel", "Id:${id ?: 1}")
            _productDetailsState.value = repository.getProductDetails(id ?: 1)
        }
    }
}