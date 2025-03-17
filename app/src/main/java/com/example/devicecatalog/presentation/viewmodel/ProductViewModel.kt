package com.example.devicecatalog.presentation.viewmodel

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
class ProductViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _productState: MutableStateFlow<ApiState<ArrayList<ProductModel>>> =
        MutableStateFlow(ApiState.Loading)
    val productState = _productState.asStateFlow()

    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            _productState.value = repository.getProduct()
        }
    }
}