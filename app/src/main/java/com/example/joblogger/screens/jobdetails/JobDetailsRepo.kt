package com.example.joblogger.screens.jobdetails

import com.example.joblogger.local.dao.JobsDao
import com.example.joblogger.local.dao.StepsDao
import javax.inject.Inject

class JobDetailsRepo @Inject constructor(
    private val jobsDao: JobsDao,
    private val stepsDao: StepsDao
) {
    fun currJob(id: String) = jobsDao.getById(id)

    fun steps(id: String) = stepsDao.getStepsForJobId(id)
}