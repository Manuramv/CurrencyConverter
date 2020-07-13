package com.viki.currency.ui.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viki.currency.repository.CurrencyRepository

//Since we are not using Dagger or anything - I'm passing the repository instance via Viewmodel factory.
class ViewModelFactory (private val currencyRepository: CurrencyRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyConvertViewModel(currencyRepository) as T
    }

}