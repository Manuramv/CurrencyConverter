package com.viki.currency.ui.currency

import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import com.viki.currency.repository.CurrencyRepository
import com.viki.currency.repository.data.CurrencyApiResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit




class CurrencyConvertViewModel(val currencyRepository:CurrencyRepository) :ViewModel() {
    val TAG = CurrencyConvertViewModel::class.java.canonicalName
    var fromCurrencyEdtLiveData = MutableLiveData<Double>()
    var toCurrencyEdtLiveData = MutableLiveData<Double>()
    private var curDataFromApi : MutableList<CurrencyData>

    var curFromSpnrData  : MutableLiveData<MutableList<CurrencyData>>
    var curToSpnrData  :MutableLiveData<MutableList<CurrencyData>>


    var spnrFromCurSelectedData: MutableLiveData<CurrencyData> = MutableLiveData()
    var spnrToCurSelectedData: MutableLiveData<CurrencyData> = MutableLiveData()

    val spinnerFromSelectedPosition : MutableLiveData<Int> // This gets updated once spinner item selection changes
    val spinnerToSelectedPosition : MutableLiveData<Int> // This gets updated once spinner item selection changes



    private var _errorLiveData= MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    //display the conversionrate
    var displayConversionRate  = MutableLiveData<String>()



    init {
        curDataFromApi = mutableListOf()
        curFromSpnrData = MutableLiveData<MutableList<CurrencyData>>()
        curToSpnrData  = MutableLiveData<MutableList<CurrencyData>> ()
        spinnerFromSelectedPosition = MutableLiveData<Int>()
        spinnerToSelectedPosition = MutableLiveData<Int>()
        //displayConversionRate = MutableLiveData<String>()



    }

    //spinner related items:::;
    //setting the API data to Spinner

    //mutable livedata to get the spinner selected position



//call this API and set the data to dropdown
    fun callMeInEvery10s(){
        Observable.interval(0,10000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe({
                fetchApi()
                Log.d(TAG,"current thread::"+ Looper.getMainLooper().isCurrentThread())

            });
    }

    fun fetchApi()  {
        currencyRepository.getLatestCurrencyRate().subscribe({
            parseJson(it)
        },{
            _errorLiveData.value = it.toString()
        })
    }


///parsing the json and addding the elements to mutablelivedata
    fun parseJson(rates: JsonElement) {
        try{
            val gson = Gson()
            val jsonObject: JsonObject = rates.getAsJsonObject()
            val rateObj = jsonObject.get("rates").asJsonObject
            curDataFromApi?.clear()
            rateObj.keySet().forEach({
                Log.d("TAG","key =====" + it+"...value="+rateObj.get(it))
                curDataFromApi?.add(CurrencyData(it,rateObj.get(it)))
            })

            for ( key in rateObj.keySet()) {
               // curDataFromApi?.add(CurrencyData(key,rateObj.get(key)))
            }
            curFromSpnrData.value = curDataFromApi
            curToSpnrData.value = curDataFromApi

            spinnerFromSelectedPosition.value = spinnerFromSelectedPosition.value
            spinnerToSelectedPosition.value = spinnerToSelectedPosition.value


        } catch (e:Exception){
            Log.d("TAG","Exception in json==" + e)
        }

    }






    fun testCurrencyCalculate() {

        val fromSpinnerSelectedItem = curDataFromApi.get(spinnerFromSelectedPosition.value ?:0).value
        val toSpinnerSelectedItem = curDataFromApi.get(spinnerToSelectedPosition.value ?: 0).value
        if(fromCurrencyEdtLiveData?.value == null) {
            toCurrencyEdtLiveData.postValue(Double.NaN)
            Log.d(TAG,"setting tovalue to null::")
        }
        Log.d(TAG,"setting tovalue fromCurrencyEdtLiveData to null::"+fromCurrencyEdtLiveData?.value)


        val fromFieldValue = fromCurrencyEdtLiveData.value

        fromCurrencyEdtLiveData?.value?.let {
            Log.d(TAG,"Onselcting from dropdown..fromspnr="+fromSpinnerSelectedItem+"..tospnr== "+toSpinnerSelectedItem+"..fromEdittextvalue=="+fromFieldValue+"..toCurrencyLiveData="+toCurrencyEdtLiveData.value)

            val  calculateSum = (fromCurrencyEdtLiveData.value?.times(fromSpinnerSelectedItem.toString().toDouble()))?.plus(toSpinnerSelectedItem.toString().toDouble())

            val currencyDiff = toSpinnerSelectedItem.toString().toDouble().div(fromSpinnerSelectedItem.toString().toDouble())

            val finalSum = fromFieldValue?.times(currencyDiff)

            toCurrencyEdtLiveData.value = finalSum
        }



        displayConversionRate.value = "1 " +  curDataFromApi.get(spinnerFromSelectedPosition.value ?:0).name +" equals "+
                curDataFromApi.get(spinnerToSelectedPosition.value ?: 0).value +" " +curDataFromApi.get(spinnerToSelectedPosition.value ?: 0).name


    }


    fun displayRate(){

    }



    override fun onCleared() {
        super.onCleared()
    }
}