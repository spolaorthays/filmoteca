object Dependencies {

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
        const val daggerAnnotationProcessor =
            "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    }

    object RxJava {
            const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
            const val rxjavaAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxjavaAndroid}"
            const val rxjavaKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxjavaKotlin}"
            //const val RXJAVA_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RXJAVA_ADAPTER}"
            const val rxjavaAdapterJake = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxjavaAdapterJake}"
    }
}