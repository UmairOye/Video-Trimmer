plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

android {
    namespace = "com.redevrx.video_trimmer"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("com.vanniktech:android-image-cropper:4.5.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("com.intuit.sdp:sdp-android:1.1.0")
//    implementation("com.arthenica:ffmpeg-kit-min-gpl:6.0")

    ///
    implementation("androidx.media3:media3-exoplayer:1.1.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.1.1")
    implementation("androidx.media3:media3-ui:1.1.1")
    implementation("androidx.media3:media3-transformer:1.1.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


//afterEvaluate {
//    publishing {
//        publications {
//
//            // Creates a Maven publication called "release".
//            create<MavenPublication>("release") {
//
//                // Applies the component for the release build variant.
//                // NOTE : Delete this line code if you publish Native Java / Kotlin Library
//                from(components["release"])
//
//                // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
//                groupId = "com.github.redevrx"
//
//                // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
//                artifactId = "android_video_trimmer"
//
//                // Version Library Name (Example : "1.0.0")
//                version = "1.0.0"
//
//            }
//
//        }
//    }
//}