package io.github.damirtugushev.introduction.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.github.damirtugushev.introduction.repository.ExchangeRateAPI

class MainActivityModel(private val exchangeRateAPI: ExchangeRateAPI) : ViewModel()
