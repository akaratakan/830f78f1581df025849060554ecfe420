package com.atakanakar.a830f78f1581df025849060554ecfe420.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.atakanakar.a830f78f1581df025849060554ecfe420.R
import com.atakanakar.a830f78f1581df025849060554ecfe420.commons.visibleIf
import com.atakanakar.a830f78f1581df025849060554ecfe420.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainVM by viewModels()
    lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        initListener()

    }

    fun showLoading(isLoad: Boolean) {
        binding.containerProgress.visibility = if (isLoad) View.VISIBLE else View.GONE
    }

    fun setToolbarInfo(
        isToolbarOpen: Boolean? = false,
        title: String? = "",
        favButtonVisible: Boolean? = false,
        questBtmVisible: Boolean? = false
    ) {
        if (isToolbarOpen == true) {
            binding.toolbar.visibility = View.VISIBLE
            binding.toolbarTitle.text = title
            binding.favToolbarButton.visibleIf(favButtonVisible == true)
            binding.planetButton.visibleIf(questBtmVisible == true)
        } else {
            binding.toolbar.visibility = View.GONE
        }
    }

    private fun initListener() {
        binding.planetButton.setOnClickListener {
            onBackPressed()
        }
        binding.favToolbarButton.setOnClickListener {
            navController.navigate(R.id.action_to_favouriteFragment)
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

}