package com.application_ruslang.ruslang.interfaces.presenterInterface

import com.application_ruslang.ruslang.Phrase

interface PopularPresenterInterface {

    fun itemClicked(phrase: Phrase?)
    fun reloadPhrases()
    fun viewIsReady()
}