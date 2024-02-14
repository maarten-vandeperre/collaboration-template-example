package com.redhat.demo.core.usecases.people

import com.redhat.demo.core.domain.people.Person
import com.redhat.demo.core.maartendomain.functional.Response

interface FindAllPeopleUseCase {
    fun execute(request: UseCaseRequest): Response<UseCaseResponse>

    class UseCaseRequest {
    }

    data class UseCaseResponse(
        val people: List<Person>
    )
}

class DefaultFindAllPeopleUseCase(
    private val personRepository: PersonRepository
) : FindAllPeopleUseCase {
    override fun execute(request: FindAllPeopleUseCase.UseCaseRequest): Response<FindAllPeopleUseCase.UseCaseResponse> {
        return personRepository.findAll().map {
            FindAllPeopleUseCase.UseCaseResponse(it)
        }
    }

}