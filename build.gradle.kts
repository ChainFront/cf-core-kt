/*
 * Copyright (c) 2019 ChainFront LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import pl.allegro.tech.build.axion.release.domain.ChecksConfig
import pl.allegro.tech.build.axion.release.domain.TagNameSerializationConfig


plugins {
    val kotlinVersion = "1.3.41"

    // Spring Boot
    id("org.springframework.boot") version "2.1.4.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.6.RELEASE"

    // Kotlin
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.kapt") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion

    // Versioning plugin
    id("pl.allegro.tech.build.axion-release") version "1.9.3"

    // Publishing
    `maven-publish`
}

// Define the namespace for our build artifacts
group = "pcrypto"

configure<DependencyManagementExtension> {
    val springBootVersion: String = "2.1.4.RELEASE"
    val springCloudVersion: String = "Greenwich.SR2"
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion") {
            bomProperty("kotlin.version", "1.3.41")
        }
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

// Explicitly declare that we're using JDK 1.8
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Convenience to let JPA work with Kotlin
allOpen {
    annotation("javax.persistence.Entity")
}

repositories {
    mavenCentral()

    // We want the release candidate build of Spring Cloud Stream, which is only in the milestone repo
    maven {
        url = uri("https://repo.spring.io/libs-milestone")
    }

    // For custom-built libs (such as java-stellar-sdk)
    mavenLocal()
}

dependencies {

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.1.4.RELEASE")

    // JWT
    implementation("com.nimbusds:nimbus-jose-jwt:6.0.2")

    // Spring Cloud Vault
    implementation("org.springframework.vault:spring-vault-core:2.1.0.RELEASE")

    // Spring Cloud Stream w/ Kafka
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")

    // Email
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // Authy for SMS and MFA push approvals
    implementation("com.authy:authy-java:1.5.0")

    // Phone number parsing and validation with Google libphonenumber
    implementation("com.googlecode.libphonenumber:libphonenumber:8.9.15")

    // Password validation
    implementation("org.passay:passay:1.0")

    // Apache commons
    implementation("org.apache.commons:commons-lang3:3.8.1")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // Exposed DB library
    implementation("org.jetbrains.exposed:exposed:0.16.4")
    implementation("org.jetbrains.exposed:spring-transaction:0.16.4")

    // OpenAPI (aka Swagger)
    implementation("io.springfox:springfox-swagger2:2.9.2")

    // Needed for AWS IAM vault authentication
    runtime("com.amazonaws:aws-java-sdk-core")

    // Enable JSON logging
    runtime("net.logstash.logback:logstash-logback-encoder:5.3") {
        exclude(module = "logback-core")
    }

    // For development
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")

    // Testing with JUnit 5 and MockK
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.ninja-squad:springmockk:1.1.1")
}


// Versioning with the Axion release plugin
scmVersion {
    // Treat uncommitted changes as trigger for version increment
    ignoreUncommittedChanges = true

    // All versions will start with "v"
    tag(closureOf<TagNameSerializationConfig> {
        prefix = "v"
        versionSeparator = ""
    })

    // Our versioning scheme is major.minor.rcX. If we're on a branch named "release/*", increment the release
    // candidate number, otherwise increment the minor version number.
    versionIncrementer("incrementMinorIfNotOnRelease", mapOf(releaseBranchPattern to "release.*"))
    branchVersionIncrementer(
        mapOf(
            "master" to "incrementMinor",
            "feature" to "incrementMinor",
            "release/.*" to "incrementPrerelease"
        )
    )

    // Decorators
    versionCreator("simple")
    branchVersionCreator(
        mapOf("feature/.*" to "versionWithBranch")
    )

    checks(closureOf<ChecksConfig> {
        // Allow for releasing a new version if there are uncommitted changes
        uncommittedChanges = false
    })
}
project.version = scmVersion.version

// Add the version number to the manifest
tasks.withType<Jar> {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version.toString()
    }
}

// Configure the Gradle wrapper
tasks.withType<Wrapper> {
    gradleVersion = "5.3.1"
}
