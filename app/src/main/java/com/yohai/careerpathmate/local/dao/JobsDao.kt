package com.yohai.careerpathmate.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.yohai.careerpathmate.local.model.JobEntity
import com.yohai.careerpathmate.local.model.JobStatus
import kotlinx.coroutines.flow.Flow

@Dao
abstract class JobsDao : BaseDao<JobEntity> {
    @Query("SELECT * FROM jobs")
    abstract fun getAll(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE id==:id")
    abstract fun getById(id: String): Flow<JobEntity>

    @Query("UPDATE jobs SET status=:newStatus WHERE id==:id")
    abstract suspend fun updateStatus(id: String, newStatus: JobStatus)
}