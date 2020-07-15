package com.application_ruslang.ruslang.model

import android.util.Log
import com.application_ruslang.ruslang.POKOPhrase
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.presenter.PopularFragmentPresenter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FirebaseModel(var presenter: PopularFragmentPresenter) {
    private val db: FirebaseFirestore

    init {
        db = FirebaseFirestore.getInstance()

        /*Log.e("LOL", "DADADA")
        var a = Phrase("Тест имя 5.0", "тест 2", "тест 3", "тест 4", "тест5", "тест6", "тест7", "тест8", "тест9", _rating = 5.0)
        db.collection("popular").add(a)
        a = Phrase("Тест имя 4.0", "тест 2", "тест 3", "тест 4", "тест5", "тест6", "тест7", "тест8", "тест9", _rating = 4.0)
        db.collection("popular").add(a)
        a = Phrase("Тест имя 3.0", "тест 2", "тест 3", "тест 4", "тест5", "тест6", "тест7", "тест8", "тест9", _rating = 3.0)
        db.collection("popular").add(a)
        a = Phrase("Тест имя 2.0", "тест 2", "тест 3", "тест 4", "тест5", "тест6", "тест7", "тест8", "тест9", _rating = 2.0)
        db.collection("popular").add(a)
        a = Phrase("Тест имя 1.0", "тест 2", "тест 3", "тест 4", "тест5", "тест6", "тест7", "тест8", "тест9", _rating = 1.0)
        db.collection("popular").add(a)*/
    }


    fun increaseRating(phrase: Phrase) {

    }

    fun loadPopularPhrases(){
        db.collection("popular").orderBy("rating", Query.Direction.DESCENDING).limit(3).get().addOnSuccessListener {
            var list = mutableListOf<Phrase>()

            for (document in it) {
                list.add(
                    Phrase(
                        document["name"].toString(),
                        document["definition"].toString(),
                        document["type"].toString(),
                        document["group"].toString(),
                        document["examples"].toString(),
                        document["hashtags"].toString(),
                        document["origin"].toString(),
                        document["synonyms"].toString(),
                        document["id"].toString()
                    )
                )
                Log.d("ELEMENTS", document["name"].toString() + " " + document["id"])
            }
        presenter.loadList(list)
        }.addOnFailureListener {

        }


    }
}