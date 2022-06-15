package com.nmssalman.elegantmedia.hotels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nmssalman.elegantmedia.APIRepository.PrimaryAPICaller
import com.nmssalman.elegantmedia.APIRepository.URLHelper
import com.nmssalman.elegantmedia.R
import com.nmssalman.elegantmedia.hotels.DataHotels.HotelsData
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.net.URL

class HotelsRepository(val context: Context) {

    private val getHotelContents = GetHotelContents()

    //live data sharing variables
    private val _getHotelsList = MutableLiveData<HotelsData>()
    val getHotelsList : LiveData<HotelsData>
        get() = _getHotelsList

    //common error sharing var
    private val _apiError = MutableLiveData<String>()
    val apiError : LiveData<String>
        get() = _apiError

    fun getHotelContents(){
        getHotelContents.onFinish()
    }
    private inner class GetHotelContents() {
        fun onFinish(){
            PrimaryAPICaller(URLHelper.HOTELS, object : okhttp3.Callback{
                override fun onFailure(call: Call, e: IOException) {
                   _apiError.postValue(e.message.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    if(response.isSuccessful)
                    {
                        val data = Gson().fromJson(response.body!!.string(), HotelsData::class.java)
                        _getHotelsList.postValue(data)
                    }
                    else
                        _apiError.postValue(context.getString(R.string.api_something_went_wrong))
                }

            })
        }
    }
}