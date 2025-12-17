plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.hideoutcafe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hideoutcafe"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.loading.button.android)
    implementation(libs.core.splashscreen)
    implementation(libs.pinview)

    implementation(libs.legacy.support.v4)
    implementation(libs.recyclerview)


    //slide show
    implementation(libs.material.v190)
    implementation (libs.glide)

    //progress dialog
//    implementation("com.github.TutorialsAndroid:KAlertDialog:v4.1")
//    implementation("io.github.tutorialsandroid:kalertdialog:20.4.8")
//    implementation ("com.github.TutorialsAndroid:progressx:v6.0.19")

    //button
    //implementation("com.cepheuen.elegant-number-button:lib:1.0.3")

    //circular image
    implementation (libs.circleimageview)
}