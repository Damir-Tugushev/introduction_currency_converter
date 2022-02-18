package io.github.damirtugushev.introduction.model.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportedCodesResult(
    @SerialName("supported_codes") val supportedCodes: Map<String, String>,
)
