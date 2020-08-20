package com.application_ruslang.ruslang.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.*
import com.application_ruslang.ruslang.interfaces.presenterInterface.PopularPresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.PopularFragmentInterface
import com.application_ruslang.ruslang.presenter.PopularPresenter
import com.application_ruslang.ruslang.view.adapter.PopularListAdapter

class PopularFragment : Fragment(), PopularFragmentInterface {

    var reload: ImageButton? = null
    var recyclerView: RecyclerView? = null
    var adapter: PopularListAdapter? = null
    var presenter: PopularPresenterInterface? = null
    var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_popular)
        reload = view.findViewById(R.id.ib_reload)
        progressBar = view.findViewById(R.id.pb_popular)

        val linearLayoutManager = LinearLayoutManager(App.applicationContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager

        presenter = PopularPresenter(this)
        adapter = PopularListAdapter(context, presenter)
        recyclerView?.adapter = adapter

        reload?.setOnClickListener {
            recyclerView?.visibility = View.GONE
            progressBar?.visibility = View.VISIBLE
            presenter?.reloadPhrases()
        }
        presenter?.viewIsReady()
    }

    override fun updateList(list: List<Phrase?>) {
        recyclerView?.visibility = View.VISIBLE
        adapter?.setList(list)
        progressBar?.visibility = View.GONE
    }

    override fun navigateToPhraseFragment(phrase: Phrase?) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.ffrmnt_nmtr,
                R.animator.fragment_remove
            )
            .add(
                R.id.container,
                PhraseFragment(
                    phrase
                )
            ).addToBackStack(null)
            .commit()
    }


}