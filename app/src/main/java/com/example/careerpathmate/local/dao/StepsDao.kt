package com.example.careerpathmate.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.careerpathmate.local.model.JobStepEntity
import com.example.careerpathmate.local.model.StepStatus
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StepsDao : BaseDao<JobStepEntity> {
    @Query("SELECT * FROM JobSteps WHERE jobId==:jobId")
    abstract fun getStepsForJobId(jobId: String): Flow<List<JobStepEntity>>

    @Query("UPDATE jobsteps SET status=:newStatus WHERE id==:id")
    abstract suspend fun updateStatus(id: String, newStatus: StepStatus)
}