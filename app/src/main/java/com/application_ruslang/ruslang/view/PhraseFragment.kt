package com.application_ruslang.ruslang.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.presenter.PhrasePresenter


class PhraseFragment(private var phrase: Phrase?) : Fragment() {

    private var name: TextView? = null
    private var definition: TextView? = null
    private var examples: TextView? = null
    private var hashtags: TextView? = null
    private var origin: TextView? = null
    private var synonyms: TextView? = null
    private var back: Button? = null
    private var share: ImageButton? = null
    private var views = mutableListOf<TextView?>()
    private var link: TextView? = null
    private var presenter: PhrasePresenter? = null

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
        views.add(name)
        definition = view.findViewById(R.id.tv_phrase_definition)
        views.add(definition)
        examples = view.findViewById(R.id.tv_phrase_examples)
        views.add(examples)
        hashtags = view.findViewById(R.id.tv_phrase_hashtags)
        views.add(hashtags)
        origin = view.findViewById(R.id.tv_phrase_origin)
        views.add(origin)
        synonyms = view.findViewById(R.id.tv_phrase_synonyms)
        views.add(synonyms)

        back = view.findViewById(R.id.btn_back)
        share = view.findViewById(R.id.ib_share)
        link = view.findViewById(R.id.tv_phrase_teenslang)
        link?.text = "teenslang.su/index.php?searchstr=" + phrase?.name?.replace(" ", "+")
        val typeface = Typeface.createFromAsset(context?.assets, "OpenSans-Italic.ttf")

        name?.text = phrase?.name
        definition?.text = phrase?.definition
        hashtags?.text = phrase?.hashtags
        origin?.text = phrase?.origin

        if (phrase?.examples != "") {
            examples?.text = "примеры: " + phrase?.examples
            examples?.typeface = typeface
        }

        if (phrase?.synonyms != "") {
            synonyms?.text = "синонимы: " + phrase?.synonyms
        }

        views.forEach {
            if (it?.text == "")
                it.visibility = View.GONE
        }

        back?.setOnClickListener { activity?.onBackPressed() }
        share?.setOnClickListener {}

        presenter = PhrasePresenter()
        phrase?.let { presenter?.viewIsReady(it) }

    }


}