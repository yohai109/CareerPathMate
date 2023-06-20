package com.example.joblogger.screens.createjob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(private val repo: CreateJobRepo) : ViewModel() {
    var jobToCreate = JobUiModel(
        companyName = ""
    )

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.save(JobEntity(jobToCreate))
        }
    }
}