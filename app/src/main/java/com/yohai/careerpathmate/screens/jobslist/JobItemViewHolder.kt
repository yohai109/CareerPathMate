package com.yohai.careerpathmate.screens.jobslist

import androidx.recyclerview.widget.RecyclerView
import com.yohai.careerpathmate.databinding.ItemJobBinding
import com.yohai.careerpathmate.uimodels.JobUiModel

class JobItemViewHolder(
    private val binding: ItemJobBinding,
    val onClickListener: ((JobUiModel) -> Unit),
    val longClickListener: ((JobUiModel) -> Unit),
) : RecyclerView.ViewHolder(binding.root) {
    private var currJob: JobUiModel? = null

    init {
        binding.root.setOnClickListener {
            currJob?.let { onClickListener(it) }
        }

        binding.root.setOnLongClickListener {
            currJob?.let { longClickListener(it) }
            true
        }
    }

    fun bind(job: JobUiModel) {
        currJob = job
        binding.apply {
            companyName.text = currJob?.companyName
            currJob?.status?.let {
                status.setText(it.title)
                statusIcon.setImageResource(it.icon)
                indicator.setBackgroundResource(it.indicatorColor)
            }
        }
    }
}