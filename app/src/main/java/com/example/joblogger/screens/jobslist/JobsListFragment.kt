package com.example.joblogger.screens.jobslist

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentJobsListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class JobsListFragment : BaseFragment<FragmentJobsListBinding>(FragmentJobsListBinding::inflate) {
    private val viewModel: JobListViewModel by viewModels()

    override fun FragmentJobsListBinding.initUI() {
        newJobFab.setOnClickListener {
            findNavController().navigate(JobsListFragmentDirections.actionJobsListFragmentToCreateJobFragment())
        }

        jobsListRV.adapter = JobListAdapter(
            onClickListener = {
                val action = JobsListFragmentDirections.actionJobsListFragmentToJobDetailsFragment(
                    jobId = it.id
                )
                root.findNavController().navigate(action)
            },
            onLongClickListener = {
                //TODO show bottomSheet
                val action = JobsListFragmentDirections.actionJobsListFragmentToJobListLongClickDialog(
                    jobId = it.id
                )
                root.findNavController().navigate(action)
            }
        )

        jobsListRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            jobsListRV.context,
            RecyclerView.VERTICAL
        )

        jobsListRV.addItemDecoration(dividerItemDecoration)

    }

    override fun initObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.jobs.collect {
                    withContext(Dispatchers.Main) {
                        (binding?.jobsListRV?.adapter as? JobListAdapter)?.submitList(it)
                    }
                }
            }
        }
    }
}