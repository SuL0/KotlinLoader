version = "1.4.10"
plugins {
    kotlin("jvm") version "1.6.10"
//    kotlin("plugin.serialization") version "1.5.20"  단순 라이브러리용 fat jar 만드는 용도로는 plugins 에 추가할 필요 없는 듯?
    id("kr.entree.spigradle") version "2.2.3"
}
group = "kr.sul"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

val pluginStorage = "C:/MC-Development/PluginStorage"
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
}

spigot {
    authors = listOf("SuL")
    apiVersion = "1.12"
    version = project.version.toString()
    description = "Kotlin STD Library"
    load = kr.entree.spigradle.data.Load.STARTUP
}


tasks.compileJava.get().options.encoding = "UTF-8"

tasks {
    compileKotlin.get().kotlinOptions.jvmTarget = "1.8"
    compileTestKotlin.get().kotlinOptions.jvmTarget = "1.8"

    jar {
        archiveFileName.set("${project.name}_S-${project.version}.jar")
        destinationDirectory.set(file(pluginStorage))
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
}
