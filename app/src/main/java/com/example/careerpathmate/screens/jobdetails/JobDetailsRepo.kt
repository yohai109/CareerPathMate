package com.example.careerpathmate.screens.jobdetails

import com.example.careerpathmate.local.dao.JobsDao
import com.example.careerpathmate.local.dao.StepsDao
import com.example.careerpathmate.local.model.JobStepEntity
import com.example.careerpathmate.local.model.StepLocation
import com.example.careerpathmate.local.model.StepStatus
import com.example.careerpathmate.uimodels.StepStatusUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

class JobDetailsRepo @Inject constructor(
    private val jobsDao: JobsDao,
    private val stepsDao: StepsDao
) {
    fun currJob(id: String) = jobsDao.getById(id)

    fun steps(id: String) = stepsDao.getStepsForJobId(id)

    suspend fun setStepStatus(id: String, newStatus: StepStatusUi) = withContext(Dispatchers.IO) {
        stepsDao.updateStatus(id, StepStatus.fromStepStatusUi(newStatus))
    }

    suspend fun deleteStep(id: String) = withContext(Dispatchers.IO) {
        stepsDao.delete(
            JobStepEntity(
                id = id,
                jobId = "",
                name = "",
                date = Calendar.getInstance(),
                location = StepLocation.OnSite,
                status = StepStatus.Current
            )
        )
    }
}