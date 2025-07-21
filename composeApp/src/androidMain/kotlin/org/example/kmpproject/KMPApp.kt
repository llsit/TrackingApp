package org.example.kmpproject

import android.app.Application
import org.example.kmpproject.di.appModule
import org.koin.core.context.startKoin

class KMPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}