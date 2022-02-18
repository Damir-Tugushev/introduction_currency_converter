package io.github.damirtugushev.introduction.repository

import com.haroldadmin.cnradapter.NetworkResponse
import io.github.damirtugushev.introduction.repository.model.ApiError
import io.github.damirtugushev.introduction.repository.model.LatestDataResult
import io.github.damirtugushev.introduction.repository.model.SupportedCodesResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateAPI {
    companion object {
        private const val API_KEY = "SECRET"
        const val baseURI = "https://v6.exchangerate-api.com/v6/$API_KEY/"
    }

    @GET("codes")
    suspend fun supportedCodes(): ApiResponse<SupportedCodesResult>

    @GET("latest/{code}")
    suspend fun latestData(@Path("code") code: String): ApiResponse<LatestDataResult>
}

typealias ApiResponse<T> = NetworkResponse<T, ApiError>
