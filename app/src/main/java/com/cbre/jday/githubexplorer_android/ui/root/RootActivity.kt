package com.cbre.jday.githubexplorer_android.activity.ui.root

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.activity.ui.login.LoginActivity
import com.cbre.jday.githubexplorer_android.activity.ui.main.MainActivity
import com.cbre.jday.githubexplorer_android.util.auth.AuthManager


class RootActivity : AppCompatActivity() {

    private lateinit var startingActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)


        if (AuthManager.isAuthenticated) {
            startingActivity = MainActivity()
        } else {
            startingActivity = LoginActivity()
        }

        val intent = Intent(this, startingActivity::class.java)
        startActivity(intent)
    }
}
