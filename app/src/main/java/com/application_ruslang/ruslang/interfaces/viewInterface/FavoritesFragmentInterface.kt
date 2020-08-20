package com.application_ruslang.ruslang.interfaces.viewInterface

import com.application_ruslang.ruslang.Phrase

interface FavoritesFragmentInterface {

    fun setList(list: MutableList<Phrase?>)
    fun updateList(list: List<Phrase?>)
}