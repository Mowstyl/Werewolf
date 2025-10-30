plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("1.0.0")
}

gradle.extra["projectName"] = "Werewolf"
rootProject.name = gradle.extra["projectName"].toString().lowercase()
