package com.cbre.jday.githubexplorer_android.ui.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

import com.cbre.jday.githubexplorer_android.R


class SearchFragment : Fragment(), View.OnClickListener {

    interface SearchFragmentInteractionListener {
        fun searchButtonTapped(searchQuery: String)
    }

    private var listener: SearchFragmentInteractionListener? = null
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText




    // Fragment Lifecycle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchButton = view.findViewById(R.id.button_search)
        searchButton.setOnClickListener(this)
        searchEditText = view.findViewById(R.id.search_input_textfield)

        return view
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentInteractionListener) {
            listener = context

            activity?.setTitle(R.string.tab_title_search)
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    // Respond to clicks

    override fun onClick(view: View) {
        if (view == searchButton) {
            onSearchButtonTapped()
        }
    }

    private fun onSearchButtonTapped() {
        listener?.searchButtonTapped(searchEditText.text.toString())
    }
}
