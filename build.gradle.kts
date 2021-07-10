version = "1.4.10"
plugins {
    kotlin("jvm") version "1.5.20"
//    kotlin("plugin.serialization") version "1.5.20"  단순 라이브러리용 fat jar 만드는 용도로는 plugins 에 추가할 필요 없는 듯?
    id("kr.entree.spigradle") version "2.2.3"
}
group = "kr.sul"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.2.2")
    compileOnly("com.destroystokyo.paper", "paper-api", "1.12.2-R0.1-SNAPSHOT")
}

spigot {
    authors = listOf("SuL")
    apiVersion = "1.12"
    version = project.version.toString()
    description = "Kotlin STD Library"
}


tasks.compileJava.get().options.encoding = "UTF-8"

tasks {
    compileKotlin.get().kotlinOptions.jvmTarget = "1.8"
    compileTestKotlin.get().kotlinOptions.jvmTarget = "1.8"

    jar {
        archiveFileName.set("${project.name}_S-${project.version}.jar")
        destinationDirectory.set(file("C:/Users/PHR/Desktop/PluginStorage"))
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
}
