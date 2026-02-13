package com.yohai.careerpathmate.screens.jobdetails

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yohai.careerpathmate.MainActivity
import com.yohai.careerpathmate.baseclasses.BaseFragment
import com.yohai.careerpathmate.databinding.FragmentJobDetailsBinding
import com.yohai.careerpathmate.uimodels.JobUiModel
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
    private val navArgs: JobDetailsFragmentArgs by navArgs()

    override fun FragmentJobDetailsBinding.initUI() {
        ViewCompat.setOnApplyWindowInsetsListener(root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            stepsRV.updatePadding(bottom = stepsRV.paddingBottom + insets.bottom)
            windowInsets
        }
        
        jobDetailsFAB.setOnClickListener {
            val action = JobDetailsFragmentDirections
                .actionJobDetailsFragmentToCreateJobStepFragment(
                    viewModel.jobId
                )
            it.findNavController().navigate(action)
        }

        stepsRV.adapter = JobStepsAdapter(
            {
                val action = JobDetailsFragmentDirections
                    .actionJobDetailsFragmentToStepLongClickDialog(
                        it.id,
                        it.status
                    )
                root.findNavController().navigate(action)
            },
            {
                val action = JobDetailsFragmentDirections
                    .actionJobDetailsFragmentToCreateJobStepFragment(
                        jobId = viewModel.jobId,
                        stepId = it.id
                    )

                root.findNavController().navigate(action)
            }
        )
        stepsRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun initArguments() {
        viewModel.jobId = navArgs.jobId
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
}