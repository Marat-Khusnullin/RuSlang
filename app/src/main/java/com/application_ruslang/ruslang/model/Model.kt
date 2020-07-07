package com.application_ruslang.ruslang.model

import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.SearchFragmentPresenter
import com.application_ruslang.ruslang.interfaces.ModelInterface
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.random.Random

class Model(presenter: SearchFragmentPresenter) : ModelInterface {

    var inpitStream: InputStream
    var bufReader: InputStreamReader
    var reader: CSVReader
    var currentFilter: String = ""
    var currentFilteredList = mutableListOf<Phrase>()
    var fullList: List<Array<String>>
    val currentPresenter: SearchFragmentPresenter

    init {
        inpitStream =
            App.applicationContext().resources.openRawResource(
                R.raw.teenslang_appwords
            )
        bufReader = InputStreamReader(inpitStream)
        reader = CSVReader(bufReader)
        fullList = reader.readAll()
        fullList.forEach {
            currentFilteredList.add(
                Phrase(
                    it[0],
                    it[1],
                    it[2],
                    it[3],
                    it[4],
                    it[5],
                    it[6],
                    it[7],
                    it[8]
                )
            )
        }
        currentPresenter = presenter
    }

    fun setSearchString(string: String) {
        currentFilteredList.clear()
        fullList.forEach {
            if (it[0].toLowerCase().contains(string)) {
                currentFilteredList.add(
                    Phrase(
                        it[0],
                        it[1],
                        it[2],
                        it[3],
                        it[4],
                        it[5],
                        it[6],
                        it[7],
                        it[8]
                    )
                )
            }
        }
        currentPresenter.filteredListUpdated()
    }

    fun getPhrasesByIndexAndCount(index: Int, count: Int): MutableList<Phrase?> {
        val list = mutableListOf<Phrase?>()
        var lastIndex: Int = index + count

        if (lastIndex >= currentFilteredList.size) {
            lastIndex = currentFilteredList.size - 1
        }
        for (i in index..lastIndex)
            list.add(currentFilteredList[i])
        return list
    }

    fun getRandomPhrase(): Phrase {
        val index = Random.nextInt(fullList.size)
        val phraseString = fullList[index]
        val phrase = Phrase(
            phraseString[0],
            phraseString[1],
            phraseString[2],
            phraseString[3],
            phraseString[4],
            phraseString[5],
            phraseString[6],
            phraseString[7],
            phraseString[8]
        )
        currentFilteredList.clear()
        currentFilteredList.add(phrase)
        return phrase
    }

}