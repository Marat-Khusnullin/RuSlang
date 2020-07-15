package com.application_ruslang.ruslang.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R


class PhraseFragment(private var phrase: Phrase?) : Fragment() {

    private var name: TextView? = null
    private var definition: TextView? = null
    private var type: TextView? = null
    private var group: TextView? = null
    private var examples: TextView? = null
    private var hashtags: TextView? = null
    private var origin: TextView? = null
    private var synonyms: TextView? = null
    private var toolbar: Toolbar? = null
    private var toolbarPhraseName: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phrase_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.tv_phrase_name)
        definition = view.findViewById(R.id.tv_phrase_definition)
        type = view.findViewById(R.id.tv_phrase_type)
        group = view.findViewById(R.id.tv_phrase_group)
        examples = view.findViewById(R.id.tv_phrase_examples)
        hashtags = view.findViewById(R.id.tv_phrase_hashtags)
        origin = view.findViewById(R.id.tv_phrase_origin)
        synonyms = view.findViewById(R.id.tv_phrase_synonyms)
        toolbar = view.findViewById(R.id.toolbar_phrase)
        toolbarPhraseName = view.findViewById(R.id.tv_toolbar_phrase_name)

        toolbarPhraseName?.text = phrase?.name
        name?.text = phrase?.name
        definition?.text = phrase?.definition
        //type?.text = phrase?.type
        //group?.text = phrase?.group
        val typeface = Typeface.createFromAsset(context?.assets, "OpenSans-Italic.ttf")
        examples?.typeface = typeface
        if (phrase?.examples == "")
            examples?.visibility = View.GONE
        examples?.text = "примеры: " + phrase?.examples
        hashtags?.text = phrase?.hashtags
        if (phrase?.origin == "")
            origin?.visibility = View.GONE
        origin?.text = phrase?.origin
        //synonyms?.text = phrase?.synonyms


    }


}