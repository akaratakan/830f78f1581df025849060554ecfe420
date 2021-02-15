package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.station

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseViewModel
import com.atakanakar.a830f78f1581df025849060554ecfe420.db.PlanetDatabase
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.SpaceApi
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


/**
 * Created by atakanakar on 11.02.2021.
 */
@HiltViewModel
class StationVM @Inject constructor(@ApplicationContext val context: Context, val api: SpaceApi) :
    BaseViewModel() {


    val favouriteList = MutableLiveData<List<StationListResponseObjectItem>>()


    private val _stationListResponse = MutableLiveData<List<StationListResponseObjectItem>>()
    val stationListResponse: LiveData<List<StationListResponseObjectItem>>
        get() = _stationListResponse

    val keyword = MutableLiveData<String>()

    fun getStationList() {
        sendRequest({ api.getAllStation() }) {
            _stationListResponse.postValue(it)
        }
    }

    fun getSearchedStationList(keyword: String): List<StationListResponseObjectItem> {
        val listFromApi = _stationListResponse.value
        val searchedList = listFromApi?.filter {
            it.name.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))
        }
        return searchedList ?: arrayListOf()
    }


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