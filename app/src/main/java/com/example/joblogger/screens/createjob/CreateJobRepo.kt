package com.example.joblogger.screens.createjob

import com.example.joblogger.local.dao.JobsDao
import com.example.joblogger.local.model.JobEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateJobRepo @Inject constructor(private val dao: JobsDao) {
    suspend fun save(newJob: JobEntity) = withContext(Dispatchers.IO) { dao.upsert(newJob) }
}