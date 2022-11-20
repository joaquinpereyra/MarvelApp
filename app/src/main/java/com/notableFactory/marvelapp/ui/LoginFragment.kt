package com.notableFactory.marvelapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.notableFactory.marvelapp.R
import java.lang.ClassCastException

class LoginFragment : Fragment() {

    private var suggestedEmail: String? = null
    private lateinit var listener: OnLoginFragmentInteractionListener
    private lateinit var emailBox:EditText
    private lateinit var passwordBox:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            suggestedEmail = it.getString("email")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signInButton = view.findViewById<Button>(R.id.SignInButton)
        val singUpButton = view.findViewById<Button>(R.id.registerButton)
        emailBox = view.findViewById<EditText>(R.id.editTextEmail)
        passwordBox = view.findViewById<EditText>(R.id.editTextPassword)

        signInButton.setOnClickListener {
            val areFormFieldsFilled = checkFormFields()
            if (areFormFieldsFilled) {
                listener.logInUser(emailBox.text.toString(), passwordBox.text.toString())
            }
        }

        singUpButton.setOnClickListener {
            listener.switchToRegisterForm()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginFragmentInteractionListener) {
            listener = context
        }
        else {
            throw ClassCastException("Must implement LoginFragment.OnLoginFragmentInteractionListener")
        }

    }

    interface OnLoginFragmentInteractionListener {
        fun logInUser(email: String, password: String)

        fun switchToRegisterForm()
    }

    private fun checkFormFields() : Boolean {
        var areFormFieldsValid:Boolean
        val emailText = emailBox.text

        val emailMatchesValidValidFormat = Patterns.EMAIL_ADDRESS.matcher(emailText).matches()
        val isEmailEmpty = emailBox.text.isNotEmpty()
        val isEmailValid = isEmailEmpty && emailMatchesValidValidFormat
        val isPasswordValid = passwordBox.text.isNotEmpty()
        areFormFieldsValid = isEmailValid && isPasswordValid && emailMatchesValidValidFormat

        if (!emailMatchesValidValidFormat) {
            emailBox.error = "Invalid email";
        }

        if (!isEmailEmpty) {
            emailBox.error = "Email is required";
        }

        if (!isPasswordValid) {
            passwordBox.error = "Password is required";
        }

        return areFormFieldsValid
    }
}