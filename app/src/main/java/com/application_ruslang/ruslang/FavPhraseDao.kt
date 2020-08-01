package com.application_ruslang.ruslang

import androidx.room.Dao
import androidx.room.Query


interface FavPhraseDao {
   // @Query("SELECT * FROM favorites_phrases")
    fun getAll(): List<FavPhrase>
}