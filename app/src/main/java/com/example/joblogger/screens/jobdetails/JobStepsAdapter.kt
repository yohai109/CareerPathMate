package com.example.joblogger.screens.jobdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.joblogger.databinding.ItemJobStepBinding
import com.example.joblogger.uimodels.JobStepDifUtil
import com.example.joblogger.uimodels.JobStepUiModel


class JobStepsAdapter: ListAdapter<JobStepUiModel,JobStepViewHolder>(JobStepDifUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobStepViewHolder {
        val binding = ItemJobStepBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return JobStepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobStepViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}