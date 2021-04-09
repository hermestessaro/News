package com.application.news.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.application.news.databinding.FragmentSignupBinding
import com.application.news.viewmodel.LoginViewModel

class SignupFragment: Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: LoginViewModel

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
        viewModel = LoginViewModel(requireActivity().application)

        binding.signup.setOnClickListener {
            viewModel.validateSignup(binding.username.text.toString(), binding.password.text.toString(), binding.passwordConfirmation.text.toString())
        }
    }

    private fun observeViewModel(){
        viewModel.validateError.observe(viewLifecycleOwner, {

        })
    }
}