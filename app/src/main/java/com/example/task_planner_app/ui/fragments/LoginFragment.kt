package com.example.task_planner_app.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_planner_app.R
import com.example.task_planner_app.databinding.FragmentLoginBinding
import com.example.task_planner_app.ui.main.LoginActivity
import com.example.task_planner_app.ui.main.MainActivity
import com.example.task_planner_app.utils.Common
import com.example.task_planner_app.viewmodel.LoginActivityViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var loadingDialog : Dialog? = null
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.buttonLogin.setOnClickListener {
            val email = binding.insertEmailLogin.text.toString()
            val password = binding.insertPasswordLogin.text.toString()
            val loginFieldsValidationResult = validateLoginFields(email, password)
            if (loginFieldsValidationResult) {
                binding.buttonLogin.visibility = View.GONE
                showLoading()
                viewModel.auth(email, password)
                viewModel.successLiveData.observe(viewLifecycleOwner, { loginSuccessful ->
                    if (loginSuccessful) {
                        hideLoading()
                        FancyToast.makeText(activity, "Login success!",
                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show()
                        goMainActivityFromLoginActivity()
                    }
                })
            } else {
                FancyToast.makeText(activity, "Please, insert all fields",
                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show()
            }


        }

        binding.textRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun hideLoading() {
        loadingDialog?.let {
            if (it.isShowing)
                it.cancel()
        }
    }

    private fun showLoading() {
        hideLoading()
        loadingDialog = Common.showLoadingDialog(requireContext())
    }

    private fun goMainActivityFromLoginActivity() {
       //val intent = Intent(context, MainActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //startActivity(intent)
        startActivity(Intent(context, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun validateLoginFields(email: String, password: String): Boolean {
        when {
            email.isEmpty() || password.isEmpty() -> {
                FancyToast.makeText(activity, "Please, fill all fields", FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                FancyToast.makeText(activity, "Please, insert a valid email", FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }
            password.length < 6 -> {
                FancyToast.makeText(activity, "Password must have 6 characters at least", FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                return false
            }
        }
        return true
    }
}