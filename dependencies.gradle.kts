ext {
    projectSdk = [
            buildTools: "35.0.0",
            compile   : 35,
            min       : 22,
            target    : 35,
    ]

    versions = [
            android_doubletaplikeview          : "0.2.0",
            androidXJunit                      : "1.1.2",
            androidXLegacy                     : "1.0.0",
            androidXTestCore                   : "1.3.0",
            anko                               : "0.10.4",
            antlr                              : "4.7.1",
            accompanistGlide                   : "0.7.1",
            appcompat                          : "1.3.0",
            assertj_core                       : "3.13.2",
            build_gradle                       : '7.0.2',
            circleimageview                    : "3.1.0",
            compose                            : "1.0.0-beta04",
            constraintlayout                   : "2.0.4",
            core_testing                       : "2.1.0",
            core_ktx                           : "1.5.0",
            coroutines                         : "1.4.3",
            customactivityoncrash              : "2.3.0",
            dagger                             : "2.23.2",
            detekt                             : "1.17.1",
            espresso                           : "3.3.0",
            ext_junit                          : "1.1.2",
            ffmpeg_kit_min_gpl                 : "4.4.LTS",
            firebase_bom                       : "26.5.0",
            firebase_dynamic_links_ktx         : "20.1.0",
            firebase_config_ktx                : "21.0.0",
            firebase_core                      : "19.0.0",
            firebase_crashlytics               : "18.1.0",
            firebase_crashlytics_gradle        : "2.7.1",
            firebase_inappmessaging_display_ktx: "20.0.0",
            firebase_messaging                 : "21.1.0",
            firebase_perf                      : "20.0.1",
            firebase_perf_plugin               : "1.4.0",
            fragment_ktx                       : "1.3.2",
            fragment_test                      : "1.4.0",
            google_services                    : "4.3.5",
            gson                               : "2.8.6",
            jacoco                             : "0.8.9",
            java                               : JavaVersion.VERSION_17,
            jvmTarget                          : "17",
            junit                              : "4.13.2",
            junit5                             : "5.7.1",
            junit5_instrumentation             : "1.2.2",
            junit5_plugin                      : "1.7.1.1",
            koin                               : "2.2.2",
            kotlin                             : "1.9.24",
            kotlinx_coroutines_core            : "1.4.3",
            kotlinx_coroutines_test            : "1.5.0",
            leakcanary                         : "2.7",
            legacy_support_v4                  : "1.0.0",
            lifecycle                          : "2.3.1",
            lifecycle_extensions               : "2.2.0",
            material                           : "1.3.0",
            mockito                            : "2.1.0",
            mockito_core                       : "2.27.0",
            mockito_inline                     : "2.13.0",
            mockk                              : "1.11.0",
            moshi                              : "1.8.0",
            navigation                         : "2.3.5",
            okhttp                             : "4.4.0",
            okhttp3_integration                : "4.9.0",
            paging_runtime_ktx                 : "3.0.0",
            picasso                            : "2.71828",
            play_services_vision               : "20.1.3",
            playcore                           : "1.10.0",
            recyclerview                       : "1.2.1",
            retrofit                           : "2.9.0",
            retrofit2_synchronous_adapter      : "0.4.0",
            retrofit2_adapter_rxjava           : "2.5.0",
            retrofit2_coroutines_adapter       : "0.9.2",
            room                               : "2.3.0",
            rxandroid                          : "2.1.1",
            rxjava                             : "2.2.17",
            test                               : "1.3.0",
            utilcodex                          : "1.30.0",
            viewModel                          : "2.3.1",
            viewpager2                         : "1.0.0",
            work_runtime                       : "2.5.0",
    ]

    pluginClassPath = [
            build_gradle   : "com.android.tools.build:gradle:$versions.build_gradle",
            detekt         : "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$versions.detekt",
            firebase       : [
                    perf       : "com.google.firebase:perf-plugin:$versions.firebase_perf_plugin",
                    crashlytics: "com.google.firebase:firebase-crashlytics-gradle:$versions.firebase_crashlytics_gradle",
            ],
            google_services: "com.google.gms:google-services:$versions.google_services",
            jacoco         : "org.jacoco:org.jacoco.core:$versions.jacoco",
            junit5         : "de.mannodermaus.gradle.plugins:android-junit5:$versions.junit5_plugin",
            kotlin         : [
                    extensions: "org.jetbrains.kotlin:kotlin-android-extensions:$versions.kotlin",
                    gradle    : "org.jetbrains.kotlin:kotlin-gradle-plugin:$versions.kotlin",
            ],
            navigation     : "androidx.navigation:navigation-safe-args-gradle-plugin:$versions.navigation",
    ]

    deps = [
            asynchronous  : [
                    kotlinx_coroutines_android: "org.jetbrains.kotlinx:kotlinx-coroutines-android:$versions.kotlinx_coroutines_core",
                    kotlinx_coroutines_core: "org.jetbrains.kotlinx:kotlinx-coroutines-core:$versions.kotlinx_coroutines_core",
                    rxandroid              : "io.reactivex.rxjava2:rxandroid:$versions.rxandroid",
                    rxjava                 : "io.reactivex.rxjava2:rxjava:$versions.rxjava",
                    work_runtime           : "androidx.work:work-runtime:$versions.work_runtime",
            ],
            component     : [
                    appcompat            : "androidx.appcompat:appcompat:$versions.appcompat",
                    constraintlayout     : "androidx.constraintlayout:constraintlayout:$versions.constraintlayout",
                    customactivityoncrash: "cat.ereza:customactivityoncrash:$versions.customactivityoncrash",
                    material             : "com.google.android.material:material:$versions.material",
                    recyclerview         : "androidx.recyclerview:recyclerview:$versions.recyclerview",
            ],
            debug         : [
                    leakcanary: "com.squareup.leakcanary:leakcanary-android:$versions.leakcanary"
            ],
            firebase      : [
                    analytics_ktx             : "com.google.firebase:firebase-analytics-ktx",
                    bom                       : "com.google.firebase:firebase-bom:$versions.firebase_bom",
                    config_ktx                : "com.google.firebase:firebase-config-ktx:$versions.firebase_config_ktx",
                    core                      : "com.google.firebase:firebase-core:$versions.firebase_core",
                    crashlytics               : "com.google.firebase:firebase-crashlytics:$versions.firebase_crashlytics",
                    crashlytics_ndk           : "com.google.firebase:firebase-crashlytics-ndk:$versions.firebase_crashlytics",
                    dynamic_links_ktx         : "com.google.firebase:firebase-dynamic-links-ktx:$versions.firebase_dynamic_links_ktx",
                    inappmessaging_display_ktx: "com.google.firebase:firebase-inappmessaging-display-ktx:$versions.firebase_inappmessaging_display_ktx",
                    messaging                 : "com.google.firebase:firebase-messaging:$versions.firebase_messaging",
                    perf                      : "com.google.firebase:firebase-perf:$versions.firebase_perf",
            ],
            google_play   : [
                    core                : "com.google.android.play:core:$versions.playcore",
                    play_services_vision: "com.google.android.gms:play-services-vision:$versions.play_services_vision",
            ],
            http          : [
                    okhttp  : [
                            core               : "com.squareup.okhttp3:okhttp:$versions.okhttp",
                            logging_interceptor: "com.squareup.okhttp3:logging-interceptor:$versions.okhttp",
                            mockwebserver: "com.squareup.okhttp3:mockwebserver:$versions.okhttp",
                    ],
                    retrofit: [
                            core               : "com.squareup.retrofit2:retrofit:$versions.retrofit",
                            adapter_rxjava     : "com.squareup.retrofit2:adapter-rxjava:$versions.retrofit2_adapter_rxjava",
                            converter_gson     : "com.squareup.retrofit2:converter-gson:$versions.retrofit",
                            converter_scalars  : "com.squareup.retrofit2:converter-scalars:$versions.retrofit",
                            coroutines_adapter : "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$versions.retrofit2_coroutines_adapter",
                            synchronous_adapter: "com.jaredsburrows.retrofit:retrofit2-synchronous-adapter:$versions.retrofit2_synchronous_adapter",
                    ],
            ],
            image         : [
                    circleimageview               : "de.hdodenhof:circleimageview:$versions.circleimageview",
                    picasso                       : "com.squareup.picasso:picasso:$versions.picasso",
            ],
            injection     : [
                    koin: [
                            android    : "org.koin:koin-android:$versions.koin",
                            compose    : "org.koin:koin-androidx-compose:$versions.koin",
                            core       : "org.koin:koin-core:$versions.koin",
                            fragment   : "org.koin:koin-androidx-fragment:$versions.koin",
                            scope      : "org.koin:koin-androidx-scope:$versions.koin",
                            viewmodel  : "org.koin:koin-androidx-viewmodel:$versions.koin",
                            workmanager: "org.koin:koin-androidx-workmanager:$versions.koin",
                    ],
                    dagger: [
                        core    : "com.google.dagger:dagger:$versions.dagger",
                        compiler: "com.google.dagger:dagger-compiler:$versions.dagger",
                    ],
            ],
            lifecycle     : [
                    common_java8 : "androidx.lifecycle:lifecycle-common-java8:$versions.lifecycle",
                    extensions   : "androidx.lifecycle:lifecycle-extensions:$versions.lifecycle_extensions",
                    livedata_ktx : "androidx.lifecycle:lifecycle-livedata-ktx:$versions.lifecycle",
                    runtime_ktx  : "androidx.lifecycle:lifecycle-runtime-ktx:$versions.lifecycle",
                    viewmodel_ktx: "androidx.lifecycle:lifecycle-viewmodel-ktx:$versions.lifecycle",
            ],
            navigation    : [
                    dynamic_features_fragment: "androidx.navigation:navigation-dynamic-features-fragment:$versions.navigation",
                    fragment_ktx             : "androidx.navigation:navigation-fragment-ktx:$versions.navigation",
                    paging_runtime_ktx       : "androidx.paging:paging-runtime-ktx:$versions.paging_runtime_ktx",
                    ui_ktx                   : "androidx.navigation:navigation-ui-ktx:$versions.navigation",
                    viewpager2               : "androidx.viewpager2:viewpager2:$versions.viewpager2",
            ],
            serialization : [
                    gson: "com.google.code.gson:gson:$versions.gson",
            ],
            storages      : [
                    room                 : [
                            runtime      : "androidx.room:room-runtime:$versions.room",
                            kapt_compiler: "androidx.room:room-compiler:$versions.room",
                            ktx          : "androidx.room:room-ktx:$versions.room",
                    ]
            ],
            test          : [
                    ui     : [
                            core_ktx     :  "androidx.test:core-ktx:$versions.core_ktx",
                            espresso     : [
                                    core   : "androidx.test.espresso:espresso-core:$versions.espresso",
                                    contrib: "androidx.test.espresso:espresso-contrib:$versions.espresso",
                            ],
                            fragment     : "androidx.fragment:fragment-testing:$versions.fragment_test",
                            ext_junit    : "androidx.test.ext:junit:$versions.ext_junit",
                            junit5       : [
                                    core  : "de.mannodermaus.junit5:android-test-core:$versions.junit5_instrumentation",
                                    runner: "de.mannodermaus.junit5:android-test-runner:$versions.junit5_instrumentation",
                            ],
                            mockk_android: "io.mockk:mockk-android:$versions.mockk",
                            rules        : "androidx.test:rules:$versions.test",
                            runner       : "androidx.test:runner:$versions.test",
                    ],
                    unitary: [
                            assertj_core: "org.assertj:assertj-core:$versions.assertj_core",
                            core_testing: "androidx.arch.core:core-testing:$versions.core_testing",
                            coroutines  : "org.jetbrains.kotlinx:kotlinx-coroutines-test:$versions.kotlinx_coroutines_test",
                            junit       : [
                                    v4: [
                                            core  : "junit:junit:$versions.junit",
                                            engine: "org.junit.vintage:junit-vintage-engine:$versions.junit5",
                                    ],
                                    v5: [
                                            core  : "org.junit.jupiter:junit-jupiter-api:$versions.junit5",
                                            engine: "org.junit.jupiter:junit-jupiter-engine:$versions.junit5",
                                            params: "org.junit.jupiter:junit-jupiter-params:$versions.junit5",
                                            plugin: "de.mannodermaus.gradle.plugins:android-junit5:$versions.junit5_plugin"
                                    ],
                            ],
                            koin_test   : "org.koin:koin-test:$versions.koin",
                            mockito     : [
                                    core: "org.mockito:mockito-core:$versions.mockito_core",
                                    inline: "org.mockito:mockito-inline:$versions.mockito_inline",
                                    kotlin: "com.nhaarman.mockitokotlin2:mockito-kotlin:$versions.mockito",
                            ],
                            mockk       : "io.mockk:mockk:$versions.mockk",
                            room_testing: "androidx.room:room-testing:$versions.room",
                    ],
            ],
            util          : [
                    anko_commons      : "org.jetbrains.anko:anko-commons:$versions.anko",
                    antlr             : [
                            runtime: "org.antlr:antlr4-runtime:$versions.antlr",
                            tool   : "org.antlr:antlr4-tool:$versions.antlr",
                    ],
                    core_ktx          : "androidx.core:core-ktx:$versions.core_ktx",
                    fragment_ktx      : "androidx.fragment:fragment-ktx:$versions.fragment_ktx",
                    kotlin_stdlib_jdk7: "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$versions.kotlin",
                    kotlin_stdlib_jdk8: "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$versions.kotlin",
                    legacy_support_v4 : "androidx.legacy:legacy-support-v4:$versions.legacy_support_v4",
                    utilcodex         : "com.blankj:utilcodex:$versions.utilcodex",
            ],
    ]

}