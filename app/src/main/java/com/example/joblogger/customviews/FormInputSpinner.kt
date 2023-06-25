package com.example.joblogger.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.joblogger.R
import com.example.joblogger.databinding.FormInputSpinnerBinding

class FormInputSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val binding = FormInputSpinnerBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )
    var label: String? = null

    val selectedItem: Any?
        get() = binding.formInputSpinner.selectedItem

    init {
        loadAttrs(context, attrs, defStyle)
        initUI()
    }

    private fun loadAttrs(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) {
        val loadedAttrs = context.obtainStyledAttributes(
            attrs,
            R.styleable.FormInputSpinner,
            defStyle,
            0
        )

        label = loadedAttrs.getString(
            R.styleable.FormInputSpinner_label
        )

        loadedAttrs.recycle()
    }

    private fun initUI() {
        binding.apply {
            formInputLabel.text = label
        }
    }

    fun setAdapter(adapter: SpinnerGenericAdapter<*>) {
        binding.apply {
            formInputSpinner.adapter = adapter
        }
    }
}