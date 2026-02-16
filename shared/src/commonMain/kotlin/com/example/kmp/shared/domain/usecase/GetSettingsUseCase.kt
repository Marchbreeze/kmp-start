package com.example.kmp.shared.domain.usecase

import com.example.kmp.shared.domain.model.AppSettings
import com.example.kmp.shared.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetSettingsUseCase(
    private val repository: SettingsRepository,
) {
    operator fun invoke(): Flow<AppSettings> {
        return repository.getSettings()
    }
}
