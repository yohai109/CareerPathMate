package com.example.joblogger.screens.jobdetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentJobDetailsBinding
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class JobDetailsFragment : BaseFragment<FragmentJobDetailsBinding>(
    FragmentJobDetailsBinding::inflate
) {
    private val viewModel: JobDetailsViewModel by viewModels()

    override fun FragmentJobDetailsBinding.initUI() {
        jobDetailsFAB.setOnClickListener {
            viewModel.createStep()
        }
    }

    override fun Bundle.initArguments() {
        val arg = JobDetailsFragmentArgs.fromBundle(this)
        viewModel.jobId = arg.jobId

    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.currJob().collect {
                    withContext(Dispatchers.Main) {
                        updateUI(it)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.steps().collect {
                    Timber.d("received ${it.size} steps")
                }
            }
        }
    }

    private fun updateUI(jobToShow: JobUiModel) {
        binding?.apply {
            companyName.text = jobToShow.companyName
            status.setText(jobToShow.status.title)
            jobToShow.status.icon.let {
                statusIcon.setImageResource(it)
            }
            contact.text = jobToShow.contactName
            description.text = jobToShow.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}