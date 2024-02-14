package com.redhat.demo.core.usecases.people

import com.redhat.demo.core.domain.people.Person
import com.redhat.demo.core.domain.people.PersonId
import com.redhat.demo.core.maartendomain.functional.Response

interface PersonRepository {
    fun savePerson(person: Person): Response<PersonId>
    fun findAll(): Response<List<Person>>
}