package com.example.careerpathmate.screens.createjob

import com.example.careerpathmate.local.dao.JobsDao
import com.example.careerpathmate.local.model.JobEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateJobRepo @Inject constructor(private val dao: JobsDao) {
    suspend fun save(newJob: JobEntity) = withContext(Dispatchers.IO) { dao.upsert(newJob) }

    fun getById(id:String) = dao.getById(id)

}