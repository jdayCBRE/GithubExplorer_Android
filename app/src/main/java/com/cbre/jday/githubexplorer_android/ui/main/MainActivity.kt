package com.cbre.jday.githubexplorer_android.activity.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.activity.ui.login.LoginActivity
import com.cbre.jday.githubexplorer_android.model.GithubEvent
import com.cbre.jday.githubexplorer_android.ui.search.SearchFragment
import com.cbre.jday.githubexplorer_android.ui.feed.ActivityFeedFragment
import com.cbre.jday.githubexplorer_android.ui.feed.EventDetailFragment
import com.cbre.jday.githubexplorer_android.ui.search.SearchResultsFragment
import com.cbre.jday.githubexplorer_android.util.auth.AuthManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), ActivityFeedFragment.ActivityFragmentInteractionListener, SearchFragment.SearchFragmentInteractionListener, SearchResultsFragment.OnListFragmentInteractionListener {

    private val activityFeedFragment = ActivityFeedFragment()
    private val searchFragment = SearchFragment()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment = when (item.itemId) {
            R.id.navigation_search -> searchFragment
            else -> activityFeedFragment
        }
        switchToFragment(fragment)
        true
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.navigation
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        activityFeedFragment.setListener(this)
        switchToFragment(activityFeedFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_login, menu)
        menu?.findItem(R.id.logout_item)?.let { item ->
            AuthManager.getAuthUser()?.let { user ->
                item.setTitle("Log Out ${user.username}")
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.logout_item) {
            AuthManager.clearAuthToken()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }




    // Fragment Interaction -- Activity Fragment

    override fun showDetailFor(event: GithubEvent) {
        var bundle = Bundle()
        bundle.putParcelable(EventDetailFragment.ARG_EVENT, event)

        val detailFragment = EventDetailFragment()
        detailFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(activityFeedFragment.id, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.title = event.actor.login
        toolbar.setNavigationOnClickListener {
            onBackPressed()
            toolbar.navigationIcon = null
            toolbar.title = getString(R.string.title_activity_feed)
        }
    }





    // Fragment Interaction -- Search Fragment

    override fun searchButtonTapped(searchQuery: String) {
        showSearchResults(searchQuery)
    }

    private fun showSearchResults(searchQuery: String) {
        var bundle = Bundle()
        bundle.putString(SearchResultsFragment.ARG_SEARCH_QUERY, searchQuery)

        val resultsFragment = SearchResultsFragment()
        resultsFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(searchFragment.id, resultsFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.title = getString(R.string.search_results)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
            toolbar.navigationIcon = null
            toolbar.title = getString(R.string.search_menu_title)
        }
    }




    // Fragment Interaction -- Search Results

    override fun onListFragmentInteraction() {

    }





    // Private Functions

    private fun switchToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment).commit()
    }
}
