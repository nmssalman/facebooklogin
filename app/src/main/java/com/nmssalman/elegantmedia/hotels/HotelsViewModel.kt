package com.nmssalman.elegantmedia.hotels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nmssalman.elegantmedia.hotels.DataHotels.HotelsData

class HotelsViewModel(application: Application): AndroidViewModel(application) {
    val getHotelsList: LiveData<HotelsData>
    val hotelsRepository: HotelsRepository
    init {
        hotelsRepository = HotelsRepository(application)
        getHotelsList = hotelsRepository.getHotelsList
        hotelsRepository.getHotelContents()
    }

}