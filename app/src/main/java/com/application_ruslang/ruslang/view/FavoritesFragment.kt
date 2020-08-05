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
import com.application_ruslang.ruslang.presenter.FavoritesPresenter

class FavoritesFragment() : Fragment() {

    private var adapter: FavoritesListAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var presenter: FavoritesPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            FavoritesListAdapter(
                context
            )
        recyclerView = view.findViewById(R.id.rv_favorites)
        presenter = FavoritesPresenter(this)
        recyclerView?.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(App.applicationContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager
        presenter?.viewIsReady()


    }

    fun updateList(list: List<Phrase>) {
        adapter?.setList(list)
    }

}