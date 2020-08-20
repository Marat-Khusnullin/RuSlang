package com.application_ruslang.ruslang.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.Phrase
import com.application_ruslang.ruslang.R
import com.application_ruslang.ruslang.interfaces.presenterInterface.NewPhrasePresenterInterface
import com.application_ruslang.ruslang.interfaces.viewInterface.NewPhraseFragmentInterface
import com.application_ruslang.ruslang.presenter.NewPhrasePresenter
import kotlinx.android.synthetic.main.fragment_new_phrase.*

class NewPhraseFragment: Fragment(), View.OnClickListener, NewPhraseFragmentInterface {

    private var presenter: NewPhrasePresenterInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_phrase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = NewPhrasePresenter(this)
        btn_new_done.setOnClickListener(this)
        btn_new_cancel.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            btn_new_done.id -> { checkPhrase() }
            btn_new_cancel.id -> { getActivity()?.supportFragmentManager?.popBackStack(); }
        }
    }

    private fun checkPhrase(){
        if(et_phrase_name.text.toString() == "") {
            et_phrase_name.setError("Имя не может быть пустым!")
            return
        }
        if(et_phrase_definition.text.toString() == "") {
            et_phrase_definition.setError("Значение не может быть пустым!")
            return
        }

        presenter?.addPhrase(
            Phrase(null,et_phrase_name.text.toString(), et_phrase_definition.text.toString(), et_phrase_type.text.toString(),
        et_phrase_group.text.toString(), et_phrase_examples.text.toString(),et_phrase_hashtags.text.toString(), et_phrase_origin.text.toString(),
        et_phrase_synonyms.text.toString(),0.0)
        )
        getActivity()?.supportFragmentManager?.popBackStack()
    }



}