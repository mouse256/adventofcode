plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.6")
    implementation("org.apache.commons:commons-text:1.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")

    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.19.0")
}

application {
    // Define the main class for the application.
    mainClass.set("adventOfCode.app.App")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

