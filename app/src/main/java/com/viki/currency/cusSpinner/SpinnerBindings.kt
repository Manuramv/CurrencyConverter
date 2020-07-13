package com.viki.currency.cusSpinner

import android.widget.Spinner
import androidx.databinding.BindingAdapter

import com.viki.currency.cusSpinner.SpinnerExtensions.setSpinnerEntries
import com.viki.currency.cusSpinner.SpinnerExtensions.setSpinnerItemSelectedListener
import com.viki.currency.cusSpinner.SpinnerExtensions.setSpinnerValue
import com.viki.currency.ui.currency.CurrencyData


object SpinnerBindings {


    @JvmStatic
    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<CurrencyData>?) {
        setSpinnerEntries(entries)
    }

    @JvmStatic
    @BindingAdapter("onItemSelected")
    fun Spinner.setOnItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @JvmStatic
    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: CurrencyData?) {
        setSpinnerValue(newValue)
    }
}