package com.redhat.demo.dataproviders.inmemorydb.driver

import com.redhat.demo.core.maartendomain.functional.Response
import java.util.concurrent.ConcurrentHashMap

interface InMemoryDatabase<DATA_TYPE> {
    fun persist(ref: String, data: DATA_TYPE): Response<Boolean>

    fun findAll(): Response<List<DATA_TYPE>>
}

class DefaultInMemoryDatabase<DATA_TYPE> : InMemoryDatabase<DATA_TYPE> {
    private val db: ConcurrentHashMap<String, DATA_TYPE> = ConcurrentHashMap()

    override fun persist(ref: String, data: DATA_TYPE): Response<Boolean> {
        db[ref] = data
        return Response.success(true)
    }

    override fun findAll(): Response<List<DATA_TYPE>> {
        return Response.success(db.values.toList())
    }
}