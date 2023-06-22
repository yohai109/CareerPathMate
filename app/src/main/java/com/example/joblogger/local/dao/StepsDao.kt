package com.example.joblogger.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.joblogger.local.model.JobStepEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StepsDao : BaseDao<JobStepEntity> {
    @Query("SELECT * FROM JobSteps WHERE jobId==:jobId")
    abstract fun getStepsForJobId(jobId: String): Flow<List<JobStepEntity>>
}