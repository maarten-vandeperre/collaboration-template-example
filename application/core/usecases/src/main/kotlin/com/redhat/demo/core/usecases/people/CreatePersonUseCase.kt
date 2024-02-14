package com.redhat.demo.core.usecases.people

import com.redhat.demo.core.domain.people.Person
import com.redhat.demo.core.domain.people.PersonId
import com.redhat.demo.core.maartendomain.functional.Response
import java.util.*

interface CreatePersonUseCase {
    fun execute(request: UseCaseRequest): Response<UseCaseResponse>

    data class UseCaseRequest(
        val firstName: String?,
        val lastName: String?
    )

    data class UseCaseResponse(
        val personId: PersonId
    )
}

class DefaultCreatePersonUseCase(
    private val personRepository: PersonRepository
) : CreatePersonUseCase {
    override fun execute(request: CreatePersonUseCase.UseCaseRequest): Response<CreatePersonUseCase.UseCaseResponse> {
        val errors = mutableListOf<String>()
        if (request.firstName == null) {
            errors.add("Field 'first name' is required")
        }
        if (request.lastName == null) {
            errors.add("Field 'last name' is required")
        }

        return if (errors.isEmpty()) {
            personRepository.savePerson(Person(
                ref = UUID.randomUUID(),
                firstName = request.firstName!!,
                lastName = request.lastName!!
            )).map {
                CreatePersonUseCase.UseCaseResponse(
                    personId = it
                )
            }
        } else {
            Response.fail(errors)
        }
    }

}