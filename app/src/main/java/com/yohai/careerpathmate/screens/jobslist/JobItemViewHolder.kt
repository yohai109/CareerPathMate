package com.yohai.careerpathmate.screens.jobslist

import androidx.recyclerview.widget.RecyclerView
import com.yohai.careerpathmate.databinding.ItemJobBinding
import com.yohai.careerpathmate.uimodels.JobUiModel

class JobItemViewHolder(
    private val binding: ItemJobBinding,
    val onClickListener: ((JobUiModel) -> Unit),
    val longClickListener: ((JobUiModel) -> Unit),
) : RecyclerView.ViewHolder(binding.root) {
    lateinit var currJob: JobUiModel

    init {
        binding.root.setOnClickListener {
            onClickListener(currJob)
        }

        binding.root.setOnLongClickListener {
            longClickListener(currJob)
            true
        }
    }

    fun bind(job: JobUiModel) {
        currJob = job
        binding.apply {
            companyName.text = currJob.companyName
            status.setText(currJob.status.title)
            statusIcon.setImageResource(currJob.status.icon)
            indicator.setBackgroundResource(currJob.status.indicatorColor)
        }
    }
}