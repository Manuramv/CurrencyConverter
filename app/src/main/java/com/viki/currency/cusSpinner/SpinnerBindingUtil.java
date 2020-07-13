package com.viki.currency.cusSpinner;

import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.viki.currency.R;
import com.viki.currency.ui.currency.CurrencyData;

import java.util.List;

public class SpinnerBindingUtil {
    @BindingAdapter(value = {"selectedValue","setentries", "selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, CurrencyData newSelectedValue,  List<CurrencyData> projects,final InverseBindingListener newTextAttrChanged) {
       pAppCompatSpinner.setAdapter(new CurrencySpinnerAdapter(pAppCompatSpinner.getContext(), R.layout.country_custom_spinner, projects));


        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
           // int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            int pos = ((CurrencySpinnerAdapter) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }
    }
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static CurrencyData captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return (CurrencyData) pAppCompatSpinner.getSelectedItem();
    }
}
