package com.example.careerpathmate.screens.jobdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.careerpathmate.databinding.ItemJobStepBinding
import com.example.careerpathmate.uimodels.JobStepDifUtil
import com.example.careerpathmate.uimodels.JobStepUiModel


class JobStepsAdapter(
  private val clickListener: (JobStepUiModel) -> Unit
) : ListAdapter<JobStepUiModel,JobStepViewHolder>(JobStepDifUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobStepViewHolder {
        val binding = ItemJobStepBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return JobStepViewHolder(binding,clickListener)
    }

    override fun onBindViewHolder(holder: JobStepViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}