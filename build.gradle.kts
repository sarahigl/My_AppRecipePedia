plugins {
    id("com.android.application") version "8.1.4" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.8.3" apply false // Safe Args plugin pour Navigation
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}