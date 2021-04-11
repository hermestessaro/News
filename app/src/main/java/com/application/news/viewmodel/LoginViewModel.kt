package com.application.news.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.news.api.ContextModule
import com.application.news.api.DaggerApiComponent
import com.application.news.api.NewsService
import com.application.news.model.LoginRequest
import com.application.news.model.SignUpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var newsService: NewsService

    val validateError = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val wrongData = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()

    init {
        DaggerApiComponent.builder().contextModule(ContextModule(application)).build().inject(this)
    }

    fun validateLogin(email:String, password: String){
        val request = LoginRequest(email, password)
        CoroutineScope(Dispatchers.IO).launch {
            login(request)
        }
    }

    fun validateSignup(email: String, password: String, confirmPass: String){
        if(password != confirmPass){
            validateError.postValue(true)
        }
        val request = SignUpRequest("hermo", email, password)
        CoroutineScope(Dispatchers.IO).launch {
            signup(request)
        }
    }

    private suspend fun login(request: LoginRequest){
        val response = newsService.signIn(request)
        return withContext(Dispatchers.Main){
            try {
                if(response.isSuccessful){
                    response.body()?.let{
                        token.postValue(it.token)
                    }
                } else {
                    response.errorBody()?.let{
                        errorMessage.postValue(it.string())
                    }
                }
            } catch (e: Throwable){
                e.printStackTrace()
            }
        }
    }

    private suspend fun signup(request: SignUpRequest){
        val response = newsService.signUp(request)
        return withContext(Dispatchers.Main){
            try {
                if(response.isSuccessful){
                    response.body()?.let{
                        token.postValue(it.token)
                    }
                } else {
                    response.errorBody()?.let{
                        errorMessage.postValue(it.string())
                    }
                }
            } catch (e: Throwable){
                e.printStackTrace()
            }
        }
    }

}