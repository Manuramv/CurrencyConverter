package com.viki.currency.models

data class CurrencyRateResponse(
    val base: String,
    val date: String,
    val rates: Rates
)