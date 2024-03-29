import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.5.6"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("org.teamvoided.iridium") version "3.1.9"

}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "Worldgen Whatever"
val modid: String by project

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository { maven("https://maven.terraformersmc.com/") }
        filter { includeGroup("com.terraformersmc") }
    }
    exclusiveContent {
        forRepository { maven("https://api.modrinth.com/maven") }
        filter { includeGroup("maven.modrinth") }
    }
    maven("https://teamvoided.org/releases")
    maven("https://maven.nucleoid.xyz")
}

modSettings {
    modId(modid)
    modName("Dusk Autumns Worldgen")

    entrypoint("main", "org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen::commonInit")
    entrypoint("client", "org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen::clientInit")
    entrypoint("fabric-datagen", "org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgenData")
    mixinFile("dusk_autumns_worldgen.mixins.json")
    accessWidener("dusk_autumns_worldgen.accesswidener")
}

val biolith: String by project
val polymer: String by project
val factorytools: String by project
val lithostitched: String by project
dependencies {

    modImplementation(include("com.terraformersmc", "biolith-fabric", biolith)) {
        exclude("com.github.glitchfiend")
    }

    modImplementation(include("org.teamvoided:reef:0.1.0")!!)

    modImplementation(include("eu.pb4", "polymer-core", polymer))
    modImplementation(include("eu.pb4", "polymer-blocks", polymer))
    modImplementation(include("eu.pb4", "polymer-resource-pack", polymer))
    modImplementation(include("eu.pb4", "polymer-virtual-entity", polymer))
    modImplementation(include("eu.pb4", "polymer-autohost", polymer))
    modImplementation(include("eu.pb4", "factorytools", factorytools))
    modImplementation(include("maven.modrinth", "biome-tag-villagers", "1.0.0"))
    modImplementation(include("maven.modrinth", "lithostitched", lithostitched))


}

loom {
    runs {
        create("DataGen") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${modid}")
            runDir("build/datagen")
        }

        create("TestWorld") {
            client()
            ideConfigGenerated(true)
            runDir("run")
            programArgs("--quickPlaySingleplayer", "test")
        }
    }
}

sourceSets["main"].resources.srcDir("src/main/generated")

tasks {
    val targetJavaVersion = 17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
}