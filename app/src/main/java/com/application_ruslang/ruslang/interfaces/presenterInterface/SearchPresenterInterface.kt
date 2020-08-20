package com.application_ruslang.ruslang.interfaces.presenterInterface

import com.application_ruslang.ruslang.Phrase

interface SearchPresenterInterface {
    fun searchStringUpdated()
    fun randomClicked()
    fun lastItemScrolled()
    fun addToFavorites(phrase: Phrase?)
    fun removeFromFavorites(phrase: Phrase?)
    fun shareButtonClicked(phrase: Phrase?)
    fun itemClicked(phrase: Phrase?)
    fun backToListClicked()
    fun viewIsReady()
}