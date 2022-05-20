plugins {
    kotlin("multiplatform") version "1.6.21"
    id("com.android.library")
    id("maven-publish")
}

val libraryVersion = "0.0.1"
var libraryGroup = "com.vivek"
var libraryArtifactId = "kmm-module"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
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
    publications {
        group = libraryGroup
        publications.withType<MavenPublication> {
            groupId = libraryGroup
            artifactId = libraryArtifactId
            version = libraryVersion
        }

        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/vivek-modi/kotlinmultiplatfromproject")
                credentials {
                    username = (System.getenv("GITHUB_USER") ?: project.properties["GITHUB_USER"]).toString()
                    password = (System.getenv("GITHUB_PERSONAL_ACCESS_TOKEN") ?: project.properties["GITHUB_PERSONAL_ACCESS_TOKEN"]).toString()
                }
            }
        }
    }
}