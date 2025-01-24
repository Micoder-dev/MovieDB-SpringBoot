plugins {
    // Kotlin plugins
    kotlin("jvm") version "1.9.25" // Kotlin JVM support
    kotlin("plugin.spring") version "1.9.25" // Spring support for Kotlin
    kotlin("plugin.jpa") version "1.9.25" // JPA support for Kotlin

    // Spring Boot plugins
    id("org.springframework.boot") version "3.4.1" // Spring Boot plugin
    id("io.spring.dependency-management") version "1.1.7" // Dependency management

    // WAR packaging (from Other Plugins)
    war // Plugin for creating WAR files
}

group = "com.micoder"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot and related dependencies
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Security dependencies (from Other Deps)
    implementation("org.springframework.boot:spring-boot-starter-security") // For Spring Security
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6") // Thymeleaf integration with Spring Security

    // Thymeleaf (from Other Deps)
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // Thymeleaf template engine

    // Spring Session and Actuator (from Other Deps)
    // implementation("org.springframework.session:spring-session-jdbc") // For managing session data in JDBC
    implementation("org.springframework.boot:spring-boot-starter-actuator") // Actuator for monitoring and metrics

    // Database and migration
    // runtimeOnly("org.postgresql:postgresql") // PostgreSQL database driver
    // implementation("org.flywaydb:flyway-core") // Flyway for database migrations
    // implementation("org.flywaydb:flyway-database-postgresql") // PostgreSQL-specific Flyway support
    runtimeOnly("com.mysql:mysql-connector-j")

    // Environment configuration (from Other Deps)
    implementation("io.github.cdimascio:dotenv-kotlin:6.3.1") // dotenv for managing environment variables

    // OpenAPI documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.3") // SpringDoc for OpenAPI documentation

    //JWT
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5") // for JSON processing

    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.mockito", module = "mockito-core")
        exclude(group = "org.mockito", module = "mockito-junit-jupiter")
    }
    testImplementation("com.ninja-squad:springmockk:4.0.0") // Mocking library for Kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5") // Kotlin test integration with JUnit5
    testImplementation("org.springframework.security:spring-security-test") // Security testing (from Other Deps)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") // JUnit platform launcher

    // Provided runtime (from Other Deps)
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat") // Embedded Tomcat server
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Here is where you customize the bootWar task:
tasks.bootWar {
    archiveFileName = "app.war"
}