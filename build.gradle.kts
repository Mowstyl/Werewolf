import java.io.ByteArrayOutputStream


plugins {
    `java-library`
    alias(libs.plugins.shadowPlugin)
    alias(libs.plugins.generatePOMPlugin)
}


group = "us.rfsmassacre"
version = "1.7.5-SNAPSHOT"  // .replace("SNAPSHOT", getGitHash)
description = "WerewolfPlugin"

ext.set("projectName", gradle.extra["projectName"].toString())
maven.pom {
    name = gradle.extra["projectName"].toString()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
        vendor = JvmVendorSpec.ORACLE
    }
}

repositories {
    gradlePluginPortal {
        content {
            includeGroup("com.gradleup")
        }
    }
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
        content {
            includeGroup("io.papermc")
            includeGroup("io.papermc.paper")
            includeGroup("com.mojang")
            includeGroup("net.md-5")
        }
    }
    maven {
        url = uri("https://maven.enginehub.org/repo/")
        content {
            includeGroup("com.sk89q.worldedit")
            includeGroup("com.sk89q.worldguard")
        }
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-releases/")
        content {
            includeGroup("net.skinsrestorer")
            includeGroup("de.tr7zw")
            includeGroup("me.NoChance.PvPManager")
        }
    }
    maven {
        url = uri("https://nexus.clanjhoo.com/repository/maven-public/")
        content {
            includeGroup("com.clanjhoo")
        }
    }
    maven {
        url = uri("https://jitpack.io")
        content {
            includeGroupByRegex("com\\.github\\..*")
        }
    }
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly(libs.papermc.paperapi)
    compileOnly(libs.sk89q.worldedit.core) {
        isTransitive = false
    }
    compileOnly(libs.sk89q.worldedit.bukkit) {
        isTransitive = false
    }
    compileOnly(libs.sk89q.worldguard.core) {
        isTransitive = false
    }
    compileOnly(libs.sk89q.worldguard.bukkit) {
        isTransitive = false
    }
    compileOnly(libs.skinsrestorer.api) {
        isTransitive = false
    }
    compileOnly(libs.techfortress.griefprevention) {
        isTransitive = false
    }
    compileOnly(libs.nochance.pvpmanager) {
        isTransitive = false
    }
    compileOnly(libs.clanjhoo.vampire) {
        isTransitive = false
    }
    compileOnly(libs.milkbowl.vaultapi) {
        isTransitive = false
    }
    compileOnly(libs.jetbrains.annotations) {
        isTransitive = false
    }
    compileOnly(libs.lombok.annotations) {
        isTransitive = false
    }
    annotationProcessor(libs.lombok.annotations) {
        isTransitive = false
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks {
    processResources {
        filesMatching("**/plugin.yml") {
            expand( project.properties )
        }
    }
}

