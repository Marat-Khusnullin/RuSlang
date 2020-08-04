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
        model = Model()
        model.pr = this
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                //Log.d("QW", "!!!!")

            }
        }
        App.applicationContext().registerReceiver(broadcastReceiver, IntentFilter("2"))
    }

    fun viewIsReady() {
        model.getFavoritesPhrases()

    }

    fun setList(list: List<Phrase>) {
        view.updateList(list)
        Log.d("123123", "" + list.size)
    }
}