package com.nmssalman.elegantmedia.hotels

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmssalman.elegantmedia.databinding.ItemHotelBinding
import com.nmssalman.elegantmedia.hotels.DataHotels.Data
import com.nmssalman.elegantmedia.hotels.DataHotels.HotelsData
import com.nmssalman.elegantmedia.utils.FragmentUtils

class HotelListAdapter(var hotelsList: List<Data>, val context: Context, val mListener: HotelListAdapterListener) : RecyclerView.Adapter<HotelListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListener, hotelsList[position], context)
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
       return hotelsList.count()
    }


    class ViewHolder(val binding: ItemHotelBinding): RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("SetTextI18n", "SimpleDateFormat", "DefaultLocale")
        fun bind(mListener: HotelListAdapterListener, hotel: Data, context: Context)
        {
            with(binding)
            {
                title.text = hotel.title
                address.text = hotel.address
                FragmentUtils.GlideToImageView(context, hotel.image.small, image)
                root.setOnClickListener { mListener.onClick(hotel) }
            }

        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHotelBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
    fun updateValues(data: List<Data>)
    {
        hotelsList = data
    }
    interface HotelListAdapterListener{
        fun onClick(hotel: Data)
    }
}