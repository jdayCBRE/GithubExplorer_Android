package com.cbre.jday.githubexplorer_android.ui.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.app.inflate
import com.cbre.jday.githubexplorer_android.model.GithubRepo
import kotlinx.android.synthetic.main.list_item_repo.view.*

class SearchResultsAdapter(private val repos: MutableList<GithubRepo>) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_repo))
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }



    fun updateRepos(repos: List<GithubRepo>) {
        this.repos.clear()
        this.repos.addAll(repos)
        notifyDataSetChanged()
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var repo: GithubRepo

        fun bind(repo: GithubRepo) {
            this.repo = repo

            itemView.repo_name.text = repo.fullName
            itemView.starCount.text = repo.stargazerCount.toString()
            itemView.forkCount.text = repo.forks.toString()
            itemView.issueCount.text = repo.openIssues.toString()
        }
    }
}