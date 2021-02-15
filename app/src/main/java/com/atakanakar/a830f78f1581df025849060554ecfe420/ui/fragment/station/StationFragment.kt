package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.station

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.commons.Constants
import com.atakanakar.a830f78f1581df025849060554ecfe420.commons.SpaceShip
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.FragmentStationBinding
import com.atakanakar.a830f78f1581df025849060554ecfe420.ui.activity.MainActivity
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


/**
 * Created by atakanakar on 11.02.2021.
 */
@AndroidEntryPoint
class StationFragment : BaseFragment() {

    private val viewModel: StationVM by viewModels()

    lateinit var stationAdapter: StationAdapter
    private var _binding: FragmentStationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareToolbar()
        setSpaceShipInfo()
        viewModel.getAllFavouriteStation()
        observeLiveData()
        initListener()
    }

    private fun observeLiveData() {

        viewModel.stationListResponse.observe(viewLifecycleOwner) {
            viewModel.favouriteList.value?.forEach { favouriteStation ->
                it.forEach { station ->
                    if (favouriteStation.name == station.name) {
                        station.isFavourite = true
                    }
                }
            }
            prepareRecyclerView(it.toMutableList())
        }
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            viewModel.getStationList()
        }
        viewModel.loadingDetection.observe(viewLifecycleOwner) {
            (activity as MainActivity).showLoading(it)
        }
        viewModel.networkErrorDetection.observe(viewLifecycleOwner) {
            showErrorDialog(it)
        }
        viewModel.keyword.observe(viewLifecycleOwner) {
            stationAdapter.replaceList(viewModel.getSearchedStationList(it).toMutableList())
        }
    }

    private fun initListener() {
        binding.searchKeyword.doOnTextChanged { text, _, _, _ ->
            Log.d("search keyword", "search keyword " + text.toString())
            viewModel.keyword.postValue(text.toString())
        }
    }

    private fun prepareRecyclerView(stationList: MutableList<StationListResponseObjectItem>) {
        stationAdapter = StationAdapter(stationList, 0.0, 0.0, ::onTravelClick, ::onFavouriteClick)
        binding.stationRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = stationAdapter
        }
    }

    private fun onFavouriteClick(
        station: StationListResponseObjectItem
    ) {
        GlobalScope.launch {
            if (station.isFavourite) {
                station.isFavourite = false
                viewModel.deleteFavourite(requireContext(), station)
            } else {
                station.isFavourite = true
                viewModel.insertToFavourites(requireContext(), station)
            }
        }
    }

    private fun onTravelClick(
        station: StationListResponseObjectItem
    ) {
        makeToast("travel to ${station.name}")
    }

    private fun getSpaceShip(): SpaceShip {
        return arguments?.getSerializable(Constants.spaceShipArg) as SpaceShip
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setSpaceShipInfo() {
        binding.spaceShipName.text   = getString(R.string.spaceship_name, getSpaceShip().name)
        binding.spaceShipHealth.text = getString(R.string.spaceship_health, getSpaceShip().health)
        binding.shipUGS.text         = getString(R.string.spaceship_ugs, getSpaceShip().ugs)
        binding.shipEUS.text         = getString(R.string.spaceship_eus, getSpaceShip().eus)
        binding.shipDS.text          = getString(R.string.spaceship_ds, getSpaceShip().ds)
    }
}