package com.yohai.careerpathmate.screens.jobdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yohai.careerpathmate.databinding.ItemJobStepBinding
import com.yohai.careerpathmate.uimodels.JobStepDifUtil
import com.yohai.careerpathmate.uimodels.JobStepUiModel


class JobStepsAdapter(
    private val clickListener: (JobStepUiModel) -> Unit,
    private val longClickListener: (JobStepUiModel) -> Unit
) : ListAdapter<JobStepUiModel, JobStepViewHolder>(JobStepDifUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobStepViewHolder {
        val binding = ItemJobStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobStepViewHolder(binding, clickListener, longClickListener)
    }

    override fun onBindViewHolder(holder: JobStepViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}