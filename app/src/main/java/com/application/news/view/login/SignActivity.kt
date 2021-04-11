package com.application.news.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.application.news.R
import com.application.news.util.SessionManager
import com.application.news.view.feed.FeedActivity
import com.application.news.viewmodel.LoginViewModel

class SignActivity: AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        setContentView(R.layout.activity_sign)
        navController = findNavController(R.id.sign_nav_host_fragment_container)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.token.observe(this, {
            saveToken(it)
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
        })
    }

    private fun saveToken(token: String){
        SessionManager(this).saveAuthToken(token)

    }
}