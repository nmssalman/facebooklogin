package com.nmssalman.elegantmedia.APIRepository

import android.content.Context
import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class PrimaryAPICaller (url: String, callback: okhttp3.Callback) {
    private val okHttpClient: OkHttpClient
    private val request: Request
    val mediaType = "application/json; charset=utf-8".toMediaType()
    init {
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .callTimeout(5, TimeUnit.MINUTES)
            .build()

            request = Request.Builder()
                .addHeader(    "X-Requested-With", "XMLHttpRequest")
                .url(url)
                .build()

        okHttpClient.newCall(request).enqueue(callback)
    }

}
