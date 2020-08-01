package com.application_ruslang.ruslang.model

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application_ruslang.ruslang.*
import com.application_ruslang.ruslang.presenter.SearchFragmentPresenter
import com.application_ruslang.ruslang.interfaces.ModelInterface
import com.opencsv.CSVReader
import java.io.*
import kotlin.random.Random


class Model(presenter: SearchFragmentPresenter) : ModelInterface {

    var inpitStream: InputStream

    var currentFilter: String = ""
    var currentFilteredList = mutableListOf<Phrase>()

    //var fullList: List<Array<String>>
    val currentPresenter: SearchFragmentPresenter
    private var db: AppDatabase? = null

    init {
        inpitStream =
            App.applicationContext().resources.openRawResource(
                R.raw.teenslang_appwords
            )
        Thread {
            db = Room.databaseBuilder(
                App.applicationContext(),
                AppDatabase::class.java, "phrases"
            ).createFromAsset("databases/phrases").build()
            currentFilteredList = db?.phraseDao()?.getAll()!!.toMutableList()
            Log.d("UUU", "" + db!!.phraseDao().getAll().size)

        }.start()
        Thread.sleep(5000)


// код с CSV на всякий случай
        /*bufReader = InputStreamReader(inpitStream)

        reader = CSVReader(bufReader)
        fullList = reader.readAll()
        //var listt = mutableListOf<Phrase>()
        fullList.forEach {

            it[0] = it[0].capitalize()
            it[1] = it[1].capitalize()
            var a = Phrase(null,it[0],
                it[1],
                it[2],
                it[3],
                it[4],
                it[5],
                it[6],
                it[7],
                0.0)
           // db!!.phraseDao().insertPhrase(a)
           *//*currentFilteredList.add(
                Phrase(
                    0,
                    it[1],
                    it[2],
                    it[3],
                    it[4],
                    it[5],
                    it[6],
                    it[7],
                    it[8],
                    0.0
                )
            )*//*
        }*/


        currentPresenter = presenter
    }

    fun setSearchString(string: String) {
        currentFilteredList.clear()
        /*fullList.forEach {
            if (it[0].toLowerCase().contains(string)) {
                currentFilteredList.add(
                    Phrase(
                        0,
                        it[1],
                        it[2],
                        it[3],
                        it[4],
                        it[5],
                        it[6],
                        it[7],
                        it[8],
                        0.0
                    )
                )
            }
        }*/
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
        val index = Random.nextInt(currentFilteredList.size)

        /* val phrase = Phrase(
             0,
             phraseString[1],
             phraseString[2],
             phraseString[3],
             phraseString[4],
             phraseString[5],
             phraseString[6],
             phraseString[7],
             phraseString[8],
             0.0
         )*/
        currentFilteredList.clear()
        //currentFilteredList.add(phrase)
        return currentFilteredList[0]
    }

}