package com.viki.currency.adapters

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.viki.currency.R



    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(generateImgUrl(url))
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.img_loader)
            .into(view);
    }

    //generating the imageurl to fetch the image from github
    fun generateImgUrl(imgCountryCode: String?): String {
        val imgBaseUrl = "https://github.com/transferwise/currency-flags/blob/master/src/flags/"
        return imgBaseUrl + imgCountryCode?.toLowerCase() + ".png" + "?raw=true"

    }

    @BindingAdapter("text")
    fun setText(view: AppCompatTextView, name: String?) {
        view.setText(name)
    }


