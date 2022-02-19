package io.github.damirtugushev.introduction.repository.room.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.damirtugushev.introduction.repository.room.dto.SupportedCodeDto

@Dao
interface SupportedCodeDao : BaseDao<SupportedCodeDto> {

    @Query("SELECT * FROM supported_code WHERE code = :code")
    suspend fun findByCode(code: String): SupportedCodeDto?

    @Query("SELECT * FROM supported_code")
    suspend fun getAll(): List<SupportedCodeDto>
}
