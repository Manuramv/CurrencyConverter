/*
package com.viki.currency.edittxt

import android.content.Context
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseMethod

object MybindingAdapter {
    */
/**
     * Needs to be used with [NumberOfSetsConverters.setArrayToString].
     *//*

    @BindingAdapter("numberOfSets")
    @JvmStatic fun setNumberOfSets(view: EditText, value: String) {
        view.setText(value)
    }

    */
/**
     * Called when the [InverseBindingListener] of the `numberOfSetsAttrChanged` binding adapter
     * is notified of a change.
     *
     * Used with the inverse method of [NumberOfSetsConverters.setArrayToString], which is
     * [NumberOfSetsConverters.stringToSetArray].
     *//*

    @InverseBindingAdapter(attribute = "numberOfSets")
    @JvmStatic fun getNumberOfSets(editText: EditText): String {
        return editText.text.toString()
    }
}

object NumberOfSetsConverters {

    */
/**
     * Used with `numberOfSets` to convert from array to String.
     *//*

    @InverseMethod("stringToInt")
    @JvmStatic fun setArrayToString( value: Int): String {
        //return context.getString(R.string.sets_format, value[0] + 1, value[1])
        return value.toString()
    }

    */
/**
     * This is the Inverse Method used in `numberOfSets`, to convert from String to array.
     *
     * Note that Context is passed
     *//*

    @JvmStatic fun stringToSetArray( value: String): Int {
        // Converts String to long
        if (value.isEmpty()) {
            return 0
        }

        return try {
            value.toInt() // First item is not passed
        } catch (e: Exception) {
            value.toInt()
        }
    }
}
*/
