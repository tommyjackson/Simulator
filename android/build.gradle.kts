import org.jetbrains.compose.compose

plugins {
  id("com.android.application")
  kotlin("android")
  id("org.jetbrains.compose")
}

android {
  compileSdk = AndroidSdk.apiLevel

  defaultConfig {
    minSdk = AndroidSdk.minSdk
    targetSdk = AndroidSdk.target
    buildToolsVersion = AndroidSdk.buildTools
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {
  api(project(":components"))
  api(project(":data"))

  implementation(Dependencies.kotlin)
  implementation(Dependencies.AndroidX.core)
  implementation(Dependencies.AndroidX.appcompat)
  implementation(Dependencies.AndroidX.material)

  implementation(compose.ui)
  implementation(compose.material)
  implementation(compose.preview)
  implementation(Dependencies.Compose.activity)
  implementation(Dependencies.Compose.navigation)

  implementation(Dependencies.Accompanist.uiController)
  implementation(Dependencies.Accompanist.coil)

  implementation(Dependencies.AndroidX.viewModel)
}