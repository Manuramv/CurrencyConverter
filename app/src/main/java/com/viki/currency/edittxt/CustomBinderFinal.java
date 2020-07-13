package com.viki.currency.edittxt;

import android.widget.TextView;

import androidx.databinding.InverseMethod;

import kotlin.jvm.JvmStatic;

public  class CustomBinderFinal {

    @InverseMethod("doubleToString")
    public static Double stringToDouble(TextView view, CharSequence value) {
        if (value == null) {
            return null;
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public static CharSequence doubleToString(TextView view, Double value) {
        if (value == null) {
            return "";
        }
        String oldText = view.getText().toString();
        if (!oldText.isEmpty()) {
            try {
                double oldVal = Double.parseDouble(oldText);
                if (oldVal == value) {
                    return view.getText();
                }
            } catch (NumberFormatException e) {
                // No problem. The TextView had text that wasn't formatted properly
            }
        }
        return value.toString();
    }
}


