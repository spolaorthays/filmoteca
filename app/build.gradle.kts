plugins {
    id(Plugins.application)
    id(Plugins.android)
    kotlin(Plugins.kapt)
}

android {
    namespace = Config.applicationId
    compileSdk = Config.compileVersion

    viewBinding {
        enable = true
    }

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetVersion
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

    implementation(project(Dependencies.Modules.movie))

    // General
    implementation(Dependencies.Android.androidKtx)
    implementation(Dependencies.Android.androidAppCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.activityKtx)

    // Dagger 2
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.daggerAndroid)
    kapt(Dependencies.Dagger.daggerCompiler)
    kapt(Dependencies.Dagger.daggerAnnotationProcessor)

    // Retrofit-Moshi
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitAdapterRx)
    implementation(Dependencies.Retrofit.converterMoshi)
    implementation(Dependencies.Retrofit.moshi)
    implementation(Dependencies.Retrofit.loggingInterceptor)

    // RxJava
    implementation(Dependencies.RxJava.rxjava)
    implementation(Dependencies.RxJava.rxjavaAndroid)
    implementation(Dependencies.RxJava.rxjavaKotlin)
    implementation(Dependencies.RxJava.rxjavaAdapterJake)

    // Test
    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.testJunit)
    androidTestImplementation(Dependencies.Tests.testEspresso)
}

kapt {
    correctErrorTypes = true
}