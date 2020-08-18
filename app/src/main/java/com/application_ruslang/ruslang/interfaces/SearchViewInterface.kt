package com.application_ruslang.ruslang.interfaces

import com.application_ruslang.ruslang.Phrase

interface SearchViewInterface {
    fun getSearchString() : String
    fun setList(list: MutableList<Phrase?>)
    fun loadExtraPhrases(list: MutableList<Phrase?>)
    fun updateList(list: MutableList<Phrase?>)
    fun shareData(string: String)
    fun navigateToPhraseFragment(phrase: Phrase?)

}