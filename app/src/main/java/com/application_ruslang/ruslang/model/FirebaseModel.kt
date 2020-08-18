package com.application_ruslang.ruslang.model

import android.util.Log
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.TrendData
import com.application_ruslang.ruslang.interfaces.SubscribablePresenterInterface
import com.application_ruslang.ruslang.presenter.PhraseTrendPresenter
import com.application_ruslang.ruslang.presenter.PopularFragmentPresenter
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

class FirebaseModel() {
    private val db: FirebaseFirestore
    private val model: Model
    private var subscribers: MutableList<SubscribablePresenterInterface?> = mutableListOf()
    private val POPULAR_PHRASES_COUNT: Long = 5

    companion object {
        val instance = FirebaseModel()
    }

    init {
        db = FirebaseFirestore.getInstance()
        model = Model.instance
    }

    fun loadPopularPhrases() {
        val listOfPhrases = mutableListOf<Phrase>()
        db.collection("trend").orderBy("totalViews", Query.Direction.DESCENDING).limit(POPULAR_PHRASES_COUNT)
            .get().addOnSuccessListener {
                it.documents.forEach { doc ->
                    val phrase = model.getPhraseById(doc.id.toLong())
                    if (phrase != null)
                        listOfPhrases.add(phrase)
                }
                notifyAboutPopularPhrases(listOfPhrases)
            }
    }

    fun addPhrase(phrase: Phrase) {
        db.collection("suggested").add(phrase)
    }

    fun loadTrendInfo(phrase: Phrase) {
        val data = db.collection("trend")
        data.document("" + phrase.id)
            .get().addOnSuccessListener { doc ->

                val trendData = TrendData(
                    totalViews = doc.getLong("totalViews"),
                    totalFavs = doc.getLong("totalFavs")
                )
                val months =
                    db.collection("trend").document(phrase.id.toString()).collection("months")
                months.document(Calendar.getInstance().get(Calendar.MONTH).toString()).get()
                    .addOnSuccessListener {
                        trendData.monthViewsCount = it.getLong("monthViewsCount")

                        trendData.monthFavsCount = it.getLong("monthFavsCount")

                        months.get().addOnSuccessListener { month ->
                            month.documents.forEach { doc ->
                                trendData.monthsViews.set(
                                    doc.id.toLong(),
                                    doc.getLong("monthViewsCount")
                                )
                            }
                        }
                        db.collection("trend").orderBy("totalViews", Query.Direction.DESCENDING)
                            .get().addOnSuccessListener {
                                phrase.rating = it.documents.indexOfFirst { it.id == phrase.id.toString() }.toDouble() +1
                                phrase.trendData = trendData
                                notifyAboutTrendInfo(phrase)
                            }

                    }

            }
    }

    fun noticeViewing(phrase: Phrase) {
        val doc = db.collection("trend").document("" + phrase.id)
        doc.get().addOnCompleteListener {
            if (it.result?.exists() == true) {
                doc.update("totalViews", FieldValue.increment(1))
            } else {
                doc.set(TrendData(totalViews = 1))
            }
            val month = doc.collection("months")
                .document(Calendar.getInstance().get(Calendar.MONTH).toString())
            month.get().addOnCompleteListener {
                if (it.result?.exists() == true) {
                    month.update("monthViewsCount", FieldValue.increment(1))
                } else {
                    month.set(TrendData(monthViewsCount = 1))
                }
            }
        }
    }

    fun noticeAddingToFavorites(phrase: Phrase?) {
        val doc = db.collection("trend").document("" + phrase?.id)
        doc.get().addOnCompleteListener { it ->
            if (it.result?.exists() == true) {
                doc.update("totalFavs", FieldValue.increment(1))
            } else {
                doc.set(TrendData(totalFavs = 1))
            }
            val month = doc.collection("months")
                .document(Calendar.getInstance().get(Calendar.MONTH).toString())
            month.get().addOnCompleteListener {
                if (it.result?.exists() == true) {
                    month.update("monthFavsCount", FieldValue.increment(1))
                } else {
                    month.set(TrendData(monthFavsCount = 1))
                }
            }
        }
    }

    fun noticeRemovingFromFavorites(phrase: Phrase?) {
        db.collection("trend").document("" + phrase?.id).collection("months")
            .document("" + Calendar.getInstance().get(Calendar.MONTH))
            .update("monthFavsCount", FieldValue.increment(-1))
        db.collection("trend").document("" + phrase?.id)
            .update("totalFavs", FieldValue.increment(-1))
    }

    fun subscribeOnChanges(presenter: SubscribablePresenterInterface) {
        subscribers.add(presenter)
    }

    private fun notifyAboutPopularPhrases(popularPhrases: List<Phrase>) {
        subscribers.forEach { it?.popularPhrasesLoaded(popularPhrases)}
    }

    private fun notifyAboutTrendInfo(phrase: Phrase) {
        subscribers.forEach { it?.phraseTrendInfoLoaded(phrase)}
    }


}