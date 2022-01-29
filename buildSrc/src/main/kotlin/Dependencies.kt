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
}