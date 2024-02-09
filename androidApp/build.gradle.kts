plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.adnan.cmpclock.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.adnan.cmpclock.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(libs.versions.java.jdk.get().toInt())
    }
}

kotlin {
    androidTarget()
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(projects.shared)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }
    }
}