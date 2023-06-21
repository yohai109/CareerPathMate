package com.example.joblogger.screens.jobslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.model.JobStatus
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(private val repo: JobListRepo) : ViewModel() {
    val jobs = repo.allJobs.map {
        it.map {
            JobUiModel(it)
        }
    }


}