package com.yohai.careerpathmate.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yohai.careerpathmate.local.dao.JobsDao
import com.yohai.careerpathmate.local.dao.StepsDao
import com.yohai.careerpathmate.local.model.JobEntity
import com.yohai.careerpathmate.local.model.JobStepEntity
import com.yohai.careerpathmate.local.typeconverters.DateTypeConverter

@Database(
    version = 1,
    entities = [JobEntity::class, JobStepEntity::class],
    autoMigrations = [
    ]
)
@TypeConverters(
    DateTypeConverter::class
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun jobsDao(): JobsDao

    abstract fun stepsDao(): StepsDao
}