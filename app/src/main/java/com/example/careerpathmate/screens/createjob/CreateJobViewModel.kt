package com.example.careerpathmate.screens.createjob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerpathmate.local.model.JobEntity
import com.example.careerpathmate.uimodels.JobUiModel
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
}