package com.application_ruslang.ruslang.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.SearchFragmentPresenter

class InfoFragment : Fragment(), View.OnClickListener {


    var tvbtnAboutProgram: TextView? = null
    var tvbtnAboutDeveloper: TextView? = null
    var tvbtnMakeReview: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvbtnAboutProgram = getView()?.findViewById(R.id.tvbtn_about_program)
        tvbtnAboutDeveloper = getView()?.findViewById(R.id.tvbtn_about_developer)
        tvbtnMakeReview = getView()?.findViewById(R.id.tvbtn_review)

        tvbtnAboutProgram?.setOnClickListener(this)
        tvbtnAboutDeveloper?.setOnClickListener(this)
        tvbtnMakeReview?.setOnClickListener(this)


    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            tvbtnAboutProgram?.id -> {

            }

            tvbtnAboutDeveloper?.id -> {

            }

            tvbtnMakeReview?.id -> {

            }
        }
    }

}