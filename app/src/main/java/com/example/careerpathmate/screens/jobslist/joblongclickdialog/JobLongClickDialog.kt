package com.example.careerpathmate.screens.jobslist.joblongclickdialog

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.careerpathmate.baseclasses.BaseBottomSheetDialogFragment
import com.example.careerpathmate.databinding.DialogJobListLongClickBinding
import com.example.careerpathmate.local.model.JobStatus
import com.example.careerpathmate.uimodels.JobUiStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobLongClickDialog : BaseBottomSheetDialogFragment<DialogJobListLongClickBinding>(
    DialogJobListLongClickBinding::inflate
) {
    private val viewModel: JobListLongClickViewModel by viewModels()
    private val arg: JobLongClickDialogArgs by navArgs()
    private var jobId: String? = null
    private var currStatus: JobUiStatus? = null

    override fun DialogJobListLongClickBinding.initUI() {
        when (currStatus) {
            JobUiStatus.OnGoing -> markAsOnGoingAction.isVisible = false
            JobUiStatus.Old -> markAsArchiveAction.isVisible = false
            JobUiStatus.Yes -> markAsYesAction.isVisible = false
            JobUiStatus.No -> markAsNoAction.isVisible = false
            null -> Toast.makeText(
                context?.applicationContext,
                "illegal argument",
                Toast.LENGTH_SHORT
            ).show()
        }

        markAsNoAction.setOnClickListener {
            jobId?.let { id -> viewModel.updateStatus(id, JobStatus.No) }
            this@JobLongClickDialog.dismiss()
        }

        markAsArchiveAction.setOnClickListener {
            jobId?.let { id -> viewModel.updateStatus(id, JobStatus.Old) }
            this@JobLongClickDialog.dismiss()
        }
        markAsYesAction.setOnClickListener {
            jobId?.let { id -> viewModel.updateStatus(id, JobStatus.Yes) }
            this@JobLongClickDialog.dismiss()
        }
        markAsOnGoingAction.setOnClickListener {
            jobId?.let { id -> viewModel.updateStatus(id, JobStatus.OnGoing) }
            this@JobLongClickDialog.dismiss()
        }
        deleteAction.setOnClickListener {
            jobId?.let { id -> viewModel.deleteJob(id) }
            this@JobLongClickDialog.dismiss()
        }
    }

    override fun initObservers() {

    }

    override fun Bundle.initArguments() {
        jobId = arg.jobId
        currStatus = arg.jobStatus
    }
}