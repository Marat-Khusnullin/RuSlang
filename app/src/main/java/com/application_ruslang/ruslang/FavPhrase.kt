package com.application_ruslang.ruslang

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Phrase::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("phrase_id")
        )
    )
)
class FavPhrase(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0, @ColumnInfo(name = "phrase_id")
    var phraseId: Long? = 0
) {

    /*@PrimaryKey(autoGenerate = true)
    var id: Long = 0*/


}