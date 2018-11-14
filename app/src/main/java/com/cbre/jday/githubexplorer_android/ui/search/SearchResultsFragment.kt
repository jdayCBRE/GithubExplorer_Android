package com.cbre.jday.githubexplorer_android.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.model.*

import com.cbre.jday.githubexplorer_android.viewmodel.SearchResultsViewModel
import kotlinx.android.synthetic.main.fragment_searchitem_list.*


class SearchResultsFragment : Fragment() {

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction()
    }


    private lateinit var searchQuery: String
    private lateinit var viewModel: SearchResultsViewModel
    private val adapter = SearchResultsAdapter(mutableListOf())

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var progressBar: ProgressBar




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            searchQuery = it.getString(ARG_SEARCH_QUERY) ?: ""
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_searchitem_list, container, false)
        viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel::class.java)
        progressBar = view.findViewById(R.id.search_results_progress_bar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)
        searchResultsRecyclerView.adapter = adapter

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        searchResultsRecyclerView.addItemDecoration(itemDecor)

        viewModel.fetchSearchResults(searchQuery).observe(this, Observer<Result<GithubRepoItems>> { result ->
            progressBar.visibility = View.GONE

            if (result?.status == Status.SUCCESS && result.data != null) {
                adapter.updateRepos(result.data.items)
            } else {
                if (result?.error == GithubError.GENERIC) {
                    Toast.makeText(context, "Error Retrieving Search Results", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {

        const val ARG_SEARCH_QUERY = "search-query"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(searchQuery: String) =
                SearchResultsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_SEARCH_QUERY, searchQuery)
                    }
                }
    }
}
