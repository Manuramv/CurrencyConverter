package com.viki.currency.ui.currency

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.viki.currency.R
import com.viki.currency.databinding.ActivityCurrencyConvertBinding
import com.viki.currency.data.repository.RepositoryFactory
import com.viki.currency.utils.VikiSnackBar


class CurrencyConvertActivity : AppCompatActivity() {
    private lateinit var currencyConvertViewModel : CurrencyConvertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCurrencyConvertBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_currency_convert)

        currencyConvertViewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepositoryFactory.createCurrecncyRepository())
        ).get(CurrencyConvertViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewmodel = currencyConvertViewModel

        //Trigger the API call -- viewmodel will take care of triggering the api call in every 10s
        currencyConvertViewModel.callMeInEvery10s()

        //observe for the from text changes and calculate the currency value
        currencyConvertViewModel.fromCurrencyEdtLiveData.observe(this, Observer { it ->
            currencyConvertViewModel.calCurrencyValue()
        })

        //observe for the from spinner and calculate the currency value
        currencyConvertViewModel.spinnerFromSelectedPosition.observe(this, Observer { it ->
            currencyConvertViewModel.calCurrencyValue()
        })

        //observe for the To spinner and calculate the currency value
        currencyConvertViewModel.spinnerToSelectedPosition.observe(this, Observer { it ->
            currencyConvertViewModel.calCurrencyValue()
        })

        //observe if there is any error such as network or json parsing show to the customer
        currencyConvertViewModel.errorLiveData.observe(this, Observer { it ->
            VikiSnackBar.showErrorMsg(binding.parentLayout,it)
        })

        //once if the data is loaded successfully show the currency converter section to the user
        currencyConvertViewModel.isLoading.observe(this, Observer { it ->
            if(!it){
                binding.progressBar.visibility = View.INVISIBLE
                binding.convertLayout.visibility = View.VISIBLE
            }
        })

    }
}



