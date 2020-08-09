package com.application_ruslang.ruslang.presenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.SearchFragmentPresenterInterface
import com.application_ruslang.ruslang.interfaces.SearchViewInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.model.Model

class SearchFragmentPresenter(private val view: SearchViewInterface) :
    SearchFragmentPresenterInterface {

    val OBJ_COUNT = 30
    var currentIndex: Int = 0
    var model: Model = Model.instance
    var firebaseModel: FirebaseModel = FirebaseModel.instance

    init {
        model.currentPresenter = this
    }

    override fun searchStringUpdated() {
        currentIndex = 0
        val searchString = view.getSearchString()
        model.setSearchString(searchString.toLowerCase())

    }

    fun filteredListUpdated() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.updateList(list)
    }

    override fun randomClicked() {
        currentIndex = 0
        model.getRandomPhrase()
    }

    override fun lastItemScrolled() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.loadExtraPhrases(list)
    }

    override fun addToFavorites(phrase: Phrase?) {
        model.addToFavorites(phrase)
        firebaseModel.noticeAddingToFavorites(phrase)
    }

    override fun removeFromFavorites(phrase: Phrase?){
        model.removeFromFavorites(phrase)
        firebaseModel.noticeRemovingFromFavorites(phrase)
    }

    override fun viewIsReady() {
        //filteredListUpdated()
    }


}