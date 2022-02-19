package io.github.damirtugushev.introduction.di

import io.github.damirtugushev.introduction.viewmodel.ConverterViewModel
import io.github.damirtugushev.introduction.viewmodel.SupportedCodesViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { json() }
    viewModel { ConverterViewModel(get(), get()) }
    viewModel { SupportedCodesViewModel(get(), get(), get()) }
}

private fun json() = Json { ignoreUnknownKeys = true }
