group = "kr.sul"
version = ext.get("version")!!

dependencies {
    compileOnly("io.github.waterfallmc:waterfall-api:1.18-R0.1-SNAPSHOT")
}

// ext.get(...)을 bungee 안에 넣으면 못 가져옴
val rootName = ext.get("rootName")!! as String
bungee {
    name = rootName
}
