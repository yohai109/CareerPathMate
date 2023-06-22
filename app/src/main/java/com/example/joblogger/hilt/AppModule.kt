package com.example.joblogger.hilt

import android.app.Application
import androidx.room.Room
import com.example.joblogger.local.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): MainDatabase = Room.databaseBuilder(
        application,
        MainDatabase::class.java,
        "main_database"
    ).build()

    @Provides
    @Singleton
    fun provideJobsDao(db: MainDatabase) = db.jobsDao()

    @Provides
    @Singleton
    fun provideStepsDao(db: MainDatabase) = db.stepsDao()
}