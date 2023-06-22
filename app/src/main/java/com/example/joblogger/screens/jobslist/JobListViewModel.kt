package com.example.joblogger.screens.jobslist

import androidx.lifecycle.ViewModel
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(private val repo: JobListRepo) : ViewModel() {
    val jobs = repo.allJobs.map {
        it.map {
            JobUiModel(it)
        }
    }


}