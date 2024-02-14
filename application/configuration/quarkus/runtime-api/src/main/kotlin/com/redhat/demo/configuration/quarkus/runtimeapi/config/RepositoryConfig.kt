package com.redhat.demo.configuration.quarkus.runtimeapi.config

import com.redhat.demo.core.domain.people.Person
import com.redhat.demo.core.usecases.people.PersonRepository
import com.redhat.demo.dataproviders.inmemorydb.driver.DefaultInMemoryDatabase
import com.redhat.demo.dataproviders.inmemorydb.mapping.people.InMemoryPersonRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import jakarta.inject.Singleton

@ApplicationScoped
class RepositoryConfig {
    @Produces
    @Singleton
    fun personRepository(): PersonRepository {
        return InMemoryPersonRepository(DefaultInMemoryDatabase<Person>())
    }
}