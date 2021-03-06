package com.application_ruslang.ruslang.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.App
import com.application_ruslang.ruslang.view.adapter.FavoritesListAdapter
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.interfaces.presenterInterface.FavoritesPresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.FavoritesFragmentInterface
import com.application_ruslang.ruslang.presenter.FavoritesPresenter

class FavoritesFragment() : Fragment(), FavoritesFragmentInterface {

    private var adapter: FavoritesListAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var presenter: FavoritesPresenterInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FavoritesPresenter(this)
        adapter = FavoritesListAdapter(context, presenter!!)
        recyclerView = view.findViewById(R.id.rv_favorites)

        recyclerView?.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(App.applicationContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager
        presenter?.viewIsReady()

    }

    override fun setList(list: MutableList<Phrase?>) {
        adapter?.setList(list)
    }

    override fun updateList(list: List<Phrase?>) {
        adapter?.updateList(list)
    }

}