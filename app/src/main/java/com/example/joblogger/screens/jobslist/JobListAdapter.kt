package com.example.joblogger.screens.jobslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.joblogger.databinding.ItemJobBinding
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.local.model.JobDiffUtil


class JobListAdapter(
    private val onClickListener: (JobEntity) -> Unit
) : ListAdapter<JobEntity, JobItemViewHolder>(JobDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobItemViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: JobItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}