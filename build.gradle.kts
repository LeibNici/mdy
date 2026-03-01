import org.gradle.external.javadoc.StandardJavadocDocletOptions

plugins {
    java
    checkstyle
    id("org.springframework.boot") version "3.2.12"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.jiandaoyun"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.mysql:mysql-connector-j")

    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Javadoc> {
    isFailOnError = true
    (options as StandardJavadocDocletOptions).apply {
        encoding = "UTF-8"
        charSet = "UTF-8"
        addStringOption("Xdoclint:all,-missing", "-quiet")
    }
}

checkstyle {
    toolVersion = "10.17.0"
    configFile = file("docs/checkstyle.xml")
    isIgnoreFailures = false
}
