package com.yohai.careerpathmate.screens.createstep

import com.yohai.careerpathmate.local.dao.StepsDao
import com.yohai.careerpathmate.local.model.JobStepEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateStepRepo @Inject constructor(
    private val stepsDao: StepsDao
) {
    suspend fun createStep(step: JobStepEntity) = withContext(Dispatchers.IO) {
        stepsDao.upsert(step)
    }

    suspend fun getStepById(id:String) = withContext(Dispatchers.IO) {
        stepsDao.getStepById(id)
    }
}