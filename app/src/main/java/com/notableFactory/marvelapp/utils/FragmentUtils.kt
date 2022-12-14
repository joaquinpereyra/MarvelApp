package com.notableFactory.marvelapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.addFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    addToBackStackName: String? = null
) {
    beginTransaction().apply {
        add(containerId, fragment)
        if (addToBackStack)
            addToBackStack(addToBackStackName)
        commit()
    }
}

fun FragmentManager.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    addToBackStackName: String? = null
) {
    beginTransaction().apply {
        replace(containerId, fragment)
        if (addToBackStack)
            addToBackStack(addToBackStackName)
        commit()
    }
}


fun FragmentManager.removeFragment(fragment: Fragment) {
    beginTransaction().apply {
        remove(fragment)
        commit()
    }
}

fun replaceFragment(fragment: Fragment, fragmentManager: FragmentManager, containerId: Int) {
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(containerId, fragment)
    fragmentTransaction.commit()
}
