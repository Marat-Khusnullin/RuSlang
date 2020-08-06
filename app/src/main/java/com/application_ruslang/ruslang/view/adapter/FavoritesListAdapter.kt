package com.application_ruslang.ruslang.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.presenter.FavoritesPresenter
import com.application_ruslang.ruslang.view.PhraseFragment

class FavoritesListAdapter(var context: Context?, var presenter: FavoritesPresenter) :
    RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {

    var phrases: MutableList<Phrase>

    init {
        phrases = mutableListOf()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.phrase_list_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return phrases.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(phrases[position])
        holder.itemView.setOnClickListener {

            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.ffrmnt_nmtr,
                    R.animator.fragment_remove
                )
                .add(
                    R.id.container,
                    PhraseFragment(
                        phrases[position]
                    )
                ).addToBackStack(null)
                .commit()
        }
        holder.favButton.setOnClickListener() {
            presenter.addToFavorite(phrases[position])
            holder.favButton.setBackgroundResource(R.drawable.star)
            phrases.removeAt(position)
            notifyDataSetChanged()
            Log.d("Debuggg", "Fav Button Clicked")
        }
    }

    fun setList(list: MutableList<Phrase>) {
        phrases = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mName: TextView? = null
        private var mDefinition: TextView? = null
        var favButton: ImageView
        var shareButton: ImageView

        init {
            mName = itemView.findViewById(R.id.tv_phrase_list_name)
            mDefinition = itemView.findViewById(R.id.tv_phrase_list_definition)
            favButton = itemView.findViewById(R.id.iv_favorites)
            shareButton = itemView.findViewById(R.id.iv_share)
        }

        fun bind(phrase: Phrase?) {
            mName?.text = phrase?.name
            mDefinition?.text = phrase?.definition
            favButton.setBackgroundResource(R.drawable.fillstar)
        }


    }


}