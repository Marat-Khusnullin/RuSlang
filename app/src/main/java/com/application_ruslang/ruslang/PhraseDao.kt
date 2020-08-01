package com.application_ruslang.ruslang

import androidx.room.*

@Dao
interface PhraseDao {
    @Query("SELECT * FROM phrase")
    fun getAll(): List<Phrase>

    @Query("SELECT * FROM phrase WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Phrase>

    @Query("SELECT * FROM phrase WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String): Phrase

    @Update
    fun updatePhrase(phrase: Phrase)

    @Insert
    fun insertAll(vararg phrases: Phrase)

    @Insert
    fun insertPhrase(phrase: Phrase)

    @Delete
    fun delete(dataPhrase: Phrase)
}