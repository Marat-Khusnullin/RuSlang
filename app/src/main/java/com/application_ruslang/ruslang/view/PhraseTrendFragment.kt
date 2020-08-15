package com.application_ruslang.ruslang.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.presenter.PhraseTrendPresenter

class PhraseTrendFragment(var phrase: Phrase?) : Fragment() {

    private var name: TextView? = null
    private var monthCount: TextView? = null
    private var favCount: TextView? = null
    private var ratingPlace: TextView? = null
    private var back: Button? = null
    private var share: ImageButton? = null
    private var totalViews: TextView? = null
    private var totalFavs: TextView? = null
    private var progressBar: ProgressBar? = null

    private var presenter: PhraseTrendPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phrase_trend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.tv_phrase_name)
        monthCount = view.findViewById(R.id.tv_phrase_month_count)
        favCount = view.findViewById(R.id.tv_phrase_favorites_count)
        ratingPlace = view.findViewById(R.id.tv_phrase_rating_place)
        back = view.findViewById(R.id.btn_back)
        share = view.findViewById(R.id.ib_share)
        totalViews = view.findViewById(R.id.tv_phrase_totalviews)
        totalFavs = view.findViewById(R.id.tv_phrase_totalfavs)
        progressBar = view.findViewById(R.id.pb_trend)


        presenter = PhraseTrendPresenter(this)

        back?.setOnClickListener {
            activity?.onBackPressed()
        }

        phrase?.let { presenter?.loadTrendInfo(it) }
        presenter?.viewIsReady()
    }


    fun refresh() {
        name?.text = phrase?.name
        monthCount?.text = "Просмотров за месяц: " + phrase?.trendData?.monthViewsCount
        totalViews?.text = "Просмотров всего: " + phrase?.trendData?.totalViews
        favCount?.text = "Добавлено в избранное за месяц: " + phrase?.trendData?.monthFavsCount
        totalFavs?.text = "Добавлено в избранное всего: " + phrase?.trendData?.totalFavs
        ratingPlace?.text = "Место в общем рейтинге: запомни брат такую фразу, всё будет, но не сразу"
    }


}