package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.ItemFavouriteBinding
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Created by atakanakar on 15.02.2021.
 */
class FavouriteAdapter(
private val movieList: MutableList<StationListResponseObjectItem>,
val currentLocationX: Double,
val currentLocationY: Double,
val onFavouriteClicked: (StationListResponseObjectItem) -> Unit
) : RecyclerView.Adapter<FavouriteAdapter.FavouriteItemHolder>() {

    inner class FavouriteItemHolder(val binding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StationListResponseObjectItem, position: Int) {

            binding.stationName.text = item.name
            binding.stationCoordinate.text = roundOfDecimal(getDistance(item))
            binding.favButton.setFavButtonTintColor(item.isFavourite)
            binding.favButton.setOnClickListener {
                binding.favButton.setFavButtonTintColor(!item.isFavourite)
                onFavouriteClicked(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteItemHolder {
        return FavouriteItemHolder(
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: FavouriteItemHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item, position)
    }


    override fun getItemCount(): Int = movieList.size

    private fun getDistance(station: StationListResponseObjectItem): Double {

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