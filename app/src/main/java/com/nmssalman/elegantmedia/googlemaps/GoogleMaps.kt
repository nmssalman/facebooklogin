package com.nmssalman.elegantmedia.googlemaps

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nmssalman.elegantmedia.R

class GoogleMaps(maps: SupportMapFragment) {
    private val googleMaps: SupportMapFragment
    init {
        this.googleMaps = maps
    }
    fun pingOnMap(location: LatLng){
        googleMaps.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(p0: GoogleMap?) {
                p0?.clear()
                addMarker(location, p0!!)
            }
        })

    }

    private fun addMarker(location: LatLng, map: GoogleMap){
        val markerOption = MarkerOptions()
        markerOption.position(location)
        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_ping))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
        map.addMarker(markerOption)

    }
}