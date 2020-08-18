package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.view.PopularFragment

class PopularFragmentPresenter(var view: PopularFragment): SubscribablePresenterInterface {

    private val model: FirebaseModel

    init {
        model = FirebaseModel.instance
        model.subscribeOnChanges(this)
    }

    fun viewIsReady(){
        model.loadPopularPhrases()
    }

    fun reloadPhrases(){
        model.loadPopularPhrases()
    }

    override fun popularPhrasesLoaded(list: List<Phrase>) {
        view.updateList(list)
    }

    fun itemClicked(phrase: Phrase?) {
        view.navigateToPhraseFragment(phrase)
    }

    override fun favoritesPhrasesLoaded(list: List<Phrase?>) {

    }

    override fun phraseTrendInfoLoaded(phrase: Phrase) {

    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {

    }

    override fun phraseListReady() {

    }

}