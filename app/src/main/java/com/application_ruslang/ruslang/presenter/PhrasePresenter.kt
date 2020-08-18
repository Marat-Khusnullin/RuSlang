package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.view.PhraseFragment

class PhrasePresenter(var view: PhraseFragment) {

    var model: FirebaseModel = FirebaseModel.instance

    fun shareButtonClicked(phrase: Phrase?) {
        var string = phrase?.name + "\n\n" + phrase?.definition + "\n" + "примеры: " + phrase?.examples + "\n\n" + "синонимы: " + phrase?.synonyms
        view.shareData(string)
    }

    fun viewIsReady(phrase: Phrase) {
        model.noticeViewing(phrase)
    }
}