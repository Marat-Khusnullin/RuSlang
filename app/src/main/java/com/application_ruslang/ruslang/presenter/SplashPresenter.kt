package com.application_ruslang.ruslang.presenter

import android.os.Handler
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.SplashActivity
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface

class SplashPresenter(var view: SplashActivity): SubscribablePresenterInterface {
    private val SPLASH_TIMEOUT: Long = 3000;

    fun viewIsReady() {
        Handler().postDelayed({
            view.navigateToMainActivity()
        }, SPLASH_TIMEOUT)

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

    }

}