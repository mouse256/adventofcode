plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

val Versions = mapOf(
	"kotest" to "5.0.1",
	"log4j" to "2.14.1",
)

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.10")

    testImplementation ("io.kotest:kotest-runner-junit5:${Versions["kotest"]}")
    testImplementation ("io.kotest:kotest-assertions-core:${Versions["kotest"]}")
    testImplementation ("io.kotest:kotest-property:${Versions["kotest"]}")

    runtimeOnly("org.apache.logging.log4j:log4j-core:${Versions["log4j"]}")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:${Versions["log4j"]}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        javaParameters = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

