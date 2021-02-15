package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.activity

import androidx.lifecycle.ViewModel
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.SpaceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by atakanakar on 10.02.2021.
 */
@HiltViewModel
class MainVM @Inject constructor(val api: SpaceApi) : ViewModel() {

}