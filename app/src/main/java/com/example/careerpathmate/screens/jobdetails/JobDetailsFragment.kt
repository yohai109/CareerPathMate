package com.example.careerpathmate.screens.jobdetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.careerpathmate.MainActivity
import com.example.careerpathmate.baseclasses.BaseFragment
import com.example.careerpathmate.databinding.FragmentJobDetailsBinding
import com.example.careerpathmate.uimodels.JobUiModel
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
            val action = JobDetailsFragmentDirections
                .actionJobDetailsFragmentToCreateJobStepFragment(
                    viewModel.jobId
                )
            it.findNavController().navigate(action)
        }

        stepsRV.adapter = JobStepsAdapter() {
            val action = JobDetailsFragmentDirections.actionJobDetailsFragmentToStepLongClickDialog(
                it.id,
                it.status
            )
            root.findNavController().navigate(action)
        }
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