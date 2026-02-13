package com.yohai.careerpathmate.screens.jobslist.joblongclickdialog

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yohai.careerpathmate.baseclasses.BaseBottomSheetDialogFragment
import com.yohai.careerpathmate.databinding.DialogJobListLongClickBinding
import com.yohai.careerpathmate.local.model.JobStatus
import com.yohai.careerpathmate.uimodels.JobUiStatus
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
            null -> context?.applicationContext?.let { ctx ->
                Toast.makeText(
                    ctx,
                    ctx.getString(com.yohai.careerpathmate.R.string.error_illegal_argument),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        editAction.setOnClickListener {
            jobId?.let { jobId ->
                val action = JobLongClickDialogDirections
                    .actionJobLongClickDialogToCreateJobFragment(
                        jobId
                    )

                this@JobLongClickDialog.findNavController().navigate(action)
            }
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