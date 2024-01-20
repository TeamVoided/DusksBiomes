pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
        maven("https://maven.teamvoided.org/releases")
    }
}

rootProject.name = "DuskAutumnsWorldgen"