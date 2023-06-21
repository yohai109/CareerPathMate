package com.example.joblogger.screens.jobslist

import androidx.lifecycle.ViewModel
import com.example.joblogger.local.MainDatabase
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(private val db: MainDatabase) : ViewModel() {
    val jobs = db.jobsDao().getAll().map {
        it.map {
            JobUiModel(it)
        }
    }
}