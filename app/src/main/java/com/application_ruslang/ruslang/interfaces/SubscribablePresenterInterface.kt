package com.application_ruslang.ruslang.interfaces

import com.application_ruslang.ruslang.Phrase

interface SubscribablePresenterInterface {

    fun phrasesUpdated(list: MutableList<Phrase?>)
    fun phraseListReady()

}