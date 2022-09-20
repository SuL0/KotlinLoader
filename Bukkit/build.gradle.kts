group = "kr.sul"
version = ext.get("version")!!

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
}

val rootName = ext.get("rootName")!! as String

spigot {
    name = rootName
    authors = listOf("SuL")
    apiVersion = "1.12"
    version = project.version.toString()
    description = "Kotlin STD Library"
    load = kr.entree.spigradle.data.Load.STARTUP
}