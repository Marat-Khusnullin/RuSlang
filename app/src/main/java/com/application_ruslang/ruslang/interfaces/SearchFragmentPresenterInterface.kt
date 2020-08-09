package com.application_ruslang.ruslang.interfaces

import com.application_ruslang.ruslang.Phrase

interface SearchFragmentPresenterInterface {
    fun searchStringUpdated()
    fun randomClicked()
    fun lastItemScrolled()
    fun addToFavorites(phrase: Phrase?)
    fun removeFromFavorites(phrase: Phrase?)
    fun viewIsReady()
}