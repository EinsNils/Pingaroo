plugins {
	java
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
}

group = "de.pingaroo"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.boot.web)
	implementation(libs.spring.boot.data.jpa)
	implementation(libs.spring.boot.security)
	implementation(libs.mssql.jdbc)
	implementation(libs.mapstruct)
	implementation(libs.jakarta.validation)
	implementation(libs.hibernate.validator)
	implementation(libs.jjwt.api)
	runtimeOnly(libs.jjwt.impl)
	runtimeOnly(libs.jjwt.jackson)
	
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
	annotationProcessor(libs.mapstruct.processor)
	
	testImplementation(libs.spring.boot.test)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<JavaCompile> {
	options.compilerArgs.addAll(listOf("-Amapstruct.defaultComponentModel=spring"))
}
