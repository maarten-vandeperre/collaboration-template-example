package com.redhat.demo.dataproviders.inmemorydb.mapping.people

import com.redhat.demo.core.domain.people.Person
import com.redhat.demo.core.domain.people.PersonId
import com.redhat.demo.core.maartendomain.functional.Response
import com.redhat.demo.core.usecases.people.PersonRepository
import com.redhat.demo.dataproviders.inmemorydb.driver.InMemoryDatabase

class InMemoryPersonRepository(
    private val inMemoryDatabase: InMemoryDatabase<Person>
) : PersonRepository {
    override fun savePerson(person: Person): Response<PersonId> {
        inMemoryDatabase.persist(person.ref.toString(), person)
        return Response.success(person.ref)
    }

    override fun findAll(): Response<List<Person>> {
        return inMemoryDatabase.findAll()
    }
}