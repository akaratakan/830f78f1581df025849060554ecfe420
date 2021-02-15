package com.atakanakar.a830f78f1581df025849060554ecfe420.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem



/**
 * Created by atakanakar on 14.02.2021.
 */
@Dao
interface PlanetDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertToFavourites (planet: StationListResponseObjectItem)

    @Delete
    suspend fun deleteFavourite (planet: StationListResponseObjectItem)

    @Query("SELECT * FROM favourite_planet")
    suspend fun getFavouriteStationList(): List<StationListResponseObjectItem>
}