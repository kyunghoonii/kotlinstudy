plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.jetbrains.kotlin.plugin.spring") version "1.9.24"
}

allOpen {
	annotation("org.springframework.stereotype.Component")
	annotation("org.springframework.transaction.annotation.Transactional")
	annotation("org.springframework.scheduling.annotation.Async")
	annotation("org.springframework.cache.annotation.Cacheable")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
	implementation("org.json:json:20231013")
	runtimeOnly("com.h2database:h2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.microutils:kotlin-logging:1.12.5")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations.all {
	exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging") // Logback starter
	exclude(group = "ch.qos.logback", module = "logback-classic")
	exclude(group = "ch.qos.logback", module = "logback-core")
	exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j") // Log4j→SLF4J 브리지 제거
	exclude(group = "org.slf4j", module = "jul-to-slf4j") // 필요 시: JUL->SLF4J 브리지도 제거
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
