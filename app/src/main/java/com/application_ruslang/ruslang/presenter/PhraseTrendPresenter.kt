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
        model.loadTrendInfo(phrase, this)
    }

    fun viewIsReady() {
        //model.loadTrendInfo()
    }

    fun infoLoaded(phrase: Phrase) {
        view.refresh()
    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {
        TODO("Not yet implemented")
    }

    override fun phraseListReady() {
        TODO("Not yet implemented")
    }


}