package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.interfaces.SearchFragmentPresenterInterface
import com.application_ruslang.ruslang.interfaces.SearchViewInterface
import com.application_ruslang.ruslang.model.Model

class SearchFragmentPresenter(private val view: SearchViewInterface) :
    SearchFragmentPresenterInterface {

    val OBJ_COUNT = 30
    var currentIndex: Int = 0
    var model: Model =
        Model(this)

    override fun searchStringUpdated() {
        currentIndex = 0
        val searchString = view.getSearchString()

        if (searchString == "") {
            val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
            view.updateList(list)
            currentIndex += list.size
        } else {
            model.setSearchString(searchString.toLowerCase())
        }
    }

    fun filteredListUpdated() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.updateList(list)
    }

    override fun randomClicked() {
        view.updateList(mutableListOf(model.getRandomPhrase()))
    }

    override fun lastItemScrolled() {
        val list = model.getPhrasesByIndexAndCount(currentIndex, OBJ_COUNT)
        currentIndex += list.size
        view.loadExtraPhrases(list)
    }


}