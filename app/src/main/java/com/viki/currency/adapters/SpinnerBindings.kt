package com.viki.currency.adapters

import android.widget.Spinner
import androidx.databinding.BindingAdapter

import com.viki.currency.adapters.SpinnerExtensions.setSpinnerEntries
import com.viki.currency.adapters.SpinnerExtensions.setSpinnerItemSelectedListener
import com.viki.currency.adapters.SpinnerExtensions.setSpinnerValue
import com.viki.currency.models.CurrencyData


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