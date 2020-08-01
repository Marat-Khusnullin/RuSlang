package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.view.NewPhraseFragment

class NewPhrasePresenter(var view: NewPhraseFragment) {

    var model: FirebaseModel

    init {
        model = FirebaseModel()
    }

    fun addPhrase(phrase: Phrase) {
        model.addPhrase(phrase)

    }
}