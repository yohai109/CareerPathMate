package com.yohai.careerpathmate.uimodels

import androidx.recyclerview.widget.DiffUtil

object JobDiffUtil : DiffUtil.ItemCallback<JobUiModel>() {
    override fun areItemsTheSame(oldItem: JobUiModel, newItem: JobUiModel): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: JobUiModel, newItem: JobUiModel): Boolean =
        oldItem == newItem

}