package com.redhat.demo.configuration.quarkus.runtimeapi.config

import com.redhat.demo.core.usecases.people.CreatePersonUseCase
import com.redhat.demo.core.usecases.people.DefaultCreatePersonUseCase
import com.redhat.demo.core.usecases.people.DefaultFindAllPeopleUseCase
import com.redhat.demo.core.usecases.people.FindAllPeopleUseCase
import com.redhat.demo.core.usecases.people.PersonRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

@ApplicationScoped
class UseCaseConfig {

    @Produces
    fun createPersonUseCase(personRepository: PersonRepository): CreatePersonUseCase {
        return DefaultCreatePersonUseCase(personRepository)
    }

    @Produces
    fun findAllPeopleUseCase(personRepository: PersonRepository): FindAllPeopleUseCase {
        return DefaultFindAllPeopleUseCase(personRepository)
    }
}