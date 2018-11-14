package com.cbre.jday.githubexplorer_android.activity.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.cbre.jday.githubexplorer_android.R
import com.cbre.jday.githubexplorer_android.activity.ui.main.MainActivity
import com.cbre.jday.githubexplorer_android.api.LoginManager
import com.cbre.jday.githubexplorer_android.model.AuthCredential
import com.cbre.jday.githubexplorer_android.model.LoginError
import com.cbre.jday.githubexplorer_android.model.Status



class LoginActivity : AppCompatActivity() {

    private lateinit var usernameTextField: EditText
    private lateinit var passwordTextField: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameTextField = findViewById(R.id.username_text)
        passwordTextField = findViewById(R.id.password_text)
    }


    @Suppress("UNUSED_PARAMETER")
    fun loginButtonTapped(view: View) {
        val username = usernameTextField.text.toString()
        val password = passwordTextField.text.toString()
        val credential = AuthCredential(username, password)

        LoginManager.login(credential) { result ->
            if (result.status == Status.SUCCESS) {

                val user = result.data

                if (user != null) {
                    println("returned user: ${user.username}")

                    // user logged in successfully -- start MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // TODO: clean this up
                    val errorMessage: String = when (result.error) {
                        is LoginError -> result.error.message()
                        else -> LoginError.UNKNOWN.message()
                    }

                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }

            } else {
                // TODO: clean this up
                val errorMessage: String = when (result.error) {
                    is LoginError -> result.error.message()
                    else -> LoginError.UNKNOWN.message()
                }

                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
