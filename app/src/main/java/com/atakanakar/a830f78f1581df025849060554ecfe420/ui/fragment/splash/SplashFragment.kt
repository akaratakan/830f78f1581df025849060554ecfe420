package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.FragmentSplashBinding
import com.atakanakar.a830f78f1581df025849060554ecfe420.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


/**
 * Created by atakanakar on 10.02.2021.
 */
@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val viewModel: SplashVM by viewModels()
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
        prepareToolbar()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                binding.motionBase.transitionToEnd()
            }
        }
    }

    private fun initListener() {
        binding.motionBase.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        delay(1000)
                        findNavController().navigate(R.id.action_splashFragment_to_spaceShipFragment)
                    }
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}