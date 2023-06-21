package com.example.joblogger.screens.jobdetails

import com.example.joblogger.local.dao.JobsDao
import javax.inject.Inject

class JobDetailsRepo @Inject constructor(
    private val dao: JobsDao
) {
    fun currJob(id: String) = dao.getById(id)
}