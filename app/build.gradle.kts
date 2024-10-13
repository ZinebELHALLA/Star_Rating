plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.starrate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.starrate"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {


    // For using View
    implementation(libs.core.ktx)// or the equivalent for your case

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

// recycle view et image circle
    implementation(libs.recyclerview)
    implementation(libs.circleimageview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
//glide dependencies
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    implementation (libs.recyclerview)
    implementation(libs.circleimageview)
    annotationProcessor (libs.compiler.v480)
}