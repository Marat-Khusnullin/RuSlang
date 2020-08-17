package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.view.PhraseTrendFragment

class PhraseTrendPresenter(var view: PhraseTrendFragment) : SubscribablePresenterInterface {

    var model: FirebaseModel

    init {
        model = FirebaseModel.instance
        model.subscribeOnChanges(this)
    }

    fun loadTrendInfo(phrase: Phrase) {
        model.loadTrendInfo(phrase)
    }

    fun viewIsReady() {

    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {

    }

    override fun popularPhrasesLoaded(list: List<Phrase>) {

    }

    override fun favoritesPhrasesLoaded(list: List<Phrase?>) {

    }

    override fun phraseTrendInfoLoaded(phrase: Phrase) {
        view.loadInfo(phrase)
    }

    override fun phraseListReady() {

    }


}