package com.example.kmp.shared.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(): DataStore<Preferences> =
    createDataStore(
        producePath = { DATA_STORE_FILE_NAME },
    )