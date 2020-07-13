package com.viki.currency.cusSpinner


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.InverseBindingListener
import com.viki.currency.R
import com.viki.currency.ui.currency.CurrencyData

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 * @author chetansachdeva on 10/04/18
 */
object SpinnerExtensions {

    /**
     * set spinner entries
     */
    @JvmStatic
    fun Spinner.setSpinnerEntries(entries: List<CurrencyData>?) {
        if (entries != null) {
            val arrayAdapter = CurrencySpinnerAdapter(context, R.layout.country_custom_spinner, entries)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = arrayAdapter
        }
    }

    /**
     * set spinner onItemSelectedListener listener
     */
    @JvmStatic
    fun Spinner.setSpinnerItemSelectedListener(listener: ItemSelectedListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onItemSelected(parent.getItemAtPosition(position) as CurrencyData)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    /**
     * set spinner onItemSelectedListener listener
     */
    @JvmStatic
    fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    /**
     * set spinner value
     */
    fun Spinner.setSpinnerValue(value: CurrencyData?) {
        if (adapter != null ) {
            val position = (adapter as ArrayAdapter<CurrencyData>).getPosition(value)
            setSelection(position, false)
            tag = position
        }
    }

    /**
     * get spinner value
     */
    fun Spinner.getSpinnerValue(): CurrencyData? {
        return selectedItem as CurrencyData
    }

    interface ItemSelectedListener {
        fun onItemSelected(item: CurrencyData)
    }
}