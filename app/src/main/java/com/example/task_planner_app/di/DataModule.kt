package com.example.task_planner_app.di

import android.content.Context
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


}