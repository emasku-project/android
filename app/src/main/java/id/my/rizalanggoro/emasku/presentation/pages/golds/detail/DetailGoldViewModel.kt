package id.my.rizalanggoro.emasku.presentation.pages.golds.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.rizalanggoro.emasku.core.Routes
import id.my.rizalanggoro.emasku.core.UiState
import id.my.rizalanggoro.emasku.core.toFailure
import id.my.rizalanggoro.emasku.openapi.apis.GoldApi
import id.my.rizalanggoro.emasku.openapi.models.DeleteGoldByIdRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class DetailGoldViewModel(
    private val key: Routes.DetailGold,
    private val goldApi: GoldApi
) : ViewModel() {
    var deleteState by mutableStateOf<UiState<DeleteGoldByIdRes>>(UiState.Initial)
        private set

    fun resetDeleteState() {
        deleteState = UiState.Initial
    }

    fun delete() = viewModelScope.launch {
        try {
            deleteState = UiState.Loading
            val body = goldApi.deleteGoldById(
                goldId = key.goldId,
            ).body()
            deleteState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            deleteState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            deleteState = UiState.Failure()
        }
    }
}