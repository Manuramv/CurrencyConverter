package com.viki.currency.repository

import com.viki.currency.repository.api.ApiInterface

object RepositoryFactory {
    fun createCurrecncyRepository() : CurrencyRepository {
        return CurrencyRepository(RetrofitApiClient.instance.buildService(ApiInterface::class.java))
    }
}