plugins {
    id(Plugins.application)
    id(Plugins.android)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}