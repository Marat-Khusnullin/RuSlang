package com.application_ruslang.ruslang.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.model.FirebaseModel
import com.application_ruslang.ruslang.presenter.PhraseTrendPresenter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class PhraseTrendFragment(var phrase: Phrase?) : Fragment() {

    private var name: TextView? = null
    private var monthCount: TextView? = null
    private var favCount: TextView? = null
    private var ratingPlace: TextView? = null
    private var graph: BarChart? = null
    private var back: Button? = null
    private var share: ImageButton? = null
    private var model: FirebaseModel = FirebaseModel.instance

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
        graph = view.findViewById(R.id.chart)
        back = view.findViewById(R.id.btn_back)
        share = view.findViewById(R.id.ib_share)

        name?.text = phrase?.name
        presenter = PhraseTrendPresenter()

        back?.setOnClickListener {
            activity?.onBackPressed()
        }
        loadInfo()
    }

    fun loadInfo() {

        monthCount?.text = "Просмотров за месяц: "
        favCount?.text = "Добавлено в избранное: "
        ratingPlace?.text = "Место в общем рейтинге: "
        var list: MutableList<BarEntry> = ArrayList()
        list.add(BarEntry(1.0f, 20.0f))
        var list2: MutableList<BarEntry> = ArrayList()
        list2.add(BarEntry(2.0f, 50.0f))
        var dataSet: MutableList<BarDataSet> = ArrayList()
        dataSet.add(BarDataSet(list, "TEST"))
        dataSet.add(BarDataSet(list2, "Ноябрь"))
        var lineData = BarData(dataSet as List<IBarDataSet>?)

        graph?.data = lineData
    }


}