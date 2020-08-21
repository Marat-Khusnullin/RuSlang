package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.SplashActivity
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.model.Model

class SplashPresenter(var view: SplashActivity): SubscribablePresenterInterface {


    fun viewIsReady() {
        Model.instance.subscribeOnPhraseChange(this)

    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {

    }

    override fun popularPhrasesLoaded(list: List<Phrase>) {

    }

    override fun favoritesPhrasesLoaded(list: List<Phrase?>) {

    }

    override fun phraseTrendInfoLoaded(phrase: Phrase) {

    }

    override fun phraseListReady() {
        view.navigateToMainActivity()
    }

}