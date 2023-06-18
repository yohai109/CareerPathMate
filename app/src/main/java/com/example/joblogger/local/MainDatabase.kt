package com.example.joblogger.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.joblogger.local.dao.JobsDao
import com.example.joblogger.local.model.JobEntity

@Database(
    version = 1,
    entities = [JobEntity::class]
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun jobsDao() : JobsDao
}