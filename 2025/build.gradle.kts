plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.apache.commons:commons-text:1.14.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.2")
}

application {
    // Define the main class for the application.
    mainClass.set("adventOfCode.app.App")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

