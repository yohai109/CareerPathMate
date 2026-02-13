package com.yohai.careerpathmate.screens.jobdetails

import androidx.recyclerview.widget.RecyclerView
import com.yohai.careerpathmate.databinding.ItemJobStepBinding
import com.yohai.careerpathmate.uimodels.JobStepUiModel
import java.text.SimpleDateFormat
import java.util.Locale

class JobStepViewHolder(
    private val binding: ItemJobStepBinding,
    private val clickListener: (JobStepUiModel) -> Unit,
    private val longClickListener: (JobStepUiModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currStep: JobStepUiModel? = null

    init {
        binding.root.setOnClickListener {
            currStep?.let { clickListener(it) }
        }
        binding.root.setOnLongClickListener {
            currStep?.let { longClickListener(it) }
            true
        }
    }

    fun bind(jobStepUiModel: JobStepUiModel) {
        binding.apply {
            currStep = jobStepUiModel
            stepName.text = jobStepUiModel.name
            val dateFormatter = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
            date.text = dateFormatter.format(jobStepUiModel.date.time)

            indicator.setBackgroundResource(jobStepUiModel.status.indicatorColor)
            status.setText(jobStepUiModel.status.title)
            statusIcon.setImageResource(jobStepUiModel.status.icon)

            location.setText(jobStepUiModel.location.title)
            locationIcon.setImageResource(jobStepUiModel.location.icon)
        }
    }
}