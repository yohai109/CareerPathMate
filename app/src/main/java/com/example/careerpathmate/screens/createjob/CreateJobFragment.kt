package com.example.careerpathmate.screens.createjob

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.careerpathmate.MainActivity
import com.example.careerpathmate.R
import com.example.careerpathmate.baseclasses.BaseFragment
import com.example.careerpathmate.customviews.SpinnerGenericAdapter
import com.example.careerpathmate.databinding.FragmentCreateJobBinding
import com.example.careerpathmate.uimodels.JobLocationUi
import com.example.careerpathmate.uimodels.JobUiStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateJobFragment : BaseFragment<FragmentCreateJobBinding>(
    FragmentCreateJobBinding::inflate
) {
    private val viewModel: CreateJobViewModel by viewModels()
    private val navArgs: CreateJobFragmentArgs by navArgs()
    private var jobId: String? = null

    override fun FragmentCreateJobBinding.initUI() {


        createJobFAB.setOnClickListener {
            viewModel.jobToCreate = viewModel.jobToCreate.copy(
                companyName = companyNameInputText.input.text.toString(),
                status = statusPickerSpinner.selectedItem as JobUiStatus,
                location = jobLocationSpinner.selectedItem as JobLocationUi,
                contactName = contactInputText.input.text.toString(),
                description = descriptionInputText.input.text.toString()
            )
            viewModel.save()
            it.findNavController().navigateUp()
        }

        context?.let { context ->
            statusPickerSpinner.setAdapter(
                SpinnerGenericAdapter(
                    context,
                    JobUiStatus.values()
                ) {
                    context.getString(it.title)
                }
            )

            jobLocationSpinner.setAdapter(
                SpinnerGenericAdapter(
                    context,
                    JobLocationUi.values()
                ) {
                    context.getString(it.title)
                }
            )
        }
    }

    override fun initArguments() {
        jobId = navArgs.jobId
        val title = if (jobId == null) {
            R.string.create_job_title
        } else {
            R.string.edit_job_title
        }

        (activity as? MainActivity)?.setToolbarTitle(title)
        viewModel.setJobId(jobId) {
            binding?.apply {
                viewModel.jobToCreate.let { job ->
                    companyNameInputText.input.setText(job.companyName)
                    contactInputText.input.setText(job.contactName)
                    descriptionInputText.input.setText(job.description)
                    jobLocationSpinner.setSelection(job.location)
                    statusPickerSpinner.setSelection(job.status)
                }
            }
        }
    }
}