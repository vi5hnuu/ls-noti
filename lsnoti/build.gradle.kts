plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
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
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.material3)
    implementation(libs.androidx.animation.android)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.runtime )
}

afterEvaluate {
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "androidx.compose" && requested.name == "compose-bom") {
                val minRequiredVersion = "2024.05.00"
                val usedVersion = requested.version ?: "unspecified"

                if (usedVersion != "unspecified" && usedVersion < minRequiredVersion) {
                    throw GradleException("""
                        ❌ ERROR: Your project is using Compose BOM $usedVersion
                        🚨 ls-noti requires Compose BOM ≥ $minRequiredVersion

                        👉 Please update your BOM:
                           implementation(platform("androidx.compose:compose-bom:$minRequiredVersion"))
                    """.trimIndent())
                }
            }
        }
    }
}
