package com.viki.currency.repository.api

import com.google.gson.JsonElement
import com.viki.currency.models.CurrencyRateResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiInterface {
    @GET("lates")
    fun getLatestRate(): Flowable<JsonElement>
}