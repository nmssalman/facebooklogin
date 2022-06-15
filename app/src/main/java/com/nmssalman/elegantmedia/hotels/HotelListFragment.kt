package com.nmssalman.elegantmedia.hotels

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmssalman.elegantmedia.*
import com.nmssalman.elegantmedia.databinding.FragmentHotelsContentBinding
import com.nmssalman.elegantmedia.facebook.FacebookAuth
import com.nmssalman.elegantmedia.facebook.FacebookUser
import com.nmssalman.elegantmedia.hotels.DataHotels.Data
import com.nmssalman.elegantmedia.login.LoginFragment
import com.nmssalman.elegantmedia.splash.SplashFragment
import com.nmssalman.elegantmedia.utils.FragmentUtils

class HotelListFragment(user: FacebookUser) : Fragment(R.layout.fragment_hotels_content), FacebookAuth.FacebookLogoutAuth, HotelListAdapter.HotelListAdapterListener {
    private lateinit var model: HotelsViewModel
    private val user: FacebookUser
    private lateinit var madapter: HotelListAdapter
    private lateinit var binding: FragmentHotelsContentBinding
    init {
        this.user = user
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelsContentBinding.bind(view)
        model = ViewModelProvider(this).get(HotelsViewModel::class.java)
        setupViews()
        setupObservers()

    }
    private fun setupViews(){
        FragmentUtils.visibleActionBar((activity as AppCompatActivity?)!!)

        with( binding)
        {
            personEmail.text = user.email
            personName.text = user.name
            logout.setOnClickListener {
                FacebookAuth.Logout(this@HotelListFragment)
            }
        }
        madapter = HotelListAdapter(listOf(), requireContext(), this)

        with(binding.list) {
            val layoutManager = LinearLayoutManager(context)
            this.layoutManager = layoutManager
            adapter = madapter
        }
    }

    override fun onClick(hotel: Data) {
        Log.i("HotelClick", hotel.description)
        FragmentUtils.navigateToFragment(HotelDetailsFragment(hotel))
    }

    override fun onLogout() {
        FragmentUtils.navigateToFragment(SplashFragment())
    }
    private fun setupObservers(){
        model.getHotelsList.observe(viewLifecycleOwner, Observer {
            Log.i("HotelsList", it.data.count().toString())
            madapter.updateValues(it.data)
            madapter.notifyDataSetChanged()
        })
    }

}