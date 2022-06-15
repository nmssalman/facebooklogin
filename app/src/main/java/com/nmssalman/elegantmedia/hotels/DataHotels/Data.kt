package com.nmssalman.elegantmedia.hotels.DataHotels

data class Data(
    val address: String,
    val description: String,
    val id: Int,
    val image: Image,
    val latitude: String,
    val longitude: String,
    val phoneNumber: String,
    val postcode: String,
    val title: String
)