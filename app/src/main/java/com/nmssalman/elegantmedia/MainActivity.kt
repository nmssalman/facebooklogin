package com.nmssalman.elegantmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.nmssalman.elegantmedia.databinding.ActivityMainBinding
import com.nmssalman.elegantmedia.login.LoginFragment
import com.nmssalman.elegantmedia.splash.SplashFragment
import com.nmssalman.elegantmedia.utils.FragmentUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        FragmentUtils.Initialize(supportFragmentManager)
        setFragment(SplashFragment())
    }

    private fun setFragment(fragment: Fragment)
    {
        FragmentUtils.navigateToFragment(fragment)
    }


}