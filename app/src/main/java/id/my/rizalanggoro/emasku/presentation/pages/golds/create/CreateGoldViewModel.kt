package id.my.rizalanggoro.emasku.presentation.pages.golds.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.rizalanggoro.emasku.core.UiState
import id.my.rizalanggoro.emasku.core.extensions.formatDateTime
import id.my.rizalanggoro.emasku.core.toFailure
import id.my.rizalanggoro.emasku.openapi.apis.GoldApi
import id.my.rizalanggoro.emasku.openapi.models.CreateGoldReq
import id.my.rizalanggoro.emasku.openapi.models.CreateGoldRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class CreateGoldViewModel(
    private val goldApi: GoldApi
) : ViewModel() {
    var createState by mutableStateOf<UiState<CreateGoldRes>>(UiState.Initial)
        private set

    fun create(price: Int, purchaseDate: Long, weight: Double) = viewModelScope.launch {
        try {
            createState = UiState.Loading
            val body = goldApi.createGold(
                body = CreateGoldReq(
                    note = "",
                    price = price.toBigDecimal(),
                    purchaseDate = purchaseDate.formatDateTime("yyyy-MM-dd"),
                    weight = weight.toBigDecimal()
                )
            ).body()
            createState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            createState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            createState = UiState.Failure()
        }
    }
}