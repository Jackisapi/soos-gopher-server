val kotlin_version: String by project
val logback_version: String by project
val ktor_version: String by project

plugins {
    application
    kotlin("jvm") version "2.0.0"
    id("org.graalvm.buildtools.native") version "0.10.2"}

application{
    mainClass.set("xyz.chilll.MainKt")
}

tasks.jar {
    manifest{
        attributes["Main-Class"] = "xyz.chilll.MainKt"
    }
    from(configurations.runtimeClasspath.get().map{if (it.isDirectory) it else zipTree(it)})
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

}

group = "xyz.chilll"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(kotlin("test"))
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-network:$ktor_version")
}

tasks.test {
    useJUnitPlatform()
}
