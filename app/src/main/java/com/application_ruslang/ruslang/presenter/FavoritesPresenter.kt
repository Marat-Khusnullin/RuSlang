package com.application_ruslang.ruslang.presenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.Model
import com.application_ruslang.ruslang.view.FavoritesFragment

class FavoritesPresenter(var view: FavoritesFragment) {

    var model: Model

    init {
        model = Model.instance
        model.pr = this

    }

    fun viewIsReady() {
        model.getFavoritesPhrases()

    }

    fun setList(list: MutableList<Phrase>) {
        view.updateList(list)

    }

    fun addToFavorite(phrase: Phrase){
        model.switchFavoriteStatus(phrase)
    }
}