plugins {
    id("java")
}

group = "com.vitosak"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.auto.service/auto-service
    implementation("com.google.auto.service:auto-service:1.1.1")
    implementation("org.springframework:spring-context:6.1.4")
    implementation("org.springframework:spring-aspects:6.1.4")

}

tasks.test {
    useJUnitPlatform()
}