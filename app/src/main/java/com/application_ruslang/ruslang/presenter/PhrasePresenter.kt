package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.FirebaseModel

class PhrasePresenter {

    var model: FirebaseModel = FirebaseModel.instance

    fun viewIsReady(phrase: Phrase) {
        model.noticeViewing(phrase)
    }
}