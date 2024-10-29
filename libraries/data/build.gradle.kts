plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "br.com.gtb.libraries.data"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.kapt.compiler)
    implementation(libs.gson)
    implementation(libs.koin.scope)
    implementation(libs.material)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)

    testImplementation(project(":libraries:testcore"))
}