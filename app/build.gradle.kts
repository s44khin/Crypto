plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APP_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.Core.CORE)
    implementation(Dependencies.Core.APP_COMPAT)
    implementation(Dependencies.Core.MATERIAL)

    implementation(Dependencies.ViewBindingPropertyDelegate.CORE)

    implementation(Dependencies.Navigation.FRAGMENT)
    implementation(Dependencies.Navigation.UI)

    implementation(Dependencies.Shimmer.CORE)

    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.CONVERTER)
    implementation(Dependencies.Network.OKHTTP)
    implementation(Dependencies.Network.INTERCEPTOR)

    implementation(Dependencies.Dagger.CORE)
    kapt(Dependencies.Dagger.COMPILER)

    implementation(Dependencies.Room.CORE)
    implementation(Dependencies.Room.COROUTINES)
    kapt(Dependencies.Room.KAPT)
}