package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.station

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.ItemStationBinding
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Created by atakanakar on 11.02.2021.
 */


class StationAdapter(
    private val movieList: MutableList<StationListResponseObjectItem>,
    val currentLocationX: Double,
    val currentLocationY: Double,
    val onTravelClicked: (StationListResponseObjectItem) -> Unit,
    val onFavouriteClicked: (StationListResponseObjectItem) -> Unit
) : RecyclerView.Adapter<StationAdapter.StationItemHolder>() {

    inner class StationItemHolder(val binding: ItemStationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StationListResponseObjectItem, position: Int) {

            binding.stationName.text = item.name
            binding.stationCoordinate.text = roundOfDecimal(getDistance(item))
            binding.stationCapacity.text = item.capacity.toString()
            binding.stationMaterial.text = item.stock.toString()
            binding.favButton.setFavButtonTintColor(item.isFavourite)
            binding.travelButton.setOnClickListener {
                onTravelClicked(item)
            }
            binding.favButton.setOnClickListener {
                binding.favButton.setFavButtonTintColor(!item.isFavourite)
                onFavouriteClicked(item)
            }
        }
    }

    fun clearList() {
        movieList.clear()
        notifyDataSetChanged()
    }

    fun replaceList(list: MutableList<StationListResponseObjectItem>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationItemHolder {
        return StationItemHolder(
            ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: StationItemHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item, position)
    }


    override fun getItemCount(): Int = movieList.size

    private fun getDistance(station:StationListResponseObjectItem): Double {

        return  sqrt(
            (((currentLocationX - station.coordinateX).pow(2)) - ((currentLocationY - station.coordinateY).pow(2))).absoluteValue
        )

    }
    private fun roundOfDecimal(number: Double): String {
        return String.format("%.2f", number)
    }

    private fun ImageView.setFavButtonTintColor(isFav : Boolean) {
        val color = if (isFav) R.color.color_primary else R.color.black
        setColorFilter(ContextCompat.getColor(context, color))
    }
}