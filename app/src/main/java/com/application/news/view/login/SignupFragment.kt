package com.application.news.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.application.news.databinding.FragmentSignupBinding
import com.application.news.viewmodel.LoginViewModel

class SignupFragment: Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMessage.postValue("")

        binding.signup.setOnClickListener {
            viewModel.validateSignup(binding.username.text.toString(), binding.password.text.toString(), binding.passwordConfirmation.text.toString())
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            binding.errorText.text = it
        })
    }
}