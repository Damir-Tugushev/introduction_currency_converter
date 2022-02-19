package io.github.damirtugushev.introduction.viewmodel

import androidx.lifecycle.ViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import io.github.damirtugushev.introduction.model.SupportedCode
import io.github.damirtugushev.introduction.model.SupportedCode.State
import io.github.damirtugushev.introduction.model.SupportedCodeWithState
import io.github.damirtugushev.introduction.repository.net.ApiResponse
import io.github.damirtugushev.introduction.repository.net.ExchangeRateAPI
import io.github.damirtugushev.introduction.repository.net.model.parse
import io.github.damirtugushev.introduction.repository.room.*
import retrofit2.Response

class SupportedCodesViewModel(
    private val exchangeRateAPI: ExchangeRateAPI,
    private val supportedCodeRepository: SupportedCodeRepository,
    private val conversionRateRepository: ConversionRateRepository,
) :
    ViewModel() {

    private var _netSupportedCodes: MutableList<SupportedCode> = mutableListOf()

    suspend fun getSupportedCodes(): List<SupportedCodeWithState> {
        val netCodes = _netSupportedCodes.map { SupportedCodeWithState(it, State.Deleted) }
        val localCodes = supportedCodeRepository.getAll()
            .map { SupportedCodeWithState(it.toModel(), State.Saved) }

        val selector: (SupportedCodeWithState) -> String = { it.code.code }
        return (localCodes + netCodes).distinctBy(selector).sortedBy(selector)
    }

    suspend fun refreshSupportedCodes(): ApiResponse<List<SupportedCodeWithState>> {
        val netResult = exchangeRateAPI.supportedCodes()
        if (netResult !is NetworkResponse.Success)
            @Suppress("UNCHECKED_CAST")
            return netResult as ApiResponse<List<SupportedCodeWithState>>

        val parsed = netResult.body.parse()
        _netSupportedCodes.clear()
        _netSupportedCodes += parsed
        return NetworkResponse.Success(getSupportedCodes(), Response.success(null))
    }

    suspend fun save(supportedCode: SupportedCode): ApiResponse<Unit> {
        supportedCodeRepository.save(supportedCode.toDto())
        _netSupportedCodes -= supportedCode

        return when (val netResult = exchangeRateAPI.latestData(supportedCode.code)) {
            is NetworkResponse.Success -> {
                val rateDtos = netResult.body.toDtos()
                rateDtos.forEach { conversionRateRepository.save(it) }
                NetworkResponse.Success(Unit, Response.success(null))
            }
            else -> {
                @Suppress("UNCHECKED_CAST")
                return netResult as ApiResponse<Unit>
            }
        }
    }

    suspend fun delete(supportedCode: SupportedCode) {
        checkNotNull(supportedCodeRepository.findById(supportedCode.code))
        _netSupportedCodes += supportedCode
        supportedCodeRepository.delete(supportedCode.toDto())
    }
}
