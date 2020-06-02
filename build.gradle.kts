val jvm_version by extra<String> { "1.8" }

fun getProperty(key: String) : String =
        if (project.hasProperty(key)) "${properties[key]}"
        else throw IllegalArgumentException("Property $key not defined")

// Export a shorter form of getProperty as a function type in extra properties.
val getProp by extra<(String) -> String> { ::getProperty }

plugins {
    id("java")
    id("org.springframework.boot") version "2.1.6.RELEASE"
}

allprojects {
    repositories {
        // this repo should be available in every subproject that uses kotlin
        mavenCentral()
        mavenLocal()
    }
}

configure<JavaPluginConvention> {
    setSourceCompatibility(jvm_version)
    setTargetCompatibility(jvm_version)
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:${getProp("springBootVersion")}")
    implementation("org.apache.commons:commons-lang3:${getProp("commonLangVersion")}")
}
