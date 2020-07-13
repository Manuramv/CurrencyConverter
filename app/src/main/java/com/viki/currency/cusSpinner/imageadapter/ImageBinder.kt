package com.viki.currency.cusSpinner.imageadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.viki.currency.R
import kotlinx.android.synthetic.main.country_custom_spinner.view.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(generateImgUrl(url))
        .error(R.drawable.ic_error)
        .placeholder(R.drawable.img_loader)
        .into(view);
}

fun generateImgUrl(imgCountryCode:String?):String{
    val imgBaseUrl = "https://github.com/transferwise/currency-flags/blob/master/src/flags/"
    return imgBaseUrl +imgCountryCode?.toLowerCase()+".png"+"?raw=true"

}

@BindingAdapter("text")
fun setText(view: AppCompatTextView, name: String?) {
    view.setText(name+"test")
}

