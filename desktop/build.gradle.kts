import org.jetbrains.compose.compose

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose")
}

group = "com.tjackapps.simulator"
version = "1.0.0"

dependencies {
  implementation(project(":components"))
  implementation(project(":data"))
  implementation(project(":extensions"))
  implementation("org.openrndr:openrndr-math:0.3.58")
  implementation(compose.desktop.currentOs)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}

compose.desktop {
  application {
    mainClass = "com.tjackapps.simulator.desktop.simulation.SimulatorLauncher"
  }
}
