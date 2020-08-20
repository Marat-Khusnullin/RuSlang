package com.application_ruslang.ruslang.interfaces.presenterInterface

import com.application_ruslang.ruslang.Phrase

interface PhrasePresenterInterface {
    fun shareButtonClicked(phrase: Phrase?)
    fun viewIsReady(phrase: Phrase)
}