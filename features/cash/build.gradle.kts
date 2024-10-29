plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "br.com.gtb.features.cash"
    compileSdk = 34

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":libraries:uicore"))
    implementation(project(":libraries:data"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.kapt.compiler)
    implementation(libs.koin.scope)
    implementation(libs.koin.viewmodel)
    implementation(libs.material)
    implementation(libs.picasso)
    implementation(libs.retrofit.core)

    androidTestImplementation(project(":libraries:testcore"))
    testImplementation(project(":libraries:testcore"))
}