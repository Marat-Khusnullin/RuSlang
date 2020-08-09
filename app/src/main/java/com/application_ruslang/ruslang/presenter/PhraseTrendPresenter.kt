package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.model.FirebaseModel

class PhraseTrendPresenter() {

    var model: FirebaseModel

    init {
        model = FirebaseModel()



    }

    fun loadTrendInfo(phrase: Phrase) {
        model.loadTrendInfo(phrase)
    }

    fun viewIsReady(){

    }


}