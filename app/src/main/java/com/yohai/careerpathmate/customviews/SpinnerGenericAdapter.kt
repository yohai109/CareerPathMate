package com.yohai.careerpathmate.customviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView

class SpinnerGenericAdapter<T>(
    context: Context,
    values: Array<T>,
    var initText: (T) -> String
) : ArrayAdapter<T>(
    context,
    android.R.layout.simple_spinner_item,
    values
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var text = convertView as? CheckedTextView
        if (text == null) {
            text = LayoutInflater.from(context)
                .inflate(
                    android.R.layout.simple_spinner_dropdown_item,
                    parent,
                    false
                ) as CheckedTextView
        }

        getItem(position)?.let { text.text = initText(it) }
        return text
    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var textView: CheckedTextView? = convertView as? CheckedTextView
        if (textView == null) {
            textView = LayoutInflater.from(context)
                .inflate(
                    android.R.layout.simple_spinner_dropdown_item,
                    parent,
                    false
                ) as CheckedTextView
        }

        getItem(position)?.let { textView.text = initText(it) }
        return textView
    }
}