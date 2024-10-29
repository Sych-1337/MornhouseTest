package com.sych.mornhousetest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FactRepository(private val factDao: FactDao) {

    val allFacts: Flow<List<FactEntity>> = factDao.getAllFacts()

    suspend fun insertFact(fact: FactEntity) {
        withContext(Dispatchers.IO) {
            factDao.insertFact(fact)
        }
    }
}

