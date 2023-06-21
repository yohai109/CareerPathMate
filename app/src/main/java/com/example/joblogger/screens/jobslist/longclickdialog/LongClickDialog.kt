package com.example.joblogger.screens.jobslist.longclickdialog

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.joblogger.baseclasses.BaseBottomSheetDialogFragment
import com.example.joblogger.databinding.DialogJobListLongClickBinding
import com.example.joblogger.local.model.JobStatus
import com.example.joblogger.screens.jobdetails.JobDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LongClickDialog : BaseBottomSheetDialogFragment<DialogJobListLongClickBinding>(
    DialogJobListLongClickBinding::inflate
) {
    private val viewModel: JobListLongClickViewModel by viewModels()
    private var jobId: String? = null

    override fun DialogJobListLongClickBinding.initUI() {
        actionMarkNo.setOnClickListener {
            jobId?.let { id -> viewModel.updateStatus(id, JobStatus.No) }
        }
        this@LongClickDialog.dismiss()
    }

    override fun initObservers() {

    }

    override fun Bundle.initArguments() {
        val arg = JobDetailsFragmentArgs.fromBundle(this)
        jobId = arg.jobId
    }
}