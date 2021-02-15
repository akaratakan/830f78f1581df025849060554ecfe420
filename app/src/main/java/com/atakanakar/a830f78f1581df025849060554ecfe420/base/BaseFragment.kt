package com.atakanakar.a830f78f1581df025849060554ecfe420.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.activity.MainActivity
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.dialog.InfoDialog
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.favourite.FavouriteFragment
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.spaceship.SpaceShipFragment
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.splash.SplashFragment
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.station.StationFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by atakanakar on 14.02.2021.
 */
@AndroidEntryPoint
abstract class BaseFragment : Fragment() {


    fun prepareToolbar() {
        when (this) {
            is SplashFragment    -> getMainActivity().setToolbarInfo(false)
            is SpaceShipFragment -> getMainActivity().setToolbarInfo(false)
            is StationFragment   -> getMainActivity().setToolbarInfo(true,"travel",true,false)
            is FavouriteFragment -> getMainActivity().setToolbarInfo(true,"Favourite",false,true)
        }
    }

    private fun getMainActivity(): MainActivity {
        return (activity as MainActivity)
    }

    fun showErrorDialog(warnText: String) {
        InfoDialog(getString(R.string.error), warnText).show(childFragmentManager, "")
    }
    fun makeToast(text: String) {
        Toast.makeText(requireContext(),text,Toast.LENGTH_SHORT).show()
    }
}