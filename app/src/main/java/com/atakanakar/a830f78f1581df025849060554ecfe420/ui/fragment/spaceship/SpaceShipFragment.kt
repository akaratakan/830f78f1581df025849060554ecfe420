package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.spaceship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.commons.Constants
import com.atakanakar.a830f78f1581df025849060554ecfe420.commons.SpaceShip
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.FragmentSpaceShipBinding
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by atakanakar on 13.02.2021.
 */

@AndroidEntryPoint
class SpaceShipFragment : BaseFragment() {

    private val TOTAL_SPECIAL_POINT = 15
    private val SPACE_SHIP_HEALTH = 100
    private val viewModel: SpaceShipVM by viewModels()
    private var _binding: FragmentSpaceShipBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
        prepareToolbar()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.totalPoint.observe(viewLifecycleOwner) {
            binding.totalPointText.text = getString(R.string.total_point_info_text, it)
        }
    }

    private fun initListener() {
        binding.applyButton.setOnClickListener {
            if (!binding.nameEt.text.isNullOrEmpty()) {
                Bundle().apply {
                    putSerializable(Constants.spaceShipArg, getSpaceShipSpeciality())
                    findNavController().navigate(
                        R.id.action_spaceShipFragment_to_stationFragment,
                        this
                    )
                }
            } else {
                showErrorDialog(getString(R.string.enter_name_warn_text))
            }
        }
        binding.velocityBar.addOnChangeListener { _, value, _ ->
            binding.velocityCount.text = value.toInt().toString()
            binding.applyButton.isEnabled = isTotalPointValid()
            viewModel.totalPoint.postValue(TOTAL_SPECIAL_POINT.minus((getTotalPointFromUser())))
        }
        binding.capacityBar.addOnChangeListener { _, value, _ ->
            binding.capacityCount.text = value.toInt().toString()
            binding.applyButton.isEnabled = isTotalPointValid()
            viewModel.totalPoint.postValue(TOTAL_SPECIAL_POINT.minus(getTotalPointFromUser()))
        }
        binding.strengthBar.addOnChangeListener { _, value, _ ->
            binding.strengthCount.text = value.toInt().toString()
            binding.applyButton.isEnabled = isTotalPointValid()
            viewModel.totalPoint.postValue(TOTAL_SPECIAL_POINT.minus(getTotalPointFromUser()))
        }
    }

    private fun getTotalPointFromUser(): Int {
        return binding.velocityBar.value.toInt() +
                binding.capacityBar.value.toInt() +
                binding.strengthBar.value.toInt()
    }

    private fun isTotalPointValid(): Boolean {
        return getTotalPointFromUser() == TOTAL_SPECIAL_POINT &&
                binding.velocityBar.value.toInt() > 0 &&
                binding.capacityBar.value.toInt() > 0 &&
                binding.strengthBar.value.toInt() > 0
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpaceShipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSpaceShipSpeciality(): SpaceShip {
        return SpaceShip(
            binding.nameEt.text.toString(),
            SPACE_SHIP_HEALTH,
            binding.capacityBar.value.toInt() * 10000,
            binding.velocityBar.value.toInt() * 20,
            binding.strengthBar.value.toInt() * 10000
        )
    }
}