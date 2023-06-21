package com.example.joblogger.screens.createjob

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentCreateJobBinding
import com.example.joblogger.uimodels.JobUiStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateJobFragment : BaseFragment<FragmentCreateJobBinding>(
    FragmentCreateJobBinding::inflate
) {
    private val viewModel: CreateJobViewModel by viewModels()

    override fun FragmentCreateJobBinding.initUI() {
        createJobFAB.setOnClickListener {
            viewModel.jobToCreate = viewModel.jobToCreate.copy(
                companyName = companyNameInputText.input.text.toString(),
                status = statusPickerSpinner.selectedItem as JobUiStatus,
                contactName = contactInputText.input.text.toString(),
                description = descriptionInputText.input.text.toString()
            )
            viewModel.save()
            it.findNavController().navigateUp()
        }

        context?.let { context ->
            val adapter = JobUiStatusArrayAdapter(context)
            adapter.apply {
                statusPickerSpinner.adapter = this
            }
        }
    }

    override fun initObservers() {

    }
}