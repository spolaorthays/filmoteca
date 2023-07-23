object Dependencies {

    object Modules {
        const val movie = ":movie"
    }

    object Android {
        const val androidKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val materialDesign = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerAndroid =
            "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
        const val daggerAnnotationProcessor =
            "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    }

    object Retrofit {
        const val retrofit ="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitAdapterRx ="com.squareup.retrofit2:adapter-rxjava:${Versions.retrofit}"
        const val converterMoshi ="com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val moshi ="com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val loggingInterceptor ="com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object RxJava {
        const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
        const val rxjavaAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxjavaAndroid}"
        const val rxjavaKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxjavaKotlin}"
        //const val RXJAVA_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RXJAVA_ADAPTER}"
        const val rxjavaAdapterJake =
            "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxjavaAdapterJake}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.jUnit}"
        const val testJunit = "androidx.test.ext:junit:${Versions.testJunit}"
        const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"
    }
}