package io.github.damirtugushev.introduction.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import io.github.damirtugushev.introduction.databinding.MainActivityBinding
import io.github.damirtugushev.introduction.presentation.viewmodel.MainActivityModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val accountViewModel: MainActivityModel by viewModel()

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
