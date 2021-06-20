package com.example.task_planner_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.task_planner_app.R
import com.example.task_planner_app.databinding.FragmentRegisterBinding
import com.example.task_planner_app.ui.main.MainActivity
import com.example.task_planner_app.viewmodel.LoginActivityViewModel
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val loginViewModel by viewModels<LoginActivityViewModel>()
    private val mainViewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.buttonRegister.setOnClickListener {
            val numberId = binding.insertNumberIdRegister.text.toString()
            val fullName = binding.insertFullNameRegister.text.toString()
            val email = binding.insertEmailRegister.text.toString()
            val password = binding.insertPasswordRegister.text.toString()
            val confirmPassword = binding.confirmPasswordRegister.text.toString()
            validateRegisterFields(numberId, fullName, email, password, confirmPassword)

            mainViewModel.successLiveData.observe(viewLifecycleOwner, { registerSuccessful ->
                if (registerSuccessful) {
                    goMainActivityFromLoginActivity()
                }
            })
            mainViewModel.createUser(numberId, fullName, email, password)
        }

        binding.textLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    private fun goMainActivityFromLoginActivity() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun validateRegisterFields(numberId: String, fullName: String, email: String, password: String, confirmPassword: String): Boolean {
        when {
            numberId.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                FancyToast.makeText(
                    activity,
                    "Please, fill all fields",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                FancyToast.makeText(
                    activity,
                    "Please, insert a valid email",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }

            password != confirmPassword -> {
                FancyToast.makeText(
                    activity,
                    "Passwords must be equals",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }
            password.length < 6 -> {
                FancyToast.makeText(
                    activity,
                    "Password must have 6 characters at least",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }
        }
        return true
    }
}