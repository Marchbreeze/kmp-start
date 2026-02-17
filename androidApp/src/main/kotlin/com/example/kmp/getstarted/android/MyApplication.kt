package com.example.kmp.getstarted.android

import android.app.Application
import com.example.kmp.shared.data.local.createDataStore
import com.example.kmp.shared.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                module {
                    single { createDataStore(this@MyApplication) }
                },
                sharedModule,
            )
        }
    }
}
