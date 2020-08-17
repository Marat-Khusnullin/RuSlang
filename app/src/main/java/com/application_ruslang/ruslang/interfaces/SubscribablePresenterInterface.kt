package com.application_ruslang.ruslang.interfaces

import com.application_ruslang.ruslang.Phrase

interface SubscribablePresenterInterface {

    fun phrasesUpdated(list: MutableList<Phrase?>)
    fun popularPhrasesLoaded(list: List<Phrase>)
    fun favoritesPhrasesLoaded(list: List<Phrase?>)
    fun phraseTrendInfoLoaded(phrase: Phrase)
    fun phraseListReady()

}