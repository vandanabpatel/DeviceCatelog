package com.example.devicecatalog.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.devicecatalog.R
import com.example.devicecatalog.model.ProductModel
import com.example.devicecatalog.utils.ApiState

@Composable
fun ProductDetailsScreen(navController: NavController?, product: ApiState<ProductModel>) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.app_name),
                icon = Icons.AutoMirrored.Filled.ArrowBack
            ) {
                navController?.navigateUp()
            }
        }
    ) { contentPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            when (product) {
                is ApiState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ApiState.Success -> {
                    val data = product.data

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(contentPadding),
                    ) {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            ) {
                                Text(
                                    "Name: " + data.name,
                                )
                                data.data?.let { it ->
                                    it.color?.let {
                                        Text(
                                            "Color: $it",
                                        )
                                    }
                                    it.capacity?.let {
                                        Text(
                                            "Capacity: $it",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                is ApiState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                    ) {
                        Text(
                            text = product.errorMsg,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = colorResource(R.color.red)
                        )
                    }
                }
            }
        }
    }
}