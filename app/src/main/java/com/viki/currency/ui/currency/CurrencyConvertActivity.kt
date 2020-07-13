package com.viki.currency.ui.currency

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.viki.currency.R
import com.viki.currency.databinding.ActivityCurrencyConvertBinding
import com.viki.currency.repository.RepositoryFactory
import com.viki.currency.utils.BaseSnackBar


class CurrencyConvertActivity : AppCompatActivity() {
    private lateinit var currencyConvertViewModel : CurrencyConvertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_currency_convert)
        val binding: ActivityCurrencyConvertBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_currency_convert)

        currencyConvertViewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepositoryFactory.createCurrecncyRepository())
        ).get(CurrencyConvertViewModel::class.java)

        /*binding.let {
            it.viewmodel = currencyConvertViewModel
            it.setLifecycleOwner(this)
            // it.spinAdapter = CountrySpinnerAdapter(this)
        }*/
        binding.lifecycleOwner = this

        binding.viewmodel = currencyConvertViewModel
        currencyConvertViewModel.callMeInEvery10s()


        fun setSpinner(){
           // binding.spnrToCurrecncy.adapter
        }

        //binding.viewmodel = currencyConvertViewModel
        /* btnClick.setOnClickListener({
            //ServiceBuilder.retrofitBuilder().getLatestRate()
           currencyConvertViewModel.fetchApi()
        })*/


        currencyConvertViewModel.fromCurrencyEdtLiveData.observe(this, Observer { it ->
            Log.d("tag","fromCurrencyLiveData value::"+it)
            //currencyConvertViewModel.calulateToCurrency()
            currencyConvertViewModel.calCurrencyValue()
        })
        currencyConvertViewModel.toCurrencyEdtLiveData.observe(this, Observer { it ->
            Log.d("tag","toCurrencyLiveData value::"+it)
           // currencyConvertViewModel.calulateFromCurrency()
            //currencyConvertViewModel.testCurrencyCalculate()
        })


        currencyConvertViewModel.spinnerFromSelectedPosition.observe(this, Observer { it ->
            Log.d("tag","selectedSpinner FROM selected position*********t::"+it)
            currencyConvertViewModel.calCurrencyValue()
        })

        currencyConvertViewModel.spinnerToSelectedPosition.observe(this, Observer { it ->
            Log.d("tag","selectedSpinner TO selected position*********t::"+it)
            currencyConvertViewModel.calCurrencyValue()
        })

        currencyConvertViewModel.errorLiveData.observe(this, Observer { it ->
            BaseSnackBar.showErrorMsg(binding.parentLayout,it)
        })








    }
}



