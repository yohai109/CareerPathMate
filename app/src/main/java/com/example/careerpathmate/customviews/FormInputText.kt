package com.example.careerpathmate.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.careerpathmate.R
import com.example.careerpathmate.databinding.FormInputTextBinding

/**
 * TODO: document your custom view class.
 */
class FormInputText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    var label: String? = null
    var placeholder: String? = null

    private val binding = FormInputTextBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    val input: EditText
        get() = binding.formInputText

    init {
        loadAttrs(context, attrs, defStyle)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            formInputLabel.text = label
            formInputText.hint = placeholder
        }
    }

    private fun loadAttrs(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) {
        val loadedAttrs = context.obtainStyledAttributes(
            attrs,
            R.styleable.FormInputText,
            defStyle,
            0
        )

        label = loadedAttrs.getString(
            R.styleable.FormInputText_label
        )
        placeholder = loadedAttrs.getString(
            R.styleable.FormInputText_placeholder
        )
        loadedAttrs.recycle()
    }
}