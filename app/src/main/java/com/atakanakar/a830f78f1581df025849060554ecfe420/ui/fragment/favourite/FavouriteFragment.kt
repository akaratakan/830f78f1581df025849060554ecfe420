package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.FragmentFavouriteBinding
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station.StationListResponseObjectItem
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created by atakanakar on 14.02.2021.
 */
@AndroidEntryPoint
class FavouriteFragment: BaseFragment() {

    lateinit var favouriteAdapter: FavouriteAdapter

    private val viewModel: FavouriteVM by viewModels()
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareToolbar()
        viewModel.getAllFavouriteStation()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            prepareRecyclerView(it.toMutableList())
        }
    }

    private fun prepareRecyclerView(stationList: MutableList<StationListResponseObjectItem>) {
        favouriteAdapter = FavouriteAdapter(stationList, 0.0, 0.0, ::onFavouriteClick)
        binding.favRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = favouriteAdapter
        }
    }

    private fun onFavouriteClick(favItem: StationListResponseObjectItem) {
        GlobalScope.launch {
            if (favItem.isFavourite) {
                favItem.isFavourite = false
                viewModel.deleteFavourite(requireContext(), favItem)
            } else {
                favItem.isFavourite = true
                viewModel.insertToFavourites(requireContext(), favItem)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}