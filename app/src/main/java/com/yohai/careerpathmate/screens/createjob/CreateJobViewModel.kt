package com.yohai.careerpathmate.screens.createjob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yohai.careerpathmate.local.model.JobEntity
import com.yohai.careerpathmate.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(private val repo: CreateJobRepo) : ViewModel() {
    var jobToCreate = JobUiModel(
        companyName = "",
        description = "",
        contactName = ""
    )

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.save(JobEntity(jobToCreate))
        }
    }

    fun setJobId(jobId: String?, callback: () -> Unit) {
        jobId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repo.getById(it).collect { collectedJob ->
                    jobToCreate = JobUiModel(collectedJob)
                    callback()
                }
            }
        }
    }
}