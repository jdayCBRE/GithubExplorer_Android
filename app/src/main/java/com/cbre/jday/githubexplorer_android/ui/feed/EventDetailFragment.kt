package com.cbre.jday.githubexplorer_android.ui.feed

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.model.GithubEvent
import com.squareup.picasso.Picasso
import java.text.DateFormat


class EventDetailFragment : Fragment() {

    private lateinit var event: GithubEvent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            bundle.getParcelable<GithubEvent>(ARG_EVENT)?.let {
                event = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_detail, container, false)

        val imageUrl = Uri.parse(event.actor.avatarURL.toString())
        val avatar = view.findViewById<ImageView>(R.id.userAvatarImage)
        Picasso.with(context).load(imageUrl).into(avatar)
        avatar.clipToOutline = true

        val timestamp = view.findViewById<TextView>(R.id.timestamp)
        timestamp.text = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(event.creationDate).toString()

        val username = view.findViewById<TextView>(R.id.username)
        username.text = event.actor.login

        val repoName = view.findViewById<TextView>(R.id.repoName)
        repoName.text = event.repo.name

        val commitCount = view.findViewById<TextView>(R.id.commitCount)
        val commits = event.payload.commits?.size.toString()
        commitCount.text = "${commits} commits"

        return view
    }


    companion object {

        const val ARG_EVENT = "event"

        @JvmStatic
        fun newInstance(event: GithubEvent) =
                ActivityFeedFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_EVENT, event)
                    }
                }
    }
}
