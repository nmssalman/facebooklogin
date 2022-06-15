package com.nmssalman.elegantmedia.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.facebook.CallbackManager
import com.nmssalman.elegantmedia.*

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

import com.nmssalman.elegantmedia.databinding.FragmentLoginBinding
import com.nmssalman.elegantmedia.facebook.FacebookAuth
import com.nmssalman.elegantmedia.facebook.FacebookUser
import com.nmssalman.elegantmedia.hotels.HotelListFragment
import com.nmssalman.elegantmedia.utils.FragmentUtils


class LoginFragment : Fragment(R.layout.fragment_login), FacebookAuth.FacebookLoginAuth {
    lateinit var binding: FragmentLoginBinding
    lateinit var facebookLogin: FacebookAuth
    var callbackManager = CallbackManager.Factory.create();
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        facebookLogin = FacebookAuth(requireContext(), requireActivity(), this, binding.btnfb, callbackManager, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccess(user: FacebookUser) {
        FragmentUtils.navigateToFragment(HotelListFragment(user))
    }

    override fun onError(message: String) {
        FragmentUtils.DisplayCustomDialog(requireContext(), "Facebook Error", message)

    }

    override fun onCancel() {
        FragmentUtils.DisplayCustomDialog(requireContext(), "Facebook Error", requireContext().getString(R.string.fb_login_cancel))
    }
}