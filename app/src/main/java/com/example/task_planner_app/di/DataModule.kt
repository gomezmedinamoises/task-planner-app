package com.example.task_planner_app.di

import android.content.Context
import androidx.room.Room
import com.example.task_planner_app.repository.AppDatabase
import com.example.task_planner_app.repository.model.dao.TaskDao
import com.example.task_planner_app.repository.model.dao.UserDao
import com.example.task_planner_app.storage.LocalStorage
import com.example.task_planner_app.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesStorage(@ApplicationContext context: Context): Storage {
        return LocalStorage(context)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database-users"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }
}