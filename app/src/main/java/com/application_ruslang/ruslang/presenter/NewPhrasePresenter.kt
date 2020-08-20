package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.presenterInterface.NewPhrasePresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.NewPhraseFragmentInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.model.Model
import com.application_ruslang.ruslang.view.NewPhraseFragment

class NewPhrasePresenter(var view: NewPhraseFragmentInterface): NewPhrasePresenterInterface {

    var firebaseModel: FirebaseModel
    var model: Model
    init {
        firebaseModel = FirebaseModel.instance
        model = Model.instance
    }

    override fun addPhrase(phrase: Phrase) {
        firebaseModel.addPhrase(phrase)
        model.addPhrase(phrase)

    }
}