package com.example.joblogger.screens.jobdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentJobDetailsBinding
import com.example.joblogger.local.model.JobEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class JobDetailsFragment : BaseFragment<FragmentJobDetailsBinding>(
    FragmentJobDetailsBinding::inflate
) {
    private val viewModel: JobDetailsViewModel by viewModels()
    private var jobId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initObservers()
    }

    private fun initArguments() {
        arguments?.let {
            val arg = JobDetailsFragmentArgs.fromBundle(it)
            jobId = arg.jobId
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.currJob(jobId ?: "").collect {
                    withContext(Dispatchers.Main) {
                        updateUI(it)
                    }
                }
            }
        }
    }

    private fun updateUI(jobToShow: JobEntity) {
        binding?.apply {
            companyName.text = jobToShow.companyName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobId = null
    }
}