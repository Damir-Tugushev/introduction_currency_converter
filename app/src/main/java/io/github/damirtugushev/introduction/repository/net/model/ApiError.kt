package io.github.damirtugushev.introduction.repository.net.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiError(@SerialName("error-type") val type: ApiErrorType)
