package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.model.Model
import com.application_ruslang.ruslang.view.FavoritesFragment

class FavoritesPresenter(var view: FavoritesFragment) {

    var model: Model
    var firebaseModel: FirebaseModel

    init {
        model = Model.instance
        firebaseModel = FirebaseModel.instance
        model.pr = this

    }

    fun viewIsReady() {
        model.getFavoritesPhrases()

    }

    fun setList(list: MutableList<Phrase>) {
        view.updateList(list)

    }

    fun removeFromFavorite(phrase: Phrase){
        model.removeFromFavorites(phrase)
        firebaseModel.noticeRemovingFromFavorites(phrase)
    }
}