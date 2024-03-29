import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.30"
    id("org.jmailen.kotlinter") version "3.5.1"
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
    id("net.minecrell.plugin-yml.bukkit") version "0.5.0" apply false
    id("com.github.ben-manes.versions") version "0.39.0"
}

allprojects {
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "com.github.ben-manes.versions")

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven(url = "https://oss.sonatype.org/content/groups/public/")
    }

    val shadowImplementation: Configuration by configurations.creating
    configurations["implementation"].extendsFrom(shadowImplementation)

    dependencies {
        shadowImplementation(kotlin("stdlib"))

        val minecraftVersion = when (17) {
            8 -> "1.8.8"
            9 -> "1.9.4"
            10 -> "1.10.2"
            11 -> "1.11.2"
            12 -> "1.12.2"
            13 -> "1.13.2"
            14 -> "1.14.4"
            15 -> "1.15.2"
            16 -> "1.16.5"
            17 -> "1.17.1"
            else -> error("Unsupported Version")
        }

        implementation("org.spigotmc:spigot-api:$minecraftVersion-R0.1-SNAPSHOT")
    }

    tasks.withType<ShadowJar> {
        configurations = listOf(shadowImplementation)
        archiveClassifier.set("")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

project(":api") {
    apply(plugin = "net.minecrell.plugin-yml.bukkit")
}
