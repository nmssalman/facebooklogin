package com.nmssalman.elegantmedia.hotels

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.nmssalman.elegantmedia.*
import com.nmssalman.elegantmedia.databinding.FragmentHotelDetailsBinding
import com.nmssalman.elegantmedia.hotels.DataHotels.Data
import com.nmssalman.elegantmedia.hotels.DataHotels.HotelsData
import com.nmssalman.elegantmedia.login.LoginFragment
import com.nmssalman.elegantmedia.utils.FragmentUtils

class HotelDetailsFragment(hotel: Data) : Fragment(R.layout.fragment_hotel_details) {
    private lateinit var binding: FragmentHotelDetailsBinding
    private val hotel: Data
    init {
        this.hotel = hotel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelDetailsBinding.bind(view)
        setHasOptionsMenu(true)
        setupViews()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inflating the custom option menu
        inflater!!.inflate(R.menu.options_hotel_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //validation and function execution according to menu selection
        when(item.itemId)
        {
            android.R.id.home -> {
                FragmentUtils.navigateToFragment(LoginFragment())
            }
            R.id.option_location -> {
                FragmentUtils.navigateToFragment(HotelLocationViewFragment(hotel))
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setupViews(){
        FragmentUtils.visibleActionBar(requireActivity())
        FragmentUtils.setActionBarTitle(requireContext().getString(R.string.list), requireActivity())

        FragmentUtils.setOptionMenu(activity as MainActivity)
       with(binding)
       {
           title.text = hotel.title
           description.text = hotel.description
           FragmentUtils.GlideToImageView(requireContext(), hotel.image.large, image)
       }
    }

}