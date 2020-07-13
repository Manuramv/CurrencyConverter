package com.viki.currency.edittxt;

import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class TxtBindingUtil {
    @BindingAdapter("android:text")
    public static void setDouble(TextView view, Double val) {
        if (val != null) {
            String currentValue = view.getText().toString();
            if (currentValue.length() != 0) {
                try {
                    double oldVal = Double.parseDouble(currentValue);
                    if (oldVal == val) {
                        return;
                    }
                } catch (NumberFormatException e) {
                    // that's ok, we can just set the value.
                }
            }
            view.setText(val.toString());
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static Double getDouble(TextView view) {
        try {
            if(view.getText().toString().equals("NaN")){
                view.setHint("Enter Number");
                view.setText("");
                return null;
            } else {
                return Double.parseDouble(view.getText().toString());
            }
        } catch (NumberFormatException e) {
            view.setHint("Enter Number");
            return null;
        }
    }
}

