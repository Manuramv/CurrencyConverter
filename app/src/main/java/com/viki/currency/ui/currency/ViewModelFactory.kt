package com.viki.currency.ui.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viki.currency.repository.CurrencyRepository

class ViewModelFactory (private val currencyRepository: CurrencyRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyConvertViewModel(currencyRepository) as T
    }

}