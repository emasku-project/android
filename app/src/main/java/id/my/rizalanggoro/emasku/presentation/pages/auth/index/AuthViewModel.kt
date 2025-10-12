package id.my.rizalanggoro.emasku.presentation.pages.auth.index

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.rizalanggoro.emasku.core.UiState
import id.my.rizalanggoro.emasku.core.managers.TokenManager
import id.my.rizalanggoro.emasku.core.toFailure
import id.my.rizalanggoro.emasku.domain.Token
import id.my.rizalanggoro.emasku.openapi.apis.AuthApi
import id.my.rizalanggoro.emasku.openapi.models.LoginReq
import id.my.rizalanggoro.emasku.openapi.models.LoginRes
import id.my.rizalanggoro.emasku.openapi.models.RegisterReq
import id.my.rizalanggoro.emasku.openapi.models.RegisterRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) : ViewModel() {
    var loginState by mutableStateOf<UiState<LoginRes>>(UiState.Initial)
        private set

    fun resetLoginState() {
        loginState = UiState.Initial
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            loginState = UiState.Loading
            val body = authApi.login(
                body = LoginReq(
                    email = email.lowercase(),
                    password = password.lowercase()
                )
            ).body()

            tokenManager.set(
                token = Token(
                    accessToken = body.token
                )
            )

            loginState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            loginState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            loginState = UiState.Failure()
        }
    }

    var registerState by mutableStateOf<UiState<RegisterRes>>(UiState.Initial)
        private set

    fun resetRegisterState() {
        registerState = UiState.Initial
    }

    fun register(name: String, email: String, password: String) = viewModelScope.launch {
        try {
            registerState = UiState.Loading
            val body = authApi.register(
                body = RegisterReq(
                    name = name,
                    email = email.lowercase(),
                    password = password.lowercase()
                )
            ).body()

            tokenManager.set(
                token = Token(
                    accessToken = body.token
                )
            )

            registerState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            registerState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            registerState = UiState.Failure()
        }
    }
}