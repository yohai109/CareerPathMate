package com.example.joblogger.screens.createjob

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import com.example.joblogger.uimodels.JobUiStatus

class JobUiStatusArrayAdapter(
    context: Context,
) : ArrayAdapter<JobUiStatus>(
    context,
    android.R.layout.simple_spinner_item,
    JobUiStatus.values()
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

        getItem(position)?.let { text.setText(it.title) }
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

        getItem(position)?.let { textView.setText(it.title) }
        return textView
    }
}