package com.example.joblogger.screens.jobdetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joblogger.MainActivity
import com.example.joblogger.R
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

        stepsRV.adapter = JobStepsAdapter()
        stepsRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
                        jobInfo(it)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.steps().collect {
                    Timber.d("received ${it.size} steps")
                    (binding?.stepsRV?.adapter as? JobStepsAdapter)?.submitList(it)
                }
            }
        }
    }

    private fun jobInfo(jobToShow: JobUiModel) {
        binding?.apply {
            (activity as? MainActivity)?.setToolbarTitle(jobToShow.companyName)

            contact.text = jobToShow.contactName
            description.text = jobToShow.description

            status.setText(jobToShow.status.title)
            statusIcon.setImageResource(jobToShow.status.icon)

            location.setText(jobToShow.location.title)
            locationIcon.setImageResource(jobToShow.location.icon)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}