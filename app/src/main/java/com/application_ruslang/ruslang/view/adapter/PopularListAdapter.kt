package com.application_ruslang.ruslang.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.interfaces.presenterInterface.PopularPresenterInterface
import com.application_ruslang.ruslang.presenter.PopularPresenter

class PopularListAdapter(var context: Context?, var presenter: PopularPresenterInterface?) :
    RecyclerView.Adapter<PopularListAdapter.ViewHolder>() {

    private var phrases: List<Phrase?> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.phrase_popular_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return phrases.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(phrases[position]!!)

        holder.itemView.setOnClickListener {

            presenter?.itemClicked(phrases[position])
        }

    }

    fun setList(list: List<Phrase?>) {
        phrases = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mName: TextView? = null
        private var mDefinition: TextView? = null

        init {
            mName = itemView.findViewById(R.id.tv_phrase_popular_name)
            mDefinition = itemView.findViewById(R.id.tv_phrase_popular_definition)
        }

        fun bind(phrase: Phrase) {
            mName?.text = phrase.name
            mDefinition?.text = phrase.definition
        }
    }

}