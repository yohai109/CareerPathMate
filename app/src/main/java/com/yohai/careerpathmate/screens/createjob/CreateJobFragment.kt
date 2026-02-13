package com.yohai.careerpathmate.screens.createjob

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.yohai.careerpathmate.MainActivity
import com.yohai.careerpathmate.R
import com.yohai.careerpathmate.baseclasses.BaseFragment
import com.yohai.careerpathmate.customviews.SpinnerGenericAdapter
import com.yohai.careerpathmate.databinding.FragmentCreateJobBinding
import com.yohai.careerpathmate.uimodels.JobLocationUi
import com.yohai.careerpathmate.uimodels.JobUiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateJobFragment : BaseFragment<FragmentCreateJobBinding>(
    FragmentCreateJobBinding::inflate
) {
    private val viewModel: CreateJobViewModel by viewModels()
    private val navArgs: CreateJobFragmentArgs by navArgs()
    private var jobId: String? = null

    override fun FragmentCreateJobBinding.initUI() {
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            fabWrapper.updatePadding(bottom = insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

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
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
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
}