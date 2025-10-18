package id.my.rizalanggoro.emasku.presentation.pages.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.rizalanggoro.emasku.core.UiState
import id.my.rizalanggoro.emasku.core.toFailure
import id.my.rizalanggoro.emasku.openapi.apis.GeneralApi
import id.my.rizalanggoro.emasku.openapi.models.GetSettingsRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class SettingViewModel(
    private val generalApi: GeneralApi
) : ViewModel() {
    var settingsState by mutableStateOf<UiState<GetSettingsRes>>(UiState.Initial)
        private set

    fun getSettings() = viewModelScope.launch {
        try {
            settingsState = UiState.Loading
            val body = generalApi.getSettings().body()
            settingsState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            settingsState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            settingsState = UiState.Failure()
        }
    }

    init {
        getSettings()
    }
}