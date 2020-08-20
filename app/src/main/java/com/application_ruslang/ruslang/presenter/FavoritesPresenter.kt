package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.interfaces.presenterInterface.FavoritesPresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.FavoritesFragmentInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.model.Model
import com.application_ruslang.ruslang.view.FavoritesFragment

class FavoritesPresenter(var view: FavoritesFragmentInterface) : SubscribablePresenterInterface,
    FavoritesPresenterInterface {

    var model: Model
    var firebaseModel: FirebaseModel

    init {
        model = Model.instance
        firebaseModel = FirebaseModel.instance
        model.subscribeOnPhraseChange(this)

    }

    override fun viewIsReady() {
        model.loadFavoritesPhrases()

    }

    override fun removeFromFavorite(phrase: Phrase) {
        model.removeFromFavorites(phrase)
        firebaseModel.noticeRemovingFromFavorites(phrase)
    }

    override fun favoritesPhrasesLoaded(list: List<Phrase?>) {
        view.setList(list.toMutableList())
    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {
        //view.updateList(list)
    }

    override fun popularPhrasesLoaded(list: List<Phrase>) {

    }


    override fun phraseTrendInfoLoaded(phrase: Phrase) {

    }

    override fun phraseListReady() {

    }
}