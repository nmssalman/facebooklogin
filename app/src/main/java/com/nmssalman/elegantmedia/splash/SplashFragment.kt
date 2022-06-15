package com.nmssalman.elegantmedia.splash

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.nmssalman.elegantmedia.*
import com.nmssalman.elegantmedia.databinding.FragmentSplashBinding
import com.nmssalman.elegantmedia.login.LoginFragment
import com.nmssalman.elegantmedia.utils.FragmentUtils

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var animationView: LottieAnimationView
    private lateinit var binding: FragmentSplashBinding
    private lateinit var myView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.myView = view
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        setupViews()
    }

    private fun setupViews() {
        FragmentUtils.hideActionBar(requireActivity())
        animationView = binding.splashAnimation
        setupAnimation()
    }

    private fun setupAnimation() {

        animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                navigateToLogin()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}

        })
        animationView.playAnimation()
    }

    private fun navigateToLogin() {
        FragmentUtils.navigateToFragment(LoginFragment())
    }
}