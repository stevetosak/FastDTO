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
    implementation("org.springframework:spring-context:6.2.7")
    implementation("org.springframework:spring-aspects:6.2.7")
    // https://mvnrepository.com/artifact/org.springframework/spring-test
    testImplementation("org.springframework:spring-test:6.2.7")// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.3")

    // https://mvnrepository.com/artifact/org.springframework/spring-web
//    implementation("org.springframework:spring-web:6.2.8")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.3")

}

tasks.test {
    useJUnitPlatform()
}