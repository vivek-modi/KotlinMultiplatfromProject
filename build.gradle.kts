plugins {
    kotlin("multiplatform") version "1.6.21"
    id("com.android.library")
    id("maven-publish")
}

group = "me.vmodi"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }
    sourceSets {
        val commonMain by getting
        val androidMain by getting
    }
}

android {
    compileSdk = 21
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    @Suppress("UnstableApiUsage") compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

publishing {
    repositories {
        maven {
            name = "KotlinMultiplatfromProject"
            url = uri("https://maven.pkg.github.com/vivek-modi/kotlinmultiplatfromproject")
            credentials {
                username = "vivek-modi"
                password = "abc"
            }
        }
    }
    publications.withType<MavenPublication> {
        println(artifactId)
        artifactId = if (name == "KotlinMultiplatfromProject") {
            artifactId.toLowerCase()
        } else {
            "$artifactId-$name".toLowerCase()
        }
    }
}