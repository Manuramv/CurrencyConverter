package com.viki.currency.cusSpinner

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.viki.currency.R
import com.viki.currency.cusSpinner.imageadapter.generateImgUrl
import com.viki.currency.databinding.CountryCustomSpinnerBinding
import com.viki.currency.ui.currency.CurrencyData
import kotlinx.android.synthetic.main.country_custom_spinner.view.*


class CurrencySpinnerAdapter(context: Context, resource: Int, private val values: List<CurrencyData>) :
    ArrayAdapter<CurrencyData>(context, resource, values) {

    override fun getCount() = values.size
    override fun getItem(position: Int) = values[position]



    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
         val binding = DataBindingUtil.inflate<CountryCustomSpinnerBinding>(
            LayoutInflater.from(context),
            R.layout.country_custom_spinner,
            parent, false
        ).also {
            it.root.tag = it
        }
        binding.adapterdata = values[position]
        return binding.root
    }
    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<CountryCustomSpinnerBinding>(
            LayoutInflater.from(context),
            R.layout.country_custom_spinner,
            parent, false
        ).also {
            it.root.tag = it
        }

        binding.adapterdata = values[position]
        return binding.root
    }
}