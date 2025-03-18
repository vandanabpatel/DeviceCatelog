package com.example.devicecatalog.model

data class ProductModel(
    val id: String, val name: String, val data: ProductData?
)

data class ProductData(val color: String?, val capacity: String?)