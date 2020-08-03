package com.application_ruslang.ruslang.interfaces

import com.application_ruslang.ruslang.Phrase

interface SearchFragmentPresenterInterface {
    fun searchStringUpdated()
    fun randomClicked()
    fun lastItemScrolled()
    fun addToFavorite(phrase: Phrase?)
}