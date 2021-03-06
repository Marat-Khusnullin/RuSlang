package com.application_ruslang.ruslang.presenter

import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.interfaces.presenterInterface.PhraseTrendPresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.PhraseTrendFragmentInterface
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.view.PhraseTrendFragment

class PhraseTrendPresenter(var view: PhraseTrendFragmentInterface) : SubscribablePresenterInterface, PhraseTrendPresenterInterface {

    var model: FirebaseModel

    init {
        model = FirebaseModel.instance
        model.subscribeOnChanges(this)
    }

    override fun loadTrendInfo(phrase: Phrase) {
        model.loadTrendInfo(phrase)
    }

    override fun shareButtonClicked(phrase: Phrase?) {
        var string = phrase?.name + "\n\n" + "просмотров: " + phrase?.trendData?.totalViews + "\n" + "просмотров за месяц: " + phrase?.trendData?.monthViewsCount +
        "\n" + "в избранном: " + phrase?.trendData?.totalFavs + "\n" + "в избранном за месяц: " + phrase?.trendData?.monthFavsCount + "\n" + "место в рейтинге: " + phrase?.rating?.toInt()
        view.shareData(string)
    }

    override fun viewIsReady() {

    }

    override fun phrasesUpdated(list: MutableList<Phrase?>) {

    }

    override fun popularPhrasesLoaded(list: List<Phrase>) {

    }

    override fun favoritesPhrasesLoaded(list: List<Phrase?>) {

    }

    override fun phraseTrendInfoLoaded(phrase: Phrase) {
        view.loadInfo(phrase)
    }

    override fun phraseListReady() {

    }


}