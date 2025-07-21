package org.example.kmpproject.di

import org.example.kmpproject.HomeViewModel
import org.example.kmpproject.data.TaskRepository
import org.example.kmpproject.data.TaskRepositoryImpl
import org.example.kmpproject.data.TaskStorage
import org.example.kmpproject.data.TaskStorageImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    single<TaskStorage> { TaskStorageImpl() }

    viewModel { HomeViewModel(get())  }
}