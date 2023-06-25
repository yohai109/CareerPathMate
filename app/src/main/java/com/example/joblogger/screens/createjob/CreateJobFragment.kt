package com.example.joblogger.screens.createjob

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.joblogger.MainActivity
import com.example.joblogger.R
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.customviews.SpinnerGenericAdapter
import com.example.joblogger.databinding.FragmentCreateJobBinding
import com.example.joblogger.uimodels.JobLocationUi
import com.example.joblogger.uimodels.JobUiStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateJobFragment : BaseFragment<FragmentCreateJobBinding>(
    FragmentCreateJobBinding::inflate
) {
    private val viewModel: CreateJobViewModel by viewModels()

    override fun FragmentCreateJobBinding.initUI() {
        (activity as? MainActivity)?.setToolbarTitle(R.string.create_job_title)

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

    override fun initObservers() {

    }
}