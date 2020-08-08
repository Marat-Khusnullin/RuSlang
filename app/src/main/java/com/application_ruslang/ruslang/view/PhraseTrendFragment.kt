package com.application_ruslang.ruslang.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.R
import com.github.mikephil.charting.charts.BarChart

class PhraseTrendFragment: Fragment() {

    private var name: TextView? = null
    private var monthCount: TextView? = null
    private var favCount: TextView? = null
    private var ratingPlace: TextView? = null
    private var graph: BarChart? = null

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
        


    }

}