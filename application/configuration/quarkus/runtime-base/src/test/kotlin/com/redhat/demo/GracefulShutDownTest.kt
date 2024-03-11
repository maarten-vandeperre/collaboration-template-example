package com.redhat.demo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
@QuarkusTest
class GracefulShutDownTest {
    @Test
    fun checkResourcesOnShutDownHook() {
        //Given
        lateinit var resource1: DatabaseResource //Should be injected from the app
        lateinit var resource2: FileSystemResource //Should be injected from the app
        //When
        given()
            .`when`().post("/shutdown-hook/trigger")
            .then()
            .statusCode(204)

        //Then
        assertThat(resource1.isGracefulShutdown(), equalTo(true))
        assertThat(resource2.isGracefulShutdown(), equalTo(true))
    }
}
interface Resource {
    fun isGracefulShutdown(): Boolean
}
interface DatabaseResource: Resource //Implemented in the in-memory section
interface FileSystemResource: Resource //Implemented in the in-memory section




