package com.auwfar.mapapplication

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.auwfar.mapapplication.databinding.ActivityMainBinding
import com.auwfar.mapapplication.utils.AppPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val appPreferences by lazy {
        AppPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        observeViewModel()
        appPreferences.userName?.takeIf { it.isNotBlank() }?.let {
            viewModel.setUserName(it)
        }
    }

    private fun observeViewModel() {
        viewModel.userName.observe(this) {
            this@MainActivity.supportActionBar?.title = "Halo $it"
        }
    }
}