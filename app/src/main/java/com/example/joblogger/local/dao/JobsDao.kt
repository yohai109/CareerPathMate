package com.example.joblogger.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.joblogger.local.model.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class JobsDao : BaseDao<JobEntity> {
    @Query("SELECT * FROM jobs")
    abstract fun getAll(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE id==:id")
    abstract fun getById(id: String): Flow<JobEntity>
}