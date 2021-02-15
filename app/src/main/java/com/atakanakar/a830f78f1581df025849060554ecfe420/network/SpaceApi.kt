package com.atakanakar.a830f78f1581df025849060554ecfe420.network

import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import retrofit2.http.*

interface SpaceApi {

    @GET("v3/e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getAllStation(): List<StationListResponseObjectItem>

}