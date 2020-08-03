package com.application_ruslang.ruslang.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.FavoritesListAdapter
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.presenter.FavoritesPresenter
import kotlinx.android.synthetic.main.fragment_favorites.*

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
        adapter = FavoritesListAdapter(context)
        recyclerView = view.findViewById(R.id.rv_favorites)
        presenter = FavoritesPresenter(this)
        recyclerView?.adapter = adapter
        presenter?.viewIsReady()


    }

    fun updateList(list: List<Phrase>) {
        adapter?.setList(list)
    }

}