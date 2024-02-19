
object Libs {

    // versions
    private const val LIFECYCLE_VERSION = "2.7.0"
    private const val LEGACY_SUPPORT_VERSION = "1.0.0"
    private const val ROOM_VERSION = "2.6.1"
    private const val COROUTINES_VERSION = "1.6.4"
    private const val NAVIGATION_VERSION = "2.7.7"
    private const val RETROFIT_VERSION = "2.9.0"
    private const val GLIDE_VERSION = "4.15.0"
    private const val PALETTE_VERSION = "28.0.0"
    private const val DESIGN_VERSION = "28.0.0"
    private const val PREFERENCES_VERSION = "1.2.1"

    // lifecycle components
    internal const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    internal const val LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:$LIFECYCLE_VERSION"
    internal const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"
    internal const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
    internal const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:$LIFECYCLE_VERSION"

    // legacy support
    internal const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:$LEGACY_SUPPORT_VERSION"

    // room components
    internal const val ROOM_RUNTIME = "androidx.room:room-runtime:$ROOM_VERSION"
    internal const val ROOM_KTX = "androidx.room:room-ktx:$ROOM_VERSION"
    internal const val ROOM_KAPT = "androidx.room:room-compiler:$ROOM_VERSION"
    internal const val ROOM_RXJAVA2 = "androidx.room:room-rxjava2:$ROOM_VERSION"

    // coroutines components
    internal const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"

    // navigation components
    internal const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    internal const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

    // retrofit components
    internal const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    internal const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    internal const val RETROFIT_RXJAVA2_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:$RETROFIT_VERSION"

    // glide components
    internal const val GLIDE = "com.github.bumptech.glide:glide:$GLIDE_VERSION"

    // palette components
    internal const val PALETTE = "com.android.support:palette-v7:$PALETTE_VERSION"

    // design components
    internal const val DESIGN = "com.android.support:design:$DESIGN_VERSION"

    // preferences components
    internal const val PREFERENCES = "androidx.preference:preference-ktx:$PREFERENCES_VERSION"
}


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.kotlincountries"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlincountries"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // lifecycle implementations
    implementation(Libs.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Libs.LIFECYCLE_VIEWMODEL_COMPOSE)
    implementation(Libs.LIFECYCLE_LIVEDATA_KTX)
    implementation(Libs.LIFECYCLE_EXTENSIONS)
    kapt(Libs.LIFECYCLE_COMPILER)

    // legacy support implementations
    implementation(Libs.LEGACY_SUPPORT)

    // room implementations
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_KTX)
    kapt(Libs.ROOM_KAPT)
    implementation(Libs.ROOM_RXJAVA2)

    // coroutines implementations
    implementation(Libs.COROUTINES_ANDROID)

    // navigation implementations
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)

    // retrofit implementations
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_CONVERTER_GSON)
    implementation(Libs.RETROFIT_RXJAVA2_ADAPTER)

    // glide implementations
    implementation(Libs.GLIDE)

    // palette implementations
    implementation(Libs.PALETTE)

    // design implementations
    implementation(Libs.DESIGN)

    // preferences implementations
    implementation(Libs.PREFERENCES)

}