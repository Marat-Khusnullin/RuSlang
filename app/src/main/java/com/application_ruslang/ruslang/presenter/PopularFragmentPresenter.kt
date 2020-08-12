package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.view.PopularFragment

class PopularFragmentPresenter(var view: PopularFragment) {

    private val model: FirebaseModel

    init {
        model = FirebaseModel(this)
    }

    fun viewIsReady(){
        model.loadPopularPhrases()
    }

    fun loadList(list: List<Phrase?>) {
        view.updateList(list)
    }

}