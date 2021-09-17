package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        binding.buttonLogIn.setOnClickListener { view: View -> this.onClick(view) }
        binding.buttonSignUp.setOnClickListener { view: View -> this.onClick(view) }
        return binding.root
    }

    private fun onClick(view: View) {
        Timber.i("onClick")
        if (binding.editTextEmailAddress.text.isEmpty()) {
            Toast.makeText(activity?.applicationContext, "Not valid user", Toast.LENGTH_SHORT)
                .show()
        } else {
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(binding.editTextEmailAddress.text.toString()))
        }
    }
}