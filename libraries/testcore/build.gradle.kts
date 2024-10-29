plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.junit5)
}

buildscript {
    dependencies {
        classpath(libs.test.unitary.junit.v5.plugin)
    }
}

android {
    namespace = "br.com.gtb.libraries.testcore"
    compileSdk = 34

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        // execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
        junitPlatform {
            instrumentationTests.includeExtensions.set(true)
        }
    }
}

dependencies {
    implementation(libs.test.unitary.coroutines)
    implementation(libs.test.unitary.core.testing)
    implementation(libs.test.unitary.junit.v4.core)
    implementation(libs.test.unitary.junit.v5.core)

    //UI Test
    androidTestImplementation(libs.test.ui.runner)
    androidTestImplementation(libs.test.ui.espresso.core)
    androidTestImplementation(libs.test.ui.espresso.contrib)
    androidTestImplementation(libs.test.ui.junit5.core)
    androidTestImplementation(libs.test.ui.rules)
    androidTestImplementation(libs.test.ui.mockk.android)
    androidTestImplementation(libs.test.unitary.assertj.core)
    androidTestImplementation(libs.test.unitary.core.testing)
    androidTestImplementation(libs.test.unitary.junit.v4.core)
    androidTestImplementation(libs.test.unitary.junit.v5.core)
    androidTestImplementation(libs.test.unitary.koin.test)
    androidTestImplementation(libs.test.unitary.mockito.kotlin)
    androidTestRuntimeOnly(libs.test.ui.junit5.runner)

    //Unitary Test
    testImplementation(libs.test.unitary.assertj.core)
    testImplementation(libs.test.unitary.core.testing)
    testImplementation(libs.test.unitary.coroutines)
    testImplementation(libs.test.unitary.junit.v4.core)
    testImplementation(libs.test.unitary.junit.v5.core)
    testImplementation(libs.test.unitary.junit.v5.params)
    testImplementation(libs.test.unitary.koin.test)
    testImplementation(libs.test.unitary.mockito.kotlin)
    testImplementation(libs.test.unitary.mockito.inline)
    testImplementation(libs.test.unitary.mockk)
    testImplementation(libs.test.unitary.room.testing)
    testRuntimeOnly(libs.test.unitary.junit.v4.engine)
    testRuntimeOnly(libs.test.unitary.junit.v5.engine)
}