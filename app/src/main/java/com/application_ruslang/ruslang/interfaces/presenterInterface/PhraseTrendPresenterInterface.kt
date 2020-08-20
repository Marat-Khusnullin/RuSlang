package com.application_ruslang.ruslang.interfaces.presenterInterface

import com.application_ruslang.ruslang.Phrase

interface PhraseTrendPresenterInterface {

    fun loadTrendInfo(phrase: Phrase)
    fun shareButtonClicked(phrase: Phrase?)
    fun viewIsReady()
}