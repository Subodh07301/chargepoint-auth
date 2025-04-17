plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
}

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Core Spring Boot web framework
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Apache Kafka integration
    implementation("org.springframework.kafka:spring-kafka")

    // Bean validation (for @Valid, @NotBlank, etc.)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Logging with SLF4J and Logback
    implementation("org.springframework.boot:spring-boot-starter-logging")

    // JUnit 5 Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // Kafka test support
    testImplementation("org.springframework.kafka:spring-kafka-test")

    // JUnit Jupiter
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Mockito for unit tests
    testImplementation("org.mockito:mockito-core")
}

tasks.test {
    useJUnitPlatform()
}
