package com.example.joblogger.screens.jobslist

import androidx.recyclerview.widget.RecyclerView
import com.example.joblogger.databinding.ItemJobBinding
import com.example.joblogger.local.model.JobEntity

class JobItemViewHolder(
    private val binding: ItemJobBinding,
    val onClickListener: ((JobEntity) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {
    lateinit var currJob: JobEntity

    init {
        binding.root.setOnClickListener {
            onClickListener(currJob)
        }
    }

    fun bind(job: JobEntity) {
        currJob = job
        binding.apply {
            companyName.text = currJob.companyName
        }
    }
}