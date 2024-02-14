plugins {
    id("io.quarkus")
}

dependencies {
    implementation(platform(project(":platform:quarkus-platform")))

    implementation(project(":application:configuration:quarkus:maarten-api"))
    implementation(project(":application:configuration:quarkus:runtime-api"))

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}