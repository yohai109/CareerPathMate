package com.example.joblogger.screens.jobslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.MainDatabase
import com.example.joblogger.local.model.JobEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(private val db: MainDatabase) : ViewModel() {
//    val jobs = MutableStateFlow(
//        arrayListOf(
//            JobEntity(companyName = "first"),
//            JobEntity(companyName = "first"),
//            JobEntity(companyName = "first"),
//            JobEntity(companyName = "first"),
//            JobEntity(companyName = "first"),
//            JobEntity(companyName = "first"),
//        )
//    )

    val jobs = db.jobsDao().getAll()

    fun createJob() {
        viewModelScope.launch(Dispatchers.IO) {
            db.jobsDao().upsert(JobEntity(companyName = "New job"))
        }
    }
}