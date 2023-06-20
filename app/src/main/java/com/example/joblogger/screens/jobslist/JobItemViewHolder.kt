package com.example.joblogger.screens.jobslist

import androidx.recyclerview.widget.RecyclerView
import com.example.joblogger.databinding.ItemJobBinding
import com.example.joblogger.uimodels.JobUiModel

class JobItemViewHolder(
    private val binding: ItemJobBinding,
    val onClickListener: ((JobUiModel) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {
    lateinit var currJob: JobUiModel

    init {
        binding.root.setOnClickListener {
            onClickListener(currJob)
        }
    }

    fun bind(job: JobUiModel) {
        currJob = job
        binding.apply {
            companyName.text = currJob.companyName
            status.text = currJob.status.name
        }
    }
}