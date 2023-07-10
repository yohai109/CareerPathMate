package com.example.careerpathmate.screens.jobslist

import com.example.careerpathmate.local.dao.JobsDao
import com.example.careerpathmate.local.model.JobEntity
import com.example.careerpathmate.local.model.JobStatus
import com.example.careerpathmate.uimodels.JobUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobListRepo @Inject constructor(
    private val dao: JobsDao
) {
    val allJobs = dao.getAll().map {
        it.map {
            JobUiModel(it)
        }
    }

    suspend fun updateStatus(id: String, newStatus: JobStatus) = withContext(Dispatchers.IO) {
        dao.updateStatus(id, newStatus)
    }

    suspend fun deleteJobs(vararg id: String) = withContext(Dispatchers.IO) {
        dao.delete(*id.map {
            JobEntity(id = it, "", "", "")
        }.toTypedArray())
    }
}