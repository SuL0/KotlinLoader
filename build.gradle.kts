import java.util.function.*

version = "1.6.10"
plugins {
    kotlin("jvm") version "1.6.10" apply true
    id("kr.entree.spigradle") version "2.2.3" apply false
    id("kr.entree.spigradle.bungee") version "2.2.3" apply false
}

group = "kr.sul"
val rootName = "KotlinLoader"
val pluginStorage = "C:/MC-Development/PluginStorage"
val bukkitCopyDestination = "C:/MC-Development/마인즈서버/plugins"
val bungeeCopyDestination = "C:/MC-Development/Waterfall/plugins"


val getPluginName: (Project) -> String = { givenProject ->
    "$rootName-${givenProject.name}_S-${givenProject.version}.jar"
}
subprojects {
    // plugin= 으로 파라미터 대상을 지정하거나 from=null
    apply(null, "org.jetbrains.kotlin.jvm")
//    apply(plugin = "kotlin")
    ext {
        set("rootName", "KotlinLoader")
        set("version", "1.6.10")
        set("pluginStorage", "C:/MC-Development/PluginStorage")
        set("bukkitDestination", "C:/MC-Development/마인즈서버/plugins")
        set("bungeeDestination", "C:/MC-Development/Waterfall/plugins")
    }
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("http://repo.citizensnpcs.co/")
        maven("https://jitpack.io")
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    }

    tasks {
        compileJava.get().options.encoding = "UTF-8"
        compileKotlin.get().kotlinOptions.jvmTarget = "1.8"
        compileTestKotlin.get().kotlinOptions.jvmTarget = "1.8"

        jar {
            archiveFileName.set(getPluginName.invoke(project))
            val destiny = when (project.name) {
                "Bungee" -> bungeeCopyDestination
                "Bukkit" -> bukkitCopyDestination
                else -> throw Exception()
            }
            destinationDirectory.set(file(destiny))
            from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        }
    }
}

project(":Bukkit").run {
    apply(plugin = "kr.entree.spigradle")
    dependencies {
        compileOnly("net.citizensnpcs:citizens-main:2.0.29-SNAPSHOT")
    }
}

project(":Bungee").run {
    apply(plugin = "kr.entree.spigradle.bungee")
}