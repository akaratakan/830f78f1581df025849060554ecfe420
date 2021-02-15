package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.spaceship

import androidx.lifecycle.MutableLiveData
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseViewModel
import javax.inject.Inject


/**
 * Created by atakanakar on 13.02.2021.
 */
class SpaceShipVM @Inject constructor(): BaseViewModel() {

    var totalPoint = MutableLiveData<Int>()
    var velocityCount = 0
    var capacityCount = 0
    var strengthCount = 0

    init {
        totalPoint.postValue(15)
    }
}