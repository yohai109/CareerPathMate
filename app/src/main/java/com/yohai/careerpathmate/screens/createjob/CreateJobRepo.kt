package com.yohai.careerpathmate.screens.createjob

import com.yohai.careerpathmate.local.dao.JobsDao
import com.yohai.careerpathmate.local.model.JobEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateJobRepo @Inject constructor(private val dao: JobsDao) {
    suspend fun save(newJob: JobEntity) = withContext(Dispatchers.IO) { dao.upsert(newJob) }

    fun getById(id:String) = dao.getById(id)

}