package com.example.videotrimmer.ui.extension

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.showToast(message:String)
{
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.addOnBackPressedCallback(onBackPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
}