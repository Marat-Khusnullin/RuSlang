package com.application_ruslang.ruslang.interfaces.viewInterface

import com.application_ruslang.ruslang.Phrase

interface PhraseTrendFragmentInterface {
    fun loadInfo(phrase: Phrase)
    fun shareData(string: String)
}