package com.example.kmp.shared

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.example.kmp.shared.ui.navigation.AppNavigation
import com.example.kmp.shared.ui.theme.AppTheme

@Composable
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
            }
            .build()
    }

    AppTheme {
        AppNavigation()
    }
}
