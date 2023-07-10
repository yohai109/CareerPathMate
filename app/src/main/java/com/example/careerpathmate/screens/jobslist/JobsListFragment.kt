package com.example.careerpathmate.screens.jobslist

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.careerpathmate.MainActivity
import com.example.careerpathmate.R
import com.example.careerpathmate.baseclasses.BaseFragment
import com.example.careerpathmate.databinding.FragmentJobsListBinding
import com.example.careerpathmate.uimodels.JobLocationUi
import com.example.careerpathmate.uimodels.JobUiStatus
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class JobsListFragment : BaseFragment<FragmentJobsListBinding>(FragmentJobsListBinding::inflate) {
    private val viewModel: JobListViewModel by viewModels()

    override fun FragmentJobsListBinding.initUI() {
        (activity as? MainActivity)?.setToolbarTitle("Job List")

        newJobFab.setOnClickListener {
            findNavController().navigate(JobsListFragmentDirections.actionJobsListFragmentToCreateJobFragment())
        }

        jobsListRV.adapter = JobListAdapter(
            onClickListener = {
                val action = JobsListFragmentDirections
                    .actionJobsListFragmentToJobDetailsFragment(
                        jobId = it.id
                    )
                root.findNavController().navigate(action)
            },
            onLongClickListener = {
                val navController = root.findNavController()
                val action = JobsListFragmentDirections
                    .actionJobsListFragmentToJobListLongClickDialog(
                        jobId = it.id,
                        jobStatus = it.status
                    )
                navController.navigate(action)
            }
        )

        jobsListRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            jobsListRV.context,
            RecyclerView.VERTICAL
        )

        jobsListRV.addItemDecoration(dividerItemDecoration)

        (activity as? MenuHost)?.addMenuProvider(
            menuProvider,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        searchBar.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchTerm(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchTerm(newText ?: "")
                return true
            }
        })

        filterBtn.setOnClickListener {
            filterMenu.root.isVisible = !filterMenu.root.isVisible
        }

        JobUiStatus.values().forEach { currStatus ->
            val chip = Chip(context).apply {
                setText(currStatus.title)
                setChipIconResource(currStatus.icon)

                setOnClickListener {
                    it.isSelected = !it.isSelected
                    viewModel.toggleStatusFilter(currStatus)
                }
            }
            filterMenu.statusFilterOptions.addView(chip)
        }

        JobLocationUi.values().forEach { currLocation ->
            val chip = Chip(context).apply {
                setText(currLocation.title)
                setChipIconResource(currLocation.icon)

                setOnClickListener {
                    it.isSelected = !it.isSelected
                    viewModel.toggleLocationFilter(currLocation)
                }
            }
            filterMenu.locationFilterOptions.addView(chip)
        }
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

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.job_list_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.searchItem -> binding?.apply {
                    searchContainer.isVisible = !searchContainer.isVisible
                }
            }
            return true
        }
    }
}