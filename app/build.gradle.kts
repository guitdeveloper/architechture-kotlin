plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "br.com.gtb.architecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.gtb.architecture"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packagingOptions {
        resources {
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/LGPL2.1"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(project(":libraries:testcore"))
    implementation(project(":libraries:data"))
    implementation(project(":libraries:uicore"))
    implementation(project(":features:cash"))
    implementation(project(":features:spotlight"))
    implementation(project(":features:product"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.kapt.compiler)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.koin.viewmodel)
    implementation(libs.material)
//            implementation deps.storages.room.ktx
//            kapt deps.storages.room.kapt_compiler
}