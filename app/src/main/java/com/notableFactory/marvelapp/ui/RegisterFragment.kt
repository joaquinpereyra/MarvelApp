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

class RegisterFragment : Fragment() {

    private lateinit var listener: RegisterFragment.OnRegisterFragmentInteractionListener
    private lateinit var emailBox:EditText
    private lateinit var passwordBox:EditText
    private lateinit var passwordConfirmationBox:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signInButton = view.findViewById<Button>(R.id.loginButton)
        val signUpButton = view.findViewById<Button>(R.id.CreateAccountButton)

        signInButton.setOnClickListener {
            listener.switchToLoginForm()
        }

        emailBox = view.findViewById<EditText>(R.id.editTextEmail)
        passwordBox = view.findViewById<EditText>(R.id.editTextPassword)
        passwordConfirmationBox = view.findViewById(R.id.editTextPasswordConfirmation)

        signUpButton.setOnClickListener {
            val areFormFieldsFilled = checkFormFields()
            if (areFormFieldsFilled) {
               listener.createUser(emailBox.text.toString(), passwordBox.text.toString())
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegisterFragmentInteractionListener) {
            listener = context
        }
        else {
            throw ClassCastException("Must implement LoginFragment.OnLoginFragmentInteractionListener")
        }
    }

    interface OnRegisterFragmentInteractionListener {
        fun createUser(email: String, password: String)

        fun switchToLoginForm()
    }

    private fun checkFormFields() : Boolean {
        var areFormFieldsValid:Boolean
        val emailText = emailBox.text

        val emailMatchesValidValidFormat = Patterns.EMAIL_ADDRESS.matcher(emailText).matches()
        val isEmailEmpty = emailBox.text.isNotEmpty()
        val isPasswordValid = passwordBox.text.isNotEmpty()
        val isPasswordConfirmationValid = passwordConfirmationBox.text.isNotEmpty()
        val arePasswordsEqual = passwordBox.text.toString() == passwordConfirmationBox.text.toString()
        val isEmailValid = isEmailEmpty && emailMatchesValidValidFormat

        areFormFieldsValid = isEmailValid &&
                isPasswordValid &&
                emailMatchesValidValidFormat &&
                arePasswordsEqual &&
                isPasswordConfirmationValid

        if (!emailMatchesValidValidFormat) {
            emailBox.error = "Invalid email";
        }

        if (!arePasswordsEqual) {
             passwordBox.error = "Passwords must match";
             passwordConfirmationBox.error = "Passwords must match";
        }

        if (!isEmailEmpty) {
            emailBox.error = "Email is required";
        }

        if (!isPasswordValid) {
            passwordBox.error = "Password is required";
        }

        if (!isPasswordConfirmationValid) {
            passwordConfirmationBox.error = "Confirmation is required"
        }

        return areFormFieldsValid
    }
}