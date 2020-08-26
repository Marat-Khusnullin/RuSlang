package com.application_ruslang.ruslang

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Phrase::class, FavPhrase::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phraseDao(): PhraseDao

}