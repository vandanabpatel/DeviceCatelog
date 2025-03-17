package com.example.devicecatalog.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.devicecatalog.model.ProductModel
import com.example.devicecatalog.utils.ApiState

@Composable
fun ProductScreen(productList: ApiState<ArrayList<ProductModel>>) {
    when (productList) {
        is ApiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }
        }

        is ApiState.Success -> {
            val data = productList.data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Product Details",
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "Brand:\n${data[0].name}",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        is ApiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = productList.errorMsg,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}