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
        val text = if (convertView is CheckedTextView) {
            convertView
        } else {
            LayoutInflater.from(context)
                .inflate(
                    android.R.layout.simple_spinner_dropdown_item,
                    parent,
                    false
                ) as? CheckedTextView
        }

        text?.let { textView ->
            getItem(position)?.let { item -> textView.text = initText(item) }
        }
        return text ?: View(context)
    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val textView = if (convertView is CheckedTextView) {
            convertView
        } else {
            LayoutInflater.from(context)
                .inflate(
                    android.R.layout.simple_spinner_dropdown_item,
                    parent,
                    false
                ) as? CheckedTextView
        }

        textView?.let { view ->
            getItem(position)?.let { item -> view.text = initText(item) }
        }
        return textView ?: View(context)
    }
}