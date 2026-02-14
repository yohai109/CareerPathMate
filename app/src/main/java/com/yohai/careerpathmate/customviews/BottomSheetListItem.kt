package com.yohai.careerpathmate.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.yohai.careerpathmate.R
import com.yohai.careerpathmate.databinding.BottomSheetListItemBinding

class BottomSheetListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    var label: String? = null

    @DrawableRes
    var icon: Int? = null

    private val binding = BottomSheetListItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

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
            R.styleable.BottomSheetListItem,
            defStyle,
            0
        )

        label = loadedAttrs.getString(
            R.styleable.BottomSheetListItem_label
        )
        icon = loadedAttrs.getResourceId(
            R.styleable.BottomSheetListItem_icon,
            0,
        )
        loadedAttrs.recycle()
    }

    private fun initUI() {
        binding.apply {
            icon?.let { actionIcon.setImageResource(it) }
            actionText.text = label
        }
    }
}