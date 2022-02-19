package io.github.damirtugushev.introduction.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.github.damirtugushev.introduction.R
import io.github.damirtugushev.introduction.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private inline val navController: NavController
        get() {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }

    fun showProgress() {
        binding.toolbarProgressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.toolbarProgressBar.visibility = View.GONE
        binding.toolbarProgressBar.setVisibilityAfterHide(View.GONE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setupWithNavController(navController)
    }
}
