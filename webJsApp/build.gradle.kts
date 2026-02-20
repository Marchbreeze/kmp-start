plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    js(IR) {
        outputModuleName.set("webJsApp")
        browser {
            commonWebpackConfig {
                outputFileName = "webJsApp.js"
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(projects.shared)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.html.js)
                implementation(libs.koin.core)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.js)
            }
        }
    }
}
