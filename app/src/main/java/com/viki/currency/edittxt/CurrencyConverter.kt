package com.viki.currency.edittxt

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod


object Converter {
    @InverseMethod("stringToDate")
    @JvmStatic fun dateToString(
        value: Any?
    ): String? {
        // Converts long to String.

        //return value.toString()

        return try {
            value.toString()
        } catch (e: Exception) {
            ""
        }
    }

    @JvmStatic fun stringToDate(
        value: Any?
    ): Any? {
        // Converts String to long.
       // if(TextUtils.isEmpty(value.toString())) return 0 as Double
        //return   value as Double//.toString().toLong()

        try{
            value?.let {
                return value
                /*if(value is Double)
                    return value.toString().toDouble()
                else if (value is Int)
                    return  value.toString().toInt()
                else
                    return value.toString()*/
            }
            return 1
        } catch (e:Exception){
          return  ""
        }
        return ""
    }


  /*  @BindingAdapter("android:text")
    fun setText(view: TextView, value: Double?) {
        if (value == null) return
        view.text = value.toString()
    }

    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    fun getTextString(view: TextView): Float? {
        return java.lang.Float.valueOf(view.text.toString())
    }*/
    //https://stackoverflow.com/questions/57555280/android-two-way-databinding-float-to-edittext
}