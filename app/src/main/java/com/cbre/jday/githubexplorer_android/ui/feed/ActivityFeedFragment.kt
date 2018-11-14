package com.cbre.jday.githubexplorer_android.ui.feed

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
import android.widget.Toast
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.model.GithubError
import com.cbre.jday.githubexplorer_android.model.GithubEvent
import com.cbre.jday.githubexplorer_android.model.Result
import com.cbre.jday.githubexplorer_android.model.Status

import com.cbre.jday.githubexplorer_android.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.activity_feed_fragment.*


class ActivityFeedFragment : Fragment(), ActivityFeedAdapter.ClickListener {

    interface ActivityFragmentInteractionListener {
        fun showDetailFor(event: GithubEvent)
    }


    private var listener: ActivityFragmentInteractionListener? = null
    private lateinit var eventsViewModel: EventsViewModel
    private val adapter = ActivityFeedAdapter(mutableListOf())






    // Fragment Lifecycle

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        activity?.setTitle(getString(R.string.title_activity_feed))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_feed_fragment, container, false)

        eventsViewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setOnItemClickListener(this)
        activityFeedRecyclerView.layoutManager = LinearLayoutManager(context)
        activityFeedRecyclerView.adapter = adapter

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        activityFeedRecyclerView.addItemDecoration(itemDecor)

        eventsViewModel.fetchEvents().observe(this, Observer<Result<List<GithubEvent>>> { result ->
            if (result?.status == Status.SUCCESS && result.data != null) {
                adapter.updateEvents(result.data)
            } else {
                if (result?.error == GithubError.GENERIC) {
                    Toast.makeText(context, "Error Retrieving Events", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setListener(listener: ActivityFragmentInteractionListener) {
        this.listener = listener
    }




    // Respond to clicks

    override fun onItemClick(position: Int, view: View) {
        val currentEvent = adapter.events[position]
        listener?.showDetailFor(currentEvent)
    }
}
