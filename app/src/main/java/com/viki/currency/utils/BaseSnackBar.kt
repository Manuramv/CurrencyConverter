package com.viki.currency.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.viki.currency.ui.currency.CurrencyConvertActivity

object BaseSnackBar {
    fun showErrorMsg(view: View, msg:String){
        val snackbar = Snackbar.make(
            view, msg,
            Snackbar.LENGTH_LONG)
            .setAction("OK",{
            })
        snackbar.show()
    }
}