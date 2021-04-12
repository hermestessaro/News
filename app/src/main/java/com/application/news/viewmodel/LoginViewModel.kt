package com.application.news.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
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

    val errorMessage = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.builder().contextModule(ContextModule(application)).build().inject(this)
    }

    fun validateLogin(email:String, password: String){
        loading.postValue(true)
        if(password == "" || email == ""){
            loading.postValue(false)
            errorMessage.postValue("Please fill all fields")
            return
        }
        val request = LoginRequest(email, password)
        CoroutineScope(Dispatchers.IO).launch {
            login(request)
        }
    }

    fun validateSignup(email: String, password: String, confirmPass: String){
        loading.postValue(true)
        if(password != confirmPass){
            errorMessage.postValue("The passwords are different")
            loading.postValue(false)
            return
        }
        if(password == "" || email == "" || confirmPass == ""){
            loading.postValue(false)
            errorMessage.postValue("Please fill all fields")
            return
        }
        val request = SignUpRequest("mock_name", email, password)
        CoroutineScope(Dispatchers.IO).launch {
            signup(request)
        }
    }

    private suspend fun login(request: LoginRequest){
        try {
            val response = newsService.signIn(request)
            return withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful){
                        response.body()?.let{
                            loading.postValue(false)
                            token.postValue(it.token)
                        }
                    } else {
                        response.errorBody()?.let{
                            loading.postValue(false)
                            errorMessage.postValue(it.string())
                        }
                    }
                } catch (e: Throwable){
                    e.printStackTrace()
                }
            }
        } catch (e: Throwable){
            errorMessage.postValue("An error occurred. Check your connection")
        }
    }

    private suspend fun signup(request: SignUpRequest){
        try {
            val response = newsService.signUp(request)
            return withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful){
                        response.body()?.let{
                            loading.postValue(false)
                            token.postValue(it.token)
                        }
                    } else {
                        response.errorBody()?.let{
                            loading.postValue(false)
                            errorMessage.postValue(it.string())
                        }
                    }
                } catch (e: Throwable){
                    e.printStackTrace()
                }
            }
        } catch (e: Throwable){
            errorMessage.postValue("An error occurred. Check your connection")
        }

    }

}