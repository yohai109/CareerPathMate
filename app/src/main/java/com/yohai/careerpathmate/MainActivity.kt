package com.yohai.careerpathmate

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yohai.careerpathmate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val controller = (
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
                )?.navController
        controller?.let { binding.toolbar.setupWithNavController(it) }
    }

    fun setToolbarTitle(newTitle: String) {
        supportActionBar?.title = newTitle
    }

    fun setToolbarTitle(@StringRes newTitle: Int){
        supportActionBar?.setTitle(newTitle)
    }
}