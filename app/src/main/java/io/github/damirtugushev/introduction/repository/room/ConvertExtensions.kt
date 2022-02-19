package io.github.damirtugushev.introduction.repository.room

import io.github.damirtugushev.introduction.model.ConversionRate
import io.github.damirtugushev.introduction.model.SupportedCode
import io.github.damirtugushev.introduction.repository.net.model.LatestDataResult
import io.github.damirtugushev.introduction.repository.room.dto.ConversionRateDto
import io.github.damirtugushev.introduction.repository.room.dto.SupportedCodeDto
import java.util.*

fun SupportedCodeDto.toModel(): SupportedCode =
    SupportedCode(code, name)

fun SupportedCode.toDto(): SupportedCodeDto =
    SupportedCodeDto(code, name)

fun LatestDataResult.toDto(targetCode: String): ConversionRateDto {
    val lastUpdate = Date(lastUpdateUnix)
    val nextUpdate = Date(nextUpdateUnix)
    val rate = checkNotNull(conversionRates[targetCode])
    return ConversionRateDto(baseCode, targetCode, lastUpdate, nextUpdate, rate)
}

fun LatestDataResult.toDtos(): List<ConversionRateDto> {
    val lastUpdate = Date(lastUpdateUnix)
    val nextUpdate = Date(nextUpdateUnix)
    val sequence = conversionRates.asSequence().filter { baseCode != it.key }.map {
        val targetCode = it.key
        val rate = it.value
        ConversionRateDto(baseCode, targetCode, lastUpdate, nextUpdate, rate)
    }
    return sequence.toList()
}

fun ConversionRate.toDto(): ConversionRateDto =
    ConversionRateDto(baseCode, targetCode, lastUpdate, nextUpdate, rate)

fun ConversionRateDto.toModel(): ConversionRate =
    ConversionRate(baseCode, targetCode, lastUpdate, nextUpdate, rate)

fun ConversionRate.inverted(): ConversionRate =
    ConversionRate(targetCode, baseCode, lastUpdate, nextUpdate, 1 / rate)
