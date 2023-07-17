plugins {
    id(Plugins.application)
    id(Plugins.android)
    kotlin(Plugins.kapt)
}

android {
    namespace = Config.applicationId
    compileSdkVersion(Config.compileVersion)

    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetVersion)
        versionCode = Config.versionCode
        versionName = Config.versionsName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName(Config.release) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Config.proguardtxt),
                Config.proguardrules
            )
        }
        getByName(Config.debug) {
            applicationIdSuffix = Config.dotDebug
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(Config.jvmTarget)
    }
}

dependencies {

    // General
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    // Dagger 2
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.daggerAndroid)
    kapt(Dependencies.Dagger.daggerCompiler)
    kapt(Dependencies.Dagger.daggerAnnotationProcessor)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.0")
    implementation("com.squareup.retrofit2:adapter-rxjava:2.9.0")

    // RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

kapt {
    correctErrorTypes = true
}