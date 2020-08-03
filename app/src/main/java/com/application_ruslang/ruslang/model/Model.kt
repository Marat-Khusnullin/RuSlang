package com.application_ruslang.ruslang.model

import android.content.Intent
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application_ruslang.ruslang.*
import com.application_ruslang.ruslang.presenter.SearchFragmentPresenter
import com.application_ruslang.ruslang.interfaces.ModelInterface
import com.application_ruslang.ruslang.presenter.FavoritesPresenter
import com.opencsv.CSVReader
import kotlinx.coroutines.delay
import kotlinx.coroutines.*
import java.io.*
import kotlin.random.Random
import kotlin.random.nextInt


class Model() : ModelInterface {

    var inpitStream: InputStream

    var currentFilter: String = ""
    var currentFilteredList = mutableListOf<Phrase>()

    var fullList: List<Array<String>>? = null
    var currentPresenter: SearchFragmentPresenter? = null
    private var db: AppDatabase? = null
    var pr: FavoritesPresenter? = null


    constructor(presenter: SearchFragmentPresenter) : this() {
        currentPresenter = presenter
    }

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
        Thread.sleep(10000)


// код с CSV на всякий случай
        /*var bufReader = InputStreamReader(inpitStream)

        var reader = CSVReader(bufReader)
        var fullList = reader.readAll()
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


    }

    fun setSearchString(string: String) {
        currentFilteredList.clear()

        GlobalScope.launch() {
            currentFilteredList = db!!.phraseDao().findFilteredByName(string).toMutableList()
            App.applicationContext().sendBroadcast(Intent("1"))
        }
        //Thread.sleep(5000)
        //currentPresenter.filteredListUpdated()
        //currentFilteredList = db!!.phraseDao().findFilteredByName(string).toMutableList()
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


    }

    fun getPhrasesByIndexAndCount(index: Int, count: Int): MutableList<Phrase?> {
        val list = mutableListOf<Phrase?>()
        var lastIndex: Int = index + count
        var firstIndex = index

        if (lastIndex >= currentFilteredList.size) {
            lastIndex = currentFilteredList.size - 1
            firstIndex = lastIndex - count
            if (firstIndex < 0)
                firstIndex = 0
        }

        for (i in index..lastIndex)
            list.add(currentFilteredList[i])

        return list
    }

    fun getRandomPhrase() {
        GlobalScope.launch {
            currentFilteredList.clear()
            val index = Random.nextLong(db!!.phraseDao().getPhrasesCount())
            val phrase = db!!.phraseDao().findById(index)
            currentFilteredList.add(phrase)
            App.applicationContext().sendBroadcast(Intent("1"))
        }
    }

    fun switchFavoriteStatus(phrase: Phrase?) {
        GlobalScope.launch {

            var b = db?.phraseDao()?.findFavPhraseById(phrase?.id!!)
            if (b == null) {
                db?.phraseDao()?.insertFavPhrase(FavPhrase(phraseId = phrase?.id))
            } else {
                db?.phraseDao()?.deleteFavPhrase(b)
            }
        }
    }

    fun getFavoritesPhrases() {
        GlobalScope.launch {
            var list = db?.phraseDao()?.getFavoritePhrases()
            val intent = Intent("2")

        }

    }



}