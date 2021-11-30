package com.koshake1.pulseapp.app

import android.app.Application
import com.koshake1.pulseapp.di.repositoryModule
import com.koshake1.pulseapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                repositoryModule,
                viewModelModule
            )
        }
    }
}