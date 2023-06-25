package com.example.joblogger.screens.jobslist.joblongclickdialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.model.JobStatus
import com.example.joblogger.screens.jobslist.JobListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListLongClickViewModel @Inject constructor(private val repo: JobListRepo) : ViewModel() {
    fun updateStatus(id: String, newStatus: JobStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateStatus(id, newStatus)
        }
    }

    fun deleteJob(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteJobs(id)
        }
    }
}