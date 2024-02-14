plugins {
    id("io.quarkus")
}

dependencies {
    implementation(platform(project(":platform:quarkus-platform")))

    implementation(project(":application:core:maarten-domain"))
    implementation(project(":application:core:maarten-core-utils"))
    implementation(project(":application:core:domain"))
    implementation(project(":application:core:usecases"))
    implementation(project(":application:data-providers:in-memory-db:driver"))
    implementation(project(":application:data-providers:in-memory-db:mapping"))

    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}
