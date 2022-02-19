package io.github.damirtugushev.introduction.di

import android.content.Context
import androidx.room.Room
import io.github.damirtugushev.introduction.repository.room.ConversionRateRepository
import io.github.damirtugushev.introduction.repository.room.CurrencyConverterDatabase
import io.github.damirtugushev.introduction.repository.room.SupportedCodeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { database(androidContext()) }
    single { supportedCodeRepository(get()) }
    single { conversionRateRepository(get()) }
}

private fun database(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        CurrencyConverterDatabase::class.java,
        "currency_converter",
    ).build()

private fun supportedCodeRepository(database: CurrencyConverterDatabase): SupportedCodeRepository =
    SupportedCodeRepository(database)

private fun conversionRateRepository(database: CurrencyConverterDatabase): ConversionRateRepository =
    ConversionRateRepository(database)
