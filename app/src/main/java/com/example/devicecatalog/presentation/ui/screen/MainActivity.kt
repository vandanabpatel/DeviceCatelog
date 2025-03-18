package com.example.devicecatalog.presentation.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.devicecatalog.presentation.ui.theme.DeviceCatalogTheme
import com.example.devicecatalog.presentation.viewmodel.ProductDetailViewModel
import com.example.devicecatalog.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviceCatalogTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val productViewModel = hiltViewModel<ProductViewModel>()

    NavHost(navController = navController, startDestination = "product") {
        composable(route = "product") {
            ProductScreen(navController, productViewModel.productState.collectAsState().value)
        }
        composable(
            route = "productDetail/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val productDetailViewModel = hiltViewModel<ProductDetailViewModel>()
            ProductDetailsScreen(
                navController,
                productDetailViewModel.productDetailsState.collectAsState().value
            )
        }
    }
}