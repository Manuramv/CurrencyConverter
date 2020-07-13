package com.viki.currency.edittxt;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class TxtBindingUtil {
   /* @BindingAdapter("android:text")
    public static void setFloat(TextView view, float value) {
        if (Float.isNaN(value)) view.setText("");
        else view.setHint("value");
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static float getFloat(TextView view) {
        String num = view.getText().toString();
        if(num.isEmpty()) return 0.0F;
        try {
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            return 0.0F;
        }
    }*/


   /* @BindingAdapter("android:text")
    public static void setText(TextView view, float value) {
        boolean setValue = view.getText().length() == 0;
        try {
            if (!setValue) {
                setValue = Float.parseFloat(view.getText().toString()) != value;
            }
        } catch (NumberFormatException e) {
            view.setHint("enter manu");
        }
        if (setValue) {
            view.setText(String.valueOf(value));
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static Object getText(TextView view) {
        try {
            return Float.parseFloat(view.getText().toString());
        } catch (NumberFormatException e) {
            view.setHint("wrong");
            return null;
        }
    }*/

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
            return Double.parseDouble(view.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
