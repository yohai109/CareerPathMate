package com.example.joblogger.screens.jobdetails

import androidx.lifecycle.ViewModel
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repo: JobDetailsRepo
) : ViewModel() {
    fun currJob(id: String) = repo.currJob(id).map {
        JobUiModel(it)
    }
}