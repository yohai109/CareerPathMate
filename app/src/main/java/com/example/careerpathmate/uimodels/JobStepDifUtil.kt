package com.example.careerpathmate.uimodels

import androidx.recyclerview.widget.DiffUtil

object JobStepDifUtil : DiffUtil.ItemCallback<JobStepUiModel>() {
    override fun areItemsTheSame(oldItem: JobStepUiModel, newItem: JobStepUiModel): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: JobStepUiModel, newItem: JobStepUiModel): Boolean =
        oldItem == newItem

}