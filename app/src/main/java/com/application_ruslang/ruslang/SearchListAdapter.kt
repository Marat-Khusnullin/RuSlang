package com.application_ruslang.ruslang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.phrase_item.view.*

class SearchListAdapter(var phrases: MutableList<Phrase?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM)
            return SearchViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.phrase_list_item, parent, false)
            ) else
            return LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.loading_item, parent, false)
            )
    }

    override fun getItemCount(): Int {
        return phrases.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(phrases[position] != null)
            VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is SearchViewHolder) {
            holder.bind(phrases[position])
            holder.itemView.setOnClickListener {

            }
        } else {

        }

    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mName: TextView? = null
        private var mDefinition: TextView? = null

        init {
            mName = itemView.findViewById(R.id.tv_phrase_list_name)
            mDefinition = itemView.findViewById(R.id.tv_phrase_list_definition)
        }

        fun bind(phrase: Phrase?) {
            mName?.text = phrase?.name
            mDefinition?.text = phrase?.definition
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mProgressBar: ProgressBar? = null

        init {
            mProgressBar = itemView.findViewById(R.id.progress_phrase_load)
        }
    }

    fun setList(list: MutableList<Phrase?>) {
        phrases = list;
        notifyDataSetChanged()
    }

    fun addPhrases(list: List<Phrase?>) {
        phrases.removeAt(phrases.lastIndex)
        list.forEach {
            phrases.add(it)
        }
        notifyDataSetChanged()
    }

    fun addPhrase(phrase: Phrase?) {
        phrases.add(phrase)
        notifyItemChanged(phrases.lastIndex)
    }


}