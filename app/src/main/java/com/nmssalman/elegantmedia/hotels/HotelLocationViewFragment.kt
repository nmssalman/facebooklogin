package com.nmssalman.elegantmedia.hotels

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nmssalman.elegantmedia.*
import com.nmssalman.elegantmedia.googlemaps.GoogleMaps
import com.nmssalman.elegantmedia.hotels.DataHotels.Data
import com.nmssalman.elegantmedia.login.LoginFragment
import com.nmssalman.elegantmedia.utils.FragmentUtils
import androidx.appcompat.app.AppCompatActivity




class HotelLocationViewFragment(hotel: Data) : Fragment(R.layout.fragment_hotel_location_view) {
    private val hotel: Data
    lateinit var map: SupportMapFragment
    private lateinit var googleMaps: GoogleMaps

    init {
        this.hotel = hotel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupViews()
        initMaps()
    }
    private fun initMaps(){
        map = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        googleMaps = GoogleMaps(map)
        googleMaps.pingOnMap(LatLng(hotel.latitude.toDouble(), hotel.longitude.toDouble()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home -> {
                FragmentUtils.navigateToFragment(HotelDetailsFragment(hotel))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViews(){
        FragmentUtils.visibleActionBar((activity as AppCompatActivity?)!!)

        FragmentUtils.setActionBarTitle(requireContext().getString(R.string.map), requireActivity())
        FragmentUtils.setOptionMenu(activity as MainActivity)
    }
}