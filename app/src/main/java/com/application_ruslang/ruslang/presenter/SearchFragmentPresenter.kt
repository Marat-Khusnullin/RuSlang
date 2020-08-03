package com.application_ruslang.ruslang.presenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.interfaces.SearchFragmentPresenterInterface
import com.application_ruslang.ruslang.interfaces.SearchViewInterface
import com.application_ruslang.ruslang.model.Model

class SearchFragmentPresenter(private val view: SearchViewInterface) :
    SearchFragmentPresenterInterface {

    val OBJ_COUNT = 30
    var currentIndex: Int = 0
    var model: Model =
        Model(this)


    init {
       val broadcastReceiver = object : BroadcastReceiver() {
           override fun onReceive(p0: Context?, p1: Intent?) {
               //Log.d("QW", "!!!!")
               filteredListUpdated()
           }
       }
        App.applicationContext().registerReceiver(broadcastReceiver, IntentFilter("1"))
    }

    override fun searchStringUpdated() {
        currentIndex = 0
        val searchString = view.getSearchString()

        if (searchString == "") {
            val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
            view.updateList(list)
            currentIndex += list.size
        } else {
            model.setSearchString(searchString.toLowerCase())
        }
    }

    fun filteredListUpdated() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.updateList(list)
    }

    override fun randomClicked() {
        currentIndex = 0
        model.getRandomPhrase()
        //view.updateList(mutableListOf(model.getRandomPhrase()))
    }

    override fun lastItemScrolled() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.loadExtraPhrases(list)
    }




}