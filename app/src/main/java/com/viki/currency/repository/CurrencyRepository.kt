package com.viki.currency.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.viki.currency.repository.api.ApiInterface
import com.viki.currency.repository.data.CurrencyApiResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class CurrencyRepository(val apiInterface: ApiInterface) {
    private val currencyServerLieData: MutableLiveData<CurrencyApiResult> =
        MutableLiveData<CurrencyApiResult>()

    fun getLatestCurrencyRate() : Observable<JsonElement> {
        return Observable.create { emitter ->
            apiInterface.getLatestRate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                        emitter.onNext(it)
                }, {
                    emitter.onError(it)
                    //it.printStackTrace()
                })

        }
    }
}


/*
return Observable.create { emitter ->

    apiInterface.getLatestRate()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe( {
            if (it.rates != null) {
                emitter.onNext(it)
            }
        }, {
            emitter.onError(it)
            //it.printStackTrace()
        })

}*/
