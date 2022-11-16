package com.notableFactory.marvelapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
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

        val btn = view.findViewById<Button>(R.id.singInButton)
        btn.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.editTextEmail)
            Log.v("MarvelApp", email.text.toString())
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
        fun logInUser()
    }
}