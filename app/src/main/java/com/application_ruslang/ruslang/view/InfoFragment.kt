package com.application_ruslang.ruslang.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.view.informationView.AboutDevFragment
import com.application_ruslang.ruslang.view.informationView.AboutProgramFragment

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
                (context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.animator.ffrmnt_nmtr,
                        R.animator.fragment_remove
                    )
                    .add(
                        R.id.container,
                        AboutProgramFragment()
                    ).addToBackStack(null)
                    .commit()
            }

            tvbtnAboutDeveloper?.id -> {
                (context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.animator.ffrmnt_nmtr,
                        R.animator.fragment_remove
                    )
                    .add(
                        R.id.container,
                        AboutDevFragment()
                    ).addToBackStack(null)
                    .commit()
            }

            tvbtnMakeReview?.id -> {

            }
        }
    }

}