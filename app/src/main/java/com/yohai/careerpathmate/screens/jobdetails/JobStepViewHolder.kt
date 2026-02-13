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
    private lateinit var currStep: JobStepUiModel

    init {
        binding.root.setOnClickListener {
            clickListener(currStep)
        }
        binding.root.setOnLongClickListener {
            longClickListener(currStep)
            true
        }
    }

    fun bind(jobStepUiModel: JobStepUiModel) {
        binding.apply {
            currStep = jobStepUiModel
            stepName.text = jobStepUiModel.name
            val formatter = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
            date.text = formatter.format(jobStepUiModel.date.time)

            indicator.setBackgroundResource(jobStepUiModel.status.indicatorColor)
            status.setText(jobStepUiModel.status.title)
            statusIcon.setImageResource(jobStepUiModel.status.icon)

            location.setText(jobStepUiModel.location.title)
            locationIcon.setImageResource(jobStepUiModel.location.icon)
        }
    }
}