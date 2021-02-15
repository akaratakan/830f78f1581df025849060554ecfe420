package com.atakanakar.a830f78f1581df025849060554ecfe420.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideNavController(activity: Activity): NavController {
        return activity.findNavController(R.id.nav_host_fragment)
    }

}