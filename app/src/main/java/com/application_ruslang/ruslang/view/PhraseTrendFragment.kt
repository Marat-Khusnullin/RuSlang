package com.application_ruslang.ruslang.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
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
    private var relativeLayout: RelativeLayout? = null

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
        relativeLayout = view.findViewById(R.id.rl_phrase_trend)

        presenter = PhraseTrendPresenter(this)

        back?.setOnClickListener {
            activity?.onBackPressed()
        }

        share?.setOnClickListener {
            presenter?.shareButtonClicked(phrase)
        }

        phrase?.let { presenter?.loadTrendInfo(it) }
        presenter?.viewIsReady()
    }

    fun loadInfo(phrase: Phrase) {
        name?.text = phrase?.name
        monthCount?.text = "за месяц: " + phrase?.trendData?.monthViewsCount
        totalViews?.text = "" + phrase?.trendData?.totalViews
        favCount?.text = "в избранное за месяц: " + phrase?.trendData?.monthFavsCount
        totalFavs?.text = "в избранном: " + phrase?.trendData?.totalFavs
        ratingPlace?.text = "место в общем рейтинге: " + phrase.rating?.toInt()
        relativeLayout?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
    }

    fun shareData(string: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, string)
        sendIntent.type = "text/plain"

        val shareIntent = Intent.createChooser(sendIntent, null)
        context?.startActivity(shareIntent)
    }


}