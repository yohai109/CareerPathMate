package com.example.joblogger.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg obj: T)

    @Delete
    suspend fun delete(vararg obj: T)
}