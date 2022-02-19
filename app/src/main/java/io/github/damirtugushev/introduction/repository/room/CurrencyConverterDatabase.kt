package io.github.damirtugushev.introduction.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.damirtugushev.introduction.repository.room.dao.ConversionRateDao
import io.github.damirtugushev.introduction.repository.room.dao.SupportedCodeDao
import io.github.damirtugushev.introduction.repository.room.dto.ConversionRateDto
import io.github.damirtugushev.introduction.repository.room.dto.SupportedCodeDto

@Database(
    entities = [SupportedCodeDto::class, ConversionRateDto::class],
    version = 1,
    exportSchema = false,
)
abstract class CurrencyConverterDatabase : RoomDatabase() {

    abstract val supportedCodeDao: SupportedCodeDao
    abstract val conversionRateDao: ConversionRateDao
}
