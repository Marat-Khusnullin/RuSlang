package com.application_ruslang.ruslang.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.*
import com.application_ruslang.ruslang.presenter.PopularFragmentPresenter
import com.application_ruslang.ruslang.view.adapter.PopularListAdapter

class PopularFragment : Fragment() {

    var reload: ImageButton? = null
    var recyclerView: RecyclerView? = null
    var adapter: PopularListAdapter? = null
    var presenter: PopularFragmentPresenter? = null

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

        val linearLayoutManager = LinearLayoutManager(App.applicationContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager

        adapter =
            PopularListAdapter(context)
        recyclerView?.adapter = adapter
        presenter = PopularFragmentPresenter(this)

        presenter?.viewIsReady()
    }

    fun updateList(list: List<Phrase?>){
        adapter?.setList(list)
    }





}