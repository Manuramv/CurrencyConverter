/*
package com.viki.currency.edittxt

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener


@BindingAdapter(value = ["input", "inputAttrChanged"], requireAll = false)
fun bindIntegerInText(
    tv: AppCompatEditText,
    value: Int,
    inverseBindingListener: InverseBindingListener
) {
    tv.setText(value.toString())
    // Set the cursor to the end of the text
    tv.setSelection(tv.text!!.length)
    tv.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence,
            start: Int,
            count: Int,
            after: Int
        ) { //inverseBindingListener.onChange();
        }

        override fun onTextChanged(
            s: CharSequence,
            start: Int,
            before: Int,
            count: Int
        ) {
            inverseBindingListener.onChange()
        }

        override fun afterTextChanged(s: Editable) { //inverseBindingListener.onChange();
        }
    })
}


@InverseBindingAdapter(attribute = "app:input", event = "app:inputAttrChanged")
fun bindCountryInverseAdapter(view: AppCompatEditText): Int {
    val string = view.text.toString()
    return  string.toInt()
}


@BindingAdapter("textv", "textvAttrChanged")
fun setText(view: EditText, value: String?) {
    view.setText(value)
}

@InverseBindingAdapter(attribute = "textv")
fun getText(view: EditText): String? {
    return view.text.toString()
}



*/
