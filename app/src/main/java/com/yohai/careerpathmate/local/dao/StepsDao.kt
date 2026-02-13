package com.yohai.careerpathmate.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.yohai.careerpathmate.local.model.JobStepEntity
import com.yohai.careerpathmate.local.model.StepStatus
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StepsDao : BaseDao<JobStepEntity> {
    @Query("SELECT * FROM JobSteps WHERE jobId==:jobId")
    abstract fun getStepsForJobId(jobId: String): Flow<List<JobStepEntity>>

    @Query("UPDATE jobsteps SET status=:newStatus WHERE id==:id")
    abstract suspend fun updateStatus(id: String, newStatus: StepStatus)

    @Query("SELECT * FROM JobSteps WHERE id==:id")
    abstract suspend fun getStepById(id: String): JobStepEntity
}