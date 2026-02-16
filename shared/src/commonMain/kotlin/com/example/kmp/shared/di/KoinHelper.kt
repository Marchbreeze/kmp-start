package com.example.kmp.shared.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(dataStore: DataStore<Preferences>) {
    startKoin {
        modules(
            module {
                single<DataStore<Preferences>> { dataStore }
            },
            sharedModule,
        )
    }
}
