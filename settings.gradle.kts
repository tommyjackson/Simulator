pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "Simulator"

include(":data")
include(":theming")
include(":extensions")
include(":components")
include(":android")
include(":desktop")
