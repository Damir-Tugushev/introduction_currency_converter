package io.github.damirtugushev.introduction.di

import io.github.damirtugushev.introduction.viewmodel.MainActivityModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { json() }
    viewModel { MainActivityModel(get()) }
}

private fun json() = Json { ignoreUnknownKeys = true }
