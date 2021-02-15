package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.favourite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseViewModel
import com.atakanakar.a830f78f1581df025849060554ecfe420.db.PlanetDatabase
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by atakanakar on 14.02.2021.
 */
@HiltViewModel
class FavouriteVM @Inject constructor(@ApplicationContext val context: Context): BaseViewModel() {

    val favouriteList = MutableLiveData<List<StationListResponseObjectItem>>()

    suspend fun insertToFavourites(context: Context, station: StationListResponseObjectItem) {
        withContext(Dispatchers.IO) {
            PlanetDatabase.getDatabase(context).planetDao().insertToFavourites(station)
        }
    }

    suspend fun deleteFavourite(context: Context, station: StationListResponseObjectItem) {
        withContext(Dispatchers.IO) {
            PlanetDatabase.getDatabase(context).planetDao().deleteFavourite(station)
        }
    }

    fun getAllFavouriteStation() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val list = PlanetDatabase.getDatabase(context).planetDao()
                    .getFavouriteStationList()
                favouriteList.postValue(list)
            }
        }


    }

}