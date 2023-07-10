package com.example.careerpathmate.screens.createstep

import com.example.careerpathmate.local.dao.StepsDao
import com.example.careerpathmate.local.model.JobStepEntity
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