package com.application.news.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.application.news.databinding.FragmentLoginBinding
import com.application.news.viewmodel.LoginViewModel

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            viewModel.validateLogin(binding.username.text.toString(), binding.password.text.toString())
        }

        binding.signupText.setOnClickListener{
            val action = LoginFragmentDirections.actionSignupFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            binding.errorText.text = it
        })
    }
}