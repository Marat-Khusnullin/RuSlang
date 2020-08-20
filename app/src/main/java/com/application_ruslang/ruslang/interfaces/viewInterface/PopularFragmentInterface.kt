package com.application_ruslang.ruslang.interfaces.viewInterface

import com.application_ruslang.ruslang.Phrase

interface PopularFragmentInterface {
    fun updateList(list: List<Phrase?>)
    fun navigateToPhraseFragment(phrase: Phrase?)

}