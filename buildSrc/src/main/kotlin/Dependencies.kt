object Dependencies {

    object Plugins {

        private const val GRADLE_VERSION = "7.1.0"
        private const val KOTLIN_VERSION = "1.6.10"

        const val GRADLE = "com.android.tools.build:gradle:$GRADLE_VERSION"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    }

    object Core {

        private const val CORE_VERSION = "1.7.0"
        private const val APP_COMPAT_VERSION = "1.4.1"
        private const val MATERIAL_VERSION = "1.5.0"

        const val CORE = "androidx.core:core-ktx:$CORE_VERSION"
        const val APP_COMPAT = "androidx.appcompat:appcompat:$APP_COMPAT_VERSION"
        const val MATERIAL = "com.google.android.material:material:$MATERIAL_VERSION"
    }

    object ViewBindingPropertyDelegate {

        private const val VERSION = "1.5.3"
        const val CORE = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:$VERSION"
    }

    object Navigation {

        private const val VERSION = "2.4.0"

        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"
        const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
    }

    object Shimmer {

        private const val VERSION = "0.5.0"
        const val CORE = "com.facebook.shimmer:shimmer:$VERSION"
    }

    object Network {

        private const val RETROFIT_VERSION = "2.9.0"
        private const val MOSHI_VERSION = "2.4.0"
        private const val OKHTTP_VERSION = "4.9.3"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val CONVERTER = "com.squareup.retrofit2:converter-moshi:$MOSHI_VERSION"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:$OKHTTP_VERSION"
        const val INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
    }

    object Dagger {

        private const val VERSION = "2.40.5"

        const val CORE = "com.google.dagger:dagger:$VERSION"
        const val COMPILER = "com.google.dagger:dagger-compiler:$VERSION"
    }

    object Room {

        private const val VERSION = "2.4.1"

        const val CORE = "androidx.room:room-runtime:$VERSION"
        const val COROUTINES = "androidx.room:room-ktx:$VERSION"
        const val KAPT = "androidx.room:room-compiler:$VERSION"
    }
}