package com.example.joblogger.screens.createstep

import com.example.joblogger.local.dao.StepsDao
import com.example.joblogger.local.model.JobStepEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateStepRepo @Inject constructor(
    private val stepsDao: StepsDao
) {
    suspend fun createStep(step: JobStepEntity) = withContext(Dispatchers.IO) {
        stepsDao.upsert(step)
    }
}