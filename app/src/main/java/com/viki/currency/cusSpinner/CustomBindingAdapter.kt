package com.viki.currency.cusSpinner

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.viki.currency.R
import com.viki.currency.ui.currency.CurrencyData




@BindingAdapter(value = ["projects", "selectedProject", "selectedProjectAttrChanged"], requireAll = false)
fun setProjects(spinner: Spinner, projects: List<CurrencyData>?, selectedProject: CurrencyData?, listener: InverseBindingListener) {
   // Log.d("tag","selected proj="+selectedProject+"...projects::"+projects)
    if (projects == null) return
    spinner.adapter = CurrencySpinnerAdapter(spinner.context, R.layout.country_custom_spinner, projects)
    setCurrentSelection(spinner, selectedProject)
    setSpinnerListener(spinner, listener)
}
private fun setSpinnerListener(spinner: Spinner, listener: InverseBindingListener) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = listener.onChange()
        override fun onNothingSelected(adapterView: AdapterView<*>) = listener.onChange()
    }
}

private fun setCurrentSelection(spinner: Spinner, selectedItem: CurrencyData?): Boolean {
    if (selectedItem == null) {
        return false
    }
    for (index in 0 until spinner.adapter.count) {
        val currentItem = spinner.getItemAtPosition(index) as CurrencyData
        if (currentItem.name == selectedItem.name) {
            spinner.setSelection(index)
            return true
        }
    }
    return false
}

@InverseBindingAdapter(attribute = "selectedProject")
fun getSelectedProject(spinner: Spinner): CurrencyData {
    return spinner.selectedItem as CurrencyData
}



/**
 * fill the Spinner with all available projects.
 * Set the Spinner selection to selectedProject.
 * If the selection changes, call the InverseBindingAdapter
 */
/*
@BindingAdapter(value = ["projects", "selectedProject", "selectedProjectAttrChanged"], requireAll = false)
fun setProjects(spinner: Spinner, projects: List<CurrencyData>?, selectedProject: CurrencyData, listener: InverseBindingListener) {
    if (projects == null) return
    spinner.adapter = NameAdapter(spinner.context, R.layout.country_custom_spinner, projects)
    setCurrentSelection(spinner, selectedProject)
    setSpinnerListener(spinner, listener)
}

private fun setSpinnerListener(spinner: Spinner, listener: InverseBindingListener) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = listener.onChange()
        override fun onNothingSelected(adapterView: AdapterView<*>) = listener.onChange()
    }
}
private fun setCurrentSelection(spinner: Spinner, selectedItem: CurrencyData?): Boolean {
    if (selectedItem == null) {
        return false
    }
    for (index in 0 until spinner.adapter.count) {
        val currentItem = spinner.getItemAtPosition(index) as CurrencyData
        if (currentItem.name == selectedItem.name) {
            spinner.setSelection(index)
            return true
        }
    }
    return false
}


*/
/**
 * get the selected projectName and use it to return a
 * Project which is then used to set appEntry.value.project
 *//*

@InverseBindingAdapter(attribute = "selectedProject")
fun getSelectedProject(spinner: Spinner): CurrencyData {
    return spinner.selectedItem as CurrencyData
}


*/
