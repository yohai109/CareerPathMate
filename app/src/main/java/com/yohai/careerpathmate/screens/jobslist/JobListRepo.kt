package com.yohai.careerpathmate.screens.jobslist

import com.yohai.careerpathmate.local.dao.JobsDao
import com.yohai.careerpathmate.local.model.JobEntity
import com.yohai.careerpathmate.local.model.JobStatus
import com.yohai.careerpathmate.uimodels.JobUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobListRepo @Inject constructor(
    private val dao: JobsDao
) {
    val allJobs = dao.getAll().map { jobsList ->
        jobsList.map { job ->
            JobUiModel(job)
        }
    }

    suspend fun updateStatus(id: String, newStatus: JobStatus) = withContext(Dispatchers.IO) {
        dao.updateStatus(id, newStatus)
    }

    suspend fun deleteJobs(vararg id: String) = withContext(Dispatchers.IO) {
        dao.delete(*id.map { jobId ->
            JobEntity(id = jobId, "", "", "")
        }.toTypedArray())
    }
}