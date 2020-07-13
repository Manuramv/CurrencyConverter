package com.viki.currency.repository

import com.viki.currency.data.api.ApiInterface
import com.viki.currency.data.api.RetrofitApiClient

object RepositoryFactory {
    fun createCurrecncyRepository() : CurrencyRepository {
        return CurrencyRepository(RetrofitApiClient.instance.buildService(ApiInterface::class.java))
    }
}