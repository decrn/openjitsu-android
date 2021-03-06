package com.openjitsu.android.openjitsu.ui.user

import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class UserActivity : AppCompatActivity(), LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoginFragment()

        // make status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun showLoginFragment() {
        supportFragmentManager.beginTransaction().replace(android.R.id.content, LoginFragment()).commit()
    }

    override fun showRegisterFragment() {
        supportFragmentManager.beginTransaction().replace(android.R.id.content, RegisterFragment()).commit()
    }

    override fun showProfileFragment() {
        supportFragmentManager.beginTransaction().replace(android.R.id.content, ProfileFragment()).commit()
    }

}
