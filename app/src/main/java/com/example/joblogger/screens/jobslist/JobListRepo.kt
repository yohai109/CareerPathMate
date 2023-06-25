package com.example.joblogger.screens.jobslist

import com.example.joblogger.local.dao.JobsDao
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.local.model.JobStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobListRepo @Inject constructor(
    private val dao: JobsDao
) {
    val allJobs = dao.getAll()

    suspend fun updateStatus(id: String, newStatus: JobStatus) = withContext(Dispatchers.IO) {
        dao.updateStatus(id, newStatus)
    }

    suspend fun deleteJobs(vararg id: String) = withContext(Dispatchers.IO) {
        dao.delete(*id.map {
            JobEntity(id = it, "", "", "")
        }.toTypedArray())
    }
}