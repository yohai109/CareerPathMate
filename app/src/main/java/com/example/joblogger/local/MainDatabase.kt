package com.example.joblogger.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.joblogger.local.dao.JobsDao
import com.example.joblogger.local.dao.StepsDao
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.local.model.JobStepEntity
import com.example.joblogger.local.typeconverters.DateTypeConverter

@Database(
    version = 1,
    entities = [JobEntity::class, JobStepEntity::class],
    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(
    DateTypeConverter::class
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun jobsDao(): JobsDao

    abstract fun stepsDao(): StepsDao
}