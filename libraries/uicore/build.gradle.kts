plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "br.com.gtb.libraries.uicore"
    compileSdk = 34

    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures{
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.utilcodex)

    testImplementation(project(":libraries:testcore"))
}