package com.application_ruslang.ruslang.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.*
import com.application_ruslang.ruslang.interfaces.SearchFragmentPresenterInterface
import com.application_ruslang.ruslang.interfaces.SearchViewInterface
import com.application_ruslang.ruslang.presenter.SearchFragmentPresenter


class SearchFragment(context: Context) : Fragment(),
    SearchViewInterface {

    private var searchView: SearchView? = null
    private var recyclerView: RecyclerView? = null
    private var presenter: SearchFragmentPresenterInterface? = null
    private var adapter: SearchListAdapter? = null
    private var activityContext: Context? = null
    private var toolbar: Toolbar? = null
    private var random: Button? = null
    private var isLoading: Boolean = false
    private var newPhrase: ImageButton? = null

    init {
        activityContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = getView()?.findViewById(R.id.view_search)
        recyclerView = getView()?.findViewById(R.id.recycler_view)
        toolbar = getView()?.findViewById(R.id.toolbar_search)
        random = getView()?.findViewById(R.id.btn_random)
        newPhrase = getView()?.findViewById(R.id.ib_new_phrase)

        val linearLayoutManager = LinearLayoutManager(App.applicationContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager
        initScrollListener()

        presenter = SearchFragmentPresenter(this)
        presenter?.viewIsReady()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter?.searchStringUpdated()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter?.searchStringUpdated()
                return true
            }
        })

        random?.setOnClickListener {
            presenter?.randomClicked()
        }

        newPhrase?.setOnClickListener {
            moveToNewPhraseFragment()
        }
    }

    override fun getSearchString(): String {
        return searchView?.query.toString()
    }

    override fun updateList(list: MutableList<Phrase?>) {
        if (adapter == null) {
            adapter = SearchListAdapter(list, activityContext, presenter)
            recyclerView?.adapter = adapter
        } else
            adapter?.setList(list)
    }

    private fun initScrollListener() {
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter!!.itemCount - 1 && adapter!!.itemCount > 1) {
                        isLoading = true
                        adapter?.addPhrase(null)
                        Handler().postDelayed({
                            presenter?.lastItemScrolled()
                        }, 1000)
                    }
                }
            }
        })
    }

    override fun loadExtraPhrases(list: MutableList<Phrase?>) {
        adapter?.addPhrases(list)
        isLoading = false
    }

    private fun moveToNewPhraseFragment() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.fragment_from_bottom, R.animator.fragment_remove)
            .add(
                R.id.container,
                NewPhraseFragment()
            ).addToBackStack(null)
            .commit()
    }


}