package com.redhat.demo.configuration.quarkus.runtimeapi.resources

import com.fasterxml.jackson.databind.ObjectMapper
import com.redhat.demo.core.maartendomain.functional.ErrorResponse
import com.redhat.demo.core.maartendomain.functional.SuccessResponse
import com.redhat.demo.core.usecases.people.CreatePersonUseCase
import com.redhat.demo.core.usecases.people.FindAllPeopleUseCase
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/people")
class PersonResource(
    private val createPersonUseCase: CreatePersonUseCase,
    private val findAllPeopleUseCase: FindAllPeopleUseCase
) {
    private val mapper = ObjectMapper()

    @POST
    @Consumes(value = [MediaType.APPLICATION_JSON])
    fun createAddress(data: String): Response {
        val input = mapper.readTree(data)
        val result = createPersonUseCase.execute(
            CreatePersonUseCase.UseCaseRequest(
                firstName = input.get("firstName").asText(),
                lastName = input.get("lastName").asText()
            )
        )
        return if (result is SuccessResponse) {
            Response.ok(result.data.personId.toString()).build()
        } else {
            Response.status(422, (result as ErrorResponse).errorMessages.joinToString(",")).build()
        }
    }

    @GET
    fun findAllPeople(): Response {
        val result = findAllPeopleUseCase.execute(FindAllPeopleUseCase.UseCaseRequest())
        return if (result is SuccessResponse) {
            Response.ok(mapper.writeValueAsString(result.data.people)).build()
        } else {
            Response.status(422, (result as ErrorResponse).errorMessages.joinToString(",")).build()
        }
    }
}