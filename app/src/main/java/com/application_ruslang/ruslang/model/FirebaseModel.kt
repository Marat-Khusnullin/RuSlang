package com.application_ruslang.ruslang.model

import android.util.Log
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.TrendData
import com.application_ruslang.ruslang.presenter.PopularFragmentPresenter
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

class FirebaseModel() {
    private val db: FirebaseFirestore
    private val model: Model
    var presenterr: PopularFragmentPresenter? = null
    private val POPULAR_PHRASES_COUNT: Long = 5

    constructor(presenter: PopularFragmentPresenter) : this() {
        presenterr = presenter
    }

    companion object {
        val instance = FirebaseModel()
    }

    init {
        db = FirebaseFirestore.getInstance()
        model = Model.instance
    }

    fun loadPopularPhrases() {
        var data = db.collection("trend")
        var listOfPhrases = mutableListOf<Phrase?>()
        data.orderBy("totalViews", Query.Direction.DESCENDING).limit(POPULAR_PHRASES_COUNT)
            .get().addOnSuccessListener {
                it.documents.forEach { doc ->
                    var phrase = model.getPhraseById(doc.id.toLong())
                    listOfPhrases.add(phrase)

                    var trendData = TrendData(totalViews = doc.getLong("totalViews"), totalFavs = doc.getLong("totalFavs"))
                    var months = db.collection("trend").document(doc.id).collection("months")
                    months.document(Calendar.getInstance().get(Calendar.MONTH).toString()).get().addOnSuccessListener {
                        trendData.monthViewsCount = it.getLong("monthViewsCount")
                        trendData.monthFavsCount = it.getLong("monthFavsCount")
                    }
                    months.get().addOnSuccessListener { month ->
                        month.documents.forEach { doc ->
                            trendData.monthsViews.set(doc.id.toLong(), doc.getLong("monthViewsCount"))
                        }
                    }
                    phrase?.trendData = trendData
                }
                presenterr?.loadList(listOfPhrases)
            }

    }

    fun addPhrase(phrase: Phrase) {
        db.collection("suggested").add(phrase).addOnSuccessListener {

        }
    }

    fun loadTrendInfo(phrase: Phrase) {

    }

    fun noticeViewing(phrase: Phrase) {
        var doc = db.collection("trend").document("" + phrase.id)
        doc.get().addOnCompleteListener {
            if (it.result?.exists() == true) {
                doc.update("totalViews", FieldValue.increment(1))
            } else {
                doc.set(TrendData(totalViews = 1))
            }
            var month = doc.collection("months")
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
        var doc = db.collection("trend").document("" + phrase?.id)
        doc.get().addOnCompleteListener {
            if (it.result?.exists() == true) {
                doc.update("totalFavs", FieldValue.increment(1))
            } else {
                doc.set(TrendData(totalFavs = 1))
            }
            var month = doc.collection("months")
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
}