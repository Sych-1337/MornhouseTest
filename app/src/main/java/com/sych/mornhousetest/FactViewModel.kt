package com.sych.mornhousetest

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class FactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FactRepository
    val allFacts: LiveData<List<FactEntity>>

    init {
        val factDao = AppDatabase.getDatabase(application).factDao()
        repository = FactRepository(factDao)
        allFacts = repository.allFacts.asLiveData()
    }

    fun insertFact(fact: FactEntity) = viewModelScope.launch {
        repository.insertFact(fact)
    }
}
