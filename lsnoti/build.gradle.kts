plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"

    `maven-publish`
}

android {
    namespace = "solutions.laxmi.lsnoti"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures { compose = true }

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    publishing {
        multipleVariants {
            allVariants() // allows publishing of both debug and release
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.material3)
    implementation(libs.androidx.animation.android)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.runtime)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("debug") {
                from(components["debug"])
                groupId = "com.github.vi5hnuu"
                artifactId = "ls-noti-debug"
                version = "1.0.0-SNAPSHOT"
                pom {
                    name.set("LsNoti Debug")
                    description.set("Debug variant for testing LsNoti.")
                }
            }

            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.vi5hnuu"
                artifactId = "ls-noti"
                version = "1.0.0"
                pom {
                    name.set("LsNoti")
                    description.set("Elegant Jetpack Compose snackbar stack library with swipe-to-dismiss.")
                }
            }
        }
    }
}

