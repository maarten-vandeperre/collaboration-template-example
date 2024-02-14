package com.redhat.demo.core.domain.people

import java.util.UUID

typealias PersonId = UUID
data class Person(
    val ref: PersonId,
    val firstName: String,
    val lastName: String
){
    val name = "$firstName $lastName"
}