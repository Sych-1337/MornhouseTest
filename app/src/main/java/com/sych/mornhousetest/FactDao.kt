package com.sych.mornhousetest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFact(fact: FactEntity)

    @Query("SELECT * FROM facts ORDER BY id DESC")
    fun getAllFacts(): Flow<List<FactEntity>>
}
