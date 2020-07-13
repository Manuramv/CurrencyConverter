package com.viki.currency.data.api

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiInterface {
    @GET("latest")
    fun getLatestRate(): Flowable<JsonElement>
}