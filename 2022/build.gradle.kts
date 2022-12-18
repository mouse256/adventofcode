plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-text:1.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

application {
    // Define the main class for the application.
    mainClass.set("adventOfCode.app.App")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

