package com.viki.currency.ui.currency

import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.viki.currency.repository.CurrencyRepository
import com.viki.currency.utils.VikiConstants.Companion.API_REFETCH_INTERVAL
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



    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean>
    get() = _isLoading

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

    }

//call this API and set the data to dropdown
    fun callMeInEvery10s(){
        _isLoading.value = true
        Observable.interval(0,API_REFETCH_INTERVAL, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe({
                fetchApi() //this will call the currency api in every 10s
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
    private fun parseJson(rates: JsonElement) {
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
            _isLoading.value = false
        } catch (e:Exception){
            _errorLiveData.value = e.toString()
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
        return number.toBigDecimal().setScale(3, RoundingMode.FLOOR).toDouble()
    }

    //clearing the fields when view model is destroyed.
    override fun onCleared() {
        super.onCleared()
    }
}