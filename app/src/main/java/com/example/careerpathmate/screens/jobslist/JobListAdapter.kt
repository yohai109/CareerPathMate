package com.example.careerpathmate.screens.jobslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.careerpathmate.databinding.ItemJobBinding
import com.example.careerpathmate.uimodels.JobDiffUtil
import com.example.careerpathmate.uimodels.JobUiModel


class JobListAdapter(
    private val onClickListener: (JobUiModel) -> Unit,
    private val onLongClickListener: (JobUiModel) -> Unit
) : ListAdapter<JobUiModel, JobItemViewHolder>(JobDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobItemViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobItemViewHolder(binding, onClickListener, onLongClickListener)
    }

    override fun onBindViewHolder(holder: JobItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}