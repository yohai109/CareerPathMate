package com.example.joblogger.screens.jobslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.MainDatabase
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(private val db: MainDatabase) : ViewModel() {
    val jobs = db.jobsDao().getAll().map {
        it.map {
            JobUiModel(it)
        }
    }
}