package id.my.rizalanggoro.emasku.presentation.pages.golds.index

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.rizalanggoro.emasku.core.UiState
import id.my.rizalanggoro.emasku.core.toFailure
import id.my.rizalanggoro.emasku.openapi.apis.GeneralApi
import id.my.rizalanggoro.emasku.openapi.apis.GoldApi
import id.my.rizalanggoro.emasku.openapi.models.GetAllGoldsRes
import id.my.rizalanggoro.emasku.openapi.models.GetMarketSummaryRes
import id.my.rizalanggoro.emasku.openapi.models.GetSummaryRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class GoldsViewModel(
    private val generalApi: GeneralApi,
    private val goldApi: GoldApi
) : ViewModel() {
    var marketSummaryState by mutableStateOf<UiState<GetMarketSummaryRes>>(UiState.Initial)
        private set

    fun getMarketSummary() = viewModelScope.launch {
        try {
            marketSummaryState = UiState.Loading
            val body = generalApi.getMarketSummary().body()
            marketSummaryState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            marketSummaryState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            marketSummaryState = UiState.Failure()
        }
    }

    var summaryState by mutableStateOf<UiState<GetSummaryRes>>(UiState.Initial)
        private set

    fun getSummary() = viewModelScope.launch {
        try {
            summaryState = UiState.Loading
            val body = generalApi.getSummary().body()
            summaryState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            summaryState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            summaryState = UiState.Failure()
        }
    }

    var goldsState by mutableStateOf<UiState<GetAllGoldsRes>>(UiState.Initial)
        private set

    fun getAllGolds() = viewModelScope.launch {
        try {
            goldsState = UiState.Loading
            val body = goldApi.getAllGolds().body()
            goldsState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            goldsState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            goldsState = UiState.Failure()
        }
    }

    init {
        getMarketSummary()
        getSummary()
        getAllGolds()
    }
}