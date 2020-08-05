package com.application_ruslang.ruslang.model

import android.util.Log
import androidx.room.Room
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.AppDatabase
import com.application_ruslang.ruslang.FavPhrase
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.ModelInterface
import com.application_ruslang.ruslang.presenter.FavoritesPresenter
import com.application_ruslang.ruslang.presenter.SearchFragmentPresenter
import kotlinx.coroutines.*
import kotlin.random.Random


class Model() : ModelInterface {

    var currentFilteredList = mutableListOf<Phrase>()

    var currentPresenter: SearchFragmentPresenter? = null
    private var db: AppDatabase? = null
    var pr: FavoritesPresenter? = null

    constructor(presenter: SearchFragmentPresenter) : this() {
        currentPresenter = presenter
    }

    init {
        GlobalScope.launch {
            db = Room.databaseBuilder(
                App.applicationContext(),
                AppDatabase::class.java, "phrases"
            ).createFromAsset("databases/phrases").build()
            currentFilteredList = db?.phraseDao()?.getAll()!!.toMutableList()

            withContext(Dispatchers.Main) {
                currentPresenter?.filteredListUpdated()
            }

        }


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
            if (string == "") {
                currentFilteredList = db!!.phraseDao().getAll().toMutableList()
            } else {
                currentFilteredList = db!!.phraseDao().findFilteredByName(string).toMutableList()
            }

            withContext(Dispatchers.Main) {
                currentPresenter?.filteredListUpdated()
            }
        }
    }

    fun getPhrasesByIndexAndCount(index: Int, count: Int): MutableList<Phrase?> = runBlocking {
        val list = mutableListOf<Phrase?>()
        GlobalScope.async {

            var lastIndex: Int = 30
            var index = 0
            if (lastIndex >= currentFilteredList.size) {
                lastIndex = currentFilteredList.size - 1

            }

            for (i in index..lastIndex) {
                var b =db?.phraseDao()?.findFavPhraseById(currentFilteredList[i].id!!)
                if (b != null) {
                    currentFilteredList[i].isFavorite = true
                    Log.d("TATATA", "" + currentFilteredList[i].name + " " + currentFilteredList[i].id )
                    Log.d("TATATA", "" + b.id + " " + b.phraseId )
                }
                list.add(currentFilteredList[i])
            }

        }.await()

        return@runBlocking list
    }

    fun getRandomPhrase() {
        GlobalScope.launch {
            currentFilteredList.clear()
            val index = Random.nextLong(db!!.phraseDao().getPhrasesCount())
            val phrase = db!!.phraseDao().findById(index)
            currentFilteredList.add(phrase)
            withContext(Dispatchers.Main) {
                currentPresenter?.filteredListUpdated()
            }

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
            withContext(Dispatchers.Main) {
                pr?.setList(list!!)
            }

        }

    }


}