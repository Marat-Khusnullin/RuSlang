package com.application_ruslang.ruslang

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Phrase(
    @PrimaryKey(autoGenerate = true) var id: Long? = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "definition") var definition: String?,
    @ColumnInfo(name = "type") var type: String?,
    @ColumnInfo(name = "group") var group: String?,
    @ColumnInfo(name = "examples") var examples: String?,
    @ColumnInfo(name = "hashtags") var hashtags: String?,
    @ColumnInfo(name = "origin") var origin: String?,
    @ColumnInfo(name = "synonyms") var synonyms: String?,
    @ColumnInfo(name = "rating") var rating: Double?

) {
    @Ignore
    var isFavorite: Boolean = false

    @Ignore
    var trendData: TrendData = TrendData(0,0)
}