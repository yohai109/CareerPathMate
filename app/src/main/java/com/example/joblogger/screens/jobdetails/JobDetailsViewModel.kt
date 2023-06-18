package com.example.joblogger.screens.jobdetails

import androidx.lifecycle.ViewModel
import com.example.joblogger.local.dao.JobsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    val dao: JobsDao
) : ViewModel() {
    fun currJob(id: String) = dao.getById(id)
}