plugins {
    id("com.android.application") version "8.6.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.8.5" apply false
    id ("org.sonarqube") version "6.0.1.5171"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}