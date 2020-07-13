package com.viki.currency.ui.currency

import com.viki.currency.models.Rates

data class CurrencyData (
    var name:String,
    var value:Any
)

 class Entry(val currencyData1: CurrencyData){
     var currencyData = currencyData1

 }

