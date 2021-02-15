package com.atakanakar.a830f78f1581df025849060554ecfe420.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(entities = [StationListResponseObjectItem::class], version = 1)
abstract class PlanetDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao

    companion object {
        private var INSTANCE: PlanetDatabase? = null
        private const val DB_NAME = "planet.db"

        fun getDatabase(@ApplicationContext context: Context): PlanetDatabase {
            if (INSTANCE == null) {
                synchronized(PlanetDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            PlanetDatabase::class.java,
                            DB_NAME
                        )

                            .build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
}