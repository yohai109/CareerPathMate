package com.example.joblogger.screens.jobdetails

import androidx.recyclerview.widget.RecyclerView
import com.example.joblogger.databinding.ItemJobStepBinding
import com.example.joblogger.uimodels.JobStepUiModel
import java.text.SimpleDateFormat
import java.util.Locale

class JobStepViewHolder(private val binding: ItemJobStepBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(jobStepUiModel: JobStepUiModel) {
        binding.apply {
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