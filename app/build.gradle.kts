plugins {
    id(Plugins.application)
    id(Plugins.android)
    kotlin(Plugins.kapt)
}

android {
    namespace = Config.applicationId
    compileSdkVersion(Config.compileVersion)

    viewBinding {
        enable = true
    }

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
    implementation("androidx.activity:activity-ktx:1.7.2")

    // Dagger 2
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.daggerAndroid)
    kapt(Dependencies.Dagger.daggerCompiler)
    kapt(Dependencies.Dagger.daggerAnnotationProcessor)

    // Retrofit-Moshi
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.0")

    // RxJava
    implementation(Dependencies.RxJava.rxjava)
    implementation(Dependencies.RxJava.rxjavaAndroid)
    implementation(Dependencies.RxJava.rxjavaKotlin)
    implementation(Dependencies.RxJava.rxjavaAdapterJake)

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

kapt {
    correctErrorTypes = true
}