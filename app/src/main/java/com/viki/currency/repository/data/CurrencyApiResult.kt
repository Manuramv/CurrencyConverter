package com.viki.currency.repository.data

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Flowable

sealed class CurrencyApiResult {
    data class Success(val data: JsonElement) : CurrencyApiResult()
    data class Error(val error: Exception) : CurrencyApiResult()
}