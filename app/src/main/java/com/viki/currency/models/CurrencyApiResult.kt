package com.viki.currency.models

import com.google.gson.JsonElement

sealed class CurrencyApiResult {
    data class Success(val data: JsonElement) : CurrencyApiResult()
    data class Error(val error: Exception) : CurrencyApiResult()
}