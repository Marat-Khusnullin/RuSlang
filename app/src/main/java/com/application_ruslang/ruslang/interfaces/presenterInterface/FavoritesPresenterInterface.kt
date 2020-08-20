package com.application_ruslang.ruslang.interfaces.presenterInterface

import com.application_ruslang.ruslang.Phrase

interface FavoritesPresenterInterface {
    fun removeFromFavorite(phrase: Phrase)
    fun viewIsReady()
}