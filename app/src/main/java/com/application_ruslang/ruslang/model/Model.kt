package com.application_ruslang.ruslang.model

import android.util.Log
import androidx.room.Room
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.AppDatabase
import com.application_ruslang.ruslang.FavPhrase
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.presenter.FavoritesPresenter
import com.application_ruslang.ruslang.presenter.SearchFragmentPresenter
import kotlinx.coroutines.*
import kotlin.random.Random


class Model() {

    private var subscribers: MutableList<SubscribablePresenterInterface> = mutableListOf()
    @Volatile
    private var currentFilteredList = mutableListOf<Phrase>()
    private var db: AppDatabase? = null

    var currentPresenter: SearchFragmentPresenter? = null

    companion object {
        val instance = Model()
    }

    init {
        GlobalScope.launch {
            db = Room.databaseBuilder(
                App.applicationContext(),
                AppDatabase::class.java, "phrases"
            ).createFromAsset("databases/phrases").build()
            currentFilteredList = db?.phraseDao()?.getAll()!!.toMutableList()
            withContext(Dispatchers.Main) {
                notifyPhrasesListReady()
            }

        }
        Log.d("Model", "Model initialized")
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
            var lastIndex: Int = index + count
            if (lastIndex >= currentFilteredList.size) {
                lastIndex = currentFilteredList.size - 1
            }

            for (i in index..lastIndex) {

                var b = db?.phraseDao()?.findFavPhraseById(currentFilteredList[i].id!!)
                if (b != null) {
                    currentFilteredList[i].isFavorite = true
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

    fun addToFavorites(phrase: Phrase?) {
        GlobalScope.launch {
            db?.phraseDao()?.insertFavPhrase(FavPhrase(phraseId = phrase?.id))
            Log.d("Database", "Phrase " + phrase?.name + " is favorite now!")
        }
    }

    fun removeFromFavorites(phrase: Phrase?) {
        GlobalScope.launch {
            db?.phraseDao()?.deleteFavPhraseByPhraseId(phrase?.id!!)
            phrase?.isFavorite = false
            withContext(Dispatchers.Main) {
                subscribers.forEach {
                    it.phrasesUpdated(mutableListOf(phrase))
                }
            }

            Log.d("Database", "Phrase " + phrase?.name + " deleted")

        }
    }

    fun loadFavoritesPhrases() {
        GlobalScope.launch {
            var favs = db?.phraseDao()?.getFavPhrases()
            var favorites = mutableListOf<Phrase?>()
            favs?.forEach { favorites.add(db?.phraseDao()?.findById(it.phraseId!!)) }
            withContext(Dispatchers.Main) {
                notifyFavoritesPhrasesLoaded(favorites)
            }
        }

    }

    fun getPhrasesByIds(phraseIds: IntArray) = runBlocking {
        var list = mutableListOf<Phrase?>()
        GlobalScope.async {
            list = db!!.phraseDao().loadAllByIds(phraseIds).toMutableList()
        }.await()

        return@runBlocking list
    }

    fun getPhraseById(id: Long) = runBlocking {
        var phrase: Phrase? = null
        GlobalScope.async {
            phrase = db!!.phraseDao().findById(id)
        }.await()

        return@runBlocking phrase
    }

    fun addPhrase(phrase: Phrase) {
        GlobalScope.launch {
            db!!.phraseDao().insertPhrase(phrase)
        }
    }

    fun subscribeOnPhraseChange(presenter: SubscribablePresenterInterface) {
        subscribers.add(presenter)
    }

    private fun notifyFavoritesPhrasesLoaded(list: List<Phrase?>) {
        subscribers.forEach { it.favoritesPhrasesLoaded(list) }
    }

    private fun notifyPhrasesListReady() {
        subscribers.forEach { it.phraseListReady() }
    }


}