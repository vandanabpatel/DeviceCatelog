package com.example.devicecatalog.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson

object Utils {
    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    fun jsonToPojo(jsonString: String?, pojoClass: Class<*>?): Any? {
        return if (jsonString.isNullOrEmpty() || jsonString.isBlank()) {
            null
        } else {
            return Gson().fromJson(jsonString, pojoClass)
        }
    }
}