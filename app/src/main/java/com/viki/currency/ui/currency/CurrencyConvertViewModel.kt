package com.viki.currency.ui.currency

import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.viki.currency.repository.CurrencyRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit


val TAG = CurrencyConvertViewModel::class.java.canonicalName
class CurrencyConvertViewModel(val currencyRepository:CurrencyRepository) :ViewModel() {
    var fromCurrencyEdtLiveData = MutableLiveData<Double>()
    var toCurrencyEdtLiveData = MutableLiveData<Double>()
    private var curDataFromApi : MutableList<CurrencyData>

    var curFromSpnrData  : MutableLiveData<MutableList<CurrencyData>>
    var curToSpnrData  :MutableLiveData<MutableList<CurrencyData>>

    val spinnerFromSelectedPosition : MutableLiveData<Int> // This gets updated once spinner item selection changes
    val spinnerToSelectedPosition : MutableLiveData<Int> // This gets updated once spinner item selection changes
    var progress = MutableLiveData<Int>()



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
        progress.value = 0
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
            val rateObj = rates.getAsJsonObject().get("rates").asJsonObject
            curDataFromApi?.clear()
            rateObj.keySet().forEach({
                Log.d("TAG","key =====" + it+"...value="+rateObj.get(it))
                curDataFromApi?.add(CurrencyData(it,rateObj.get(it)))
            })
            curFromSpnrData.value = curDataFromApi
            curToSpnrData.value = curDataFromApi

            //this section we can imporve a bit. currently I'm resetting the spinner livedata everytime without comparing
            //if the update is needed or not.SO I'm just assigning previously selected value again to spinners
            spinnerFromSelectedPosition.value = spinnerFromSelectedPosition.value
            spinnerToSelectedPosition.value = spinnerToSelectedPosition.value
        } catch (e:Exception){
            _errorLiveData.value = e.toString()
            Log.d("TAG","Exception while parsing api response" + e)
        }

    }






    //Calculate the curency value - whenever the changes is happening on either of the dropdown or
    // From edit text field
    fun calCurrencyValue() {
        val fromSpinnerSelectedItem = curDataFromApi.get(spinnerFromSelectedPosition.value ?:0).value
        val toSpinnerSelectedItem = curDataFromApi.get(spinnerToSelectedPosition.value ?: 0).value

        //special case- forcefully setting to the to field as "NAN" to make sure that when the from filed is empty it is
        //clearing the value in to field as well
        if(fromCurrencyEdtLiveData?.value == null) {
            toCurrencyEdtLiveData.postValue(Double.NaN)
        }

            var currencyDiff = toSpinnerSelectedItem.toString().toDouble().div(fromSpinnerSelectedItem.toString().toDouble())
            currencyDiff = roundOffDecimal(currencyDiff)
            val finalSum = fromCurrencyEdtLiveData.value?.times(currencyDiff)
            //update the value - To field
            toCurrencyEdtLiveData.value = finalSum

            //update the currency unit price field to notice the user based on the dropdown selection
            displayConversionRate.value = "1 " +  curDataFromApi.get(spinnerFromSelectedPosition.value ?:0).name +" equals "+
                    currencyDiff +" " +curDataFromApi.get(spinnerToSelectedPosition.value ?: 0).name


    }


    //method to round off the currency value
    private fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }

    //clearing the fields when view model is destroyed.
    override fun onCleared() {
        super.onCleared()
    }
}