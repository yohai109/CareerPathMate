package com.example.joblogger.screens.jobslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentJobsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class JobsListFragment : BaseFragment<FragmentJobsListBinding>(FragmentJobsListBinding::inflate) {
    private val viewModel: JobListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initUI() {
        binding?.apply {
            newJobFab.setOnClickListener {
                viewModel.createJob()
            }

            jobsListRV.adapter = JobListAdapter() {
                val toast = Toast(context?.applicationContext)
                toast.setText("clicked on ${it.companyName} id: ${it.id}")
                toast.show()
            }

            jobsListRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(
                jobsListRV.context,
                RecyclerView.VERTICAL
            )

            jobsListRV.addItemDecoration(dividerItemDecoration)
        }
    }

    private fun initObservers() {
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