plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.allopen") version "1.9.22"
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "com.redhat.demo"
version = "1.0.0-SNAPSHOT"

subprojects.filter { !(it.name == "platform" || it.parent?.name == "platform") }.forEach {
    println("configure ${it.name}")
    it.group = "com.redhat.demo"
    it.version = "1.0.0-SNAPSHOT"

    it.repositories {
        mavenCentral()
        mavenLocal()
    }

    it.apply(plugin = "idea")
    it.apply(plugin = "org.jetbrains.kotlin.jvm")
    it.apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    it.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21

        sourceSets.getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        sourceSets.getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    it.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget =  JavaVersion.VERSION_21.toString()
            javaParameters = true
        }
    }

    it.dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}
