package com.cbre.jday.githubexplorer_android.ui.feed

import android.app.Activity
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.app.inflate
import com.cbre.jday.githubexplorer_android.model.GithubEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_event.view.*
import java.text.DateFormat


class ActivityFeedAdapter(val events: MutableList<GithubEvent>) : RecyclerView.Adapter<ActivityFeedAdapter.ViewHolder>() {

    interface ClickListener {
        fun onItemClick(position: Int, view: View)
    }

    private var clickListener: ClickListener? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_event))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun updateEvents(events: List<GithubEvent>) {
        this.events.clear()
        this.events.addAll(events)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private lateinit var event: GithubEvent

        fun bind(event: GithubEvent) {
            itemView.setOnClickListener(this)
            this.event = event

            val avatar = itemView.findViewById<ImageView>(R.id.avatar)
            avatar.clipToOutline = true

            val imageUrl = Uri.parse(event.actor.avatarURL.toString())
            Picasso.with(itemView.context).load(imageUrl).into(avatar)
            itemView.creationDate.text = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(event.creationDate).toString()
            itemView.username.text = event.actor.login
            itemView.repoName.text = event.repo.name
        }


        override fun onClick(view: View) {
            clickListener?.onItemClick(adapterPosition, view)
        }
    }
}