package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.presenterInterface.SearchPresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.SearchFragmentInterface
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.model.Model

class SearchPresenter(private val view: SearchFragmentInterface) :
    SearchPresenterInterface, SubscribablePresenterInterface {

    val OBJ_COUNT = 30
    var currentIndex: Int = 0
    var model: Model = Model.instance
    var firebaseModel: FirebaseModel = FirebaseModel.instance

    init {
        model.currentPresenter = this
        model.subscribeOnPhraseChange(this)
    }

    override fun searchStringUpdated() {
        currentIndex = 0
        val searchString = view.getSearchString()
        model.setSearchString(searchString.toLowerCase())

    }

    fun filteredListUpdated() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.setList(list)
    }

    override fun randomClicked() {
        currentIndex = 0
        model.getRandomPhrase()
    }

    override fun lastItemScrolled() {

        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.loadExtraPhrases(list)
    }

    override fun addToFavorites(phrase: Phrase?) {
        model.addToFavorites(phrase)
        firebaseModel.noticeAddingToFavorites(phrase)
    }

    override fun removeFromFavorites(phrase: Phrase?){
        model.removeFromFavorites(phrase)
        firebaseModel.noticeRemovingFromFavorites(phrase)
    }

    override fun shareButtonClicked(phrase: Phrase?) {
        var string = phrase?.name + "\n" + phrase?.definition
        view.shareData(string)
    }

    override fun itemClicked(phrase: Phrase?) {
        view.navigateToPhraseFragment(phrase)
    }

    override fun backToListClicked() {
        searchStringUpdated()
    }

    override fun viewIsReady() {
        filteredListUpdated()
    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {
        view.updateList(list)
    }

    override fun popularPhrasesLoaded(list: List<Phrase>) {

    }

    override fun favoritesPhrasesLoaded(list: List<Phrase?>) {

    }

    override fun phraseTrendInfoLoaded(phrase: Phrase) {

    }

    override fun phraseListReady() {
        filteredListUpdated()
    }


}