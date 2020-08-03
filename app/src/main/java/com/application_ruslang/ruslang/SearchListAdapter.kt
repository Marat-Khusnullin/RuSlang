package com.application_ruslang.ruslang

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.view.PhraseFragment

class SearchListAdapter(var phrases: MutableList<Phrase?>, _context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private val context = _context

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
        return if (phrases[position] != null)
            VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is SearchViewHolder) {
            holder.bind(phrases[position])
            holder.itemView.setOnClickListener {

                (context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.ffrmnt_nmtr, R.animator.fragment_remove)
                    .add(
                        R.id.container,
                        PhraseFragment(
                            phrases[position]
                        )
                    ).addToBackStack(null)
                    .commit()
            }

            val typeface = Typeface.createFromAsset(context?.assets, "OpenSans-Regular.ttf")
            holder.setFont(typeface)
        }

    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mName: TextView? = null
        private var mDefinition: TextView? = null
        private var favButton: ImageView? = null
        private var shareButton: ImageView? = null

        init {
            mName = itemView.findViewById(R.id.tv_phrase_list_name)
            mDefinition = itemView.findViewById(R.id.tv_phrase_list_definition)
            favButton = itemView.findViewById(R.id.iv_favorites)
            shareButton = itemView.findViewById(R.id.iv_share)
        }

        fun bind(phrase: Phrase?) {
            mName?.text = phrase?.name
            mDefinition?.text = phrase?.definition

            mName?.setOnClickListener {
                
            }

        }

        fun setFont(typeface: Typeface) {
            //mName?.typeface = typeface
            //mDefinition?.typeface = typeface
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mProgressBar: ProgressBar? = null

        init {
            mProgressBar = itemView.findViewById(R.id.progress_phrase_load)
        }
    }

    fun setList(list: MutableList<Phrase?>) {
        phrases = list
        Log.d("QWE", "SET LIST")
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