package id.my.rizalanggoro.emasku.core.di

import id.my.rizalanggoro.emasku.BuildConfig
import id.my.rizalanggoro.emasku.core.managers.TokenManager
import id.my.rizalanggoro.emasku.openapi.apis.AuthApi
import id.my.rizalanggoro.emasku.openapi.apis.GeneralApi
import id.my.rizalanggoro.emasku.openapi.apis.GoldApi
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

private const val API_BASE_URL = BuildConfig.API_BASE_URL

val serviceModule = module {
    single<((HttpClientConfig<*>) -> Unit)> {
        {
            it.expectSuccess = true

            it.install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            it.install(Auth) {
                bearer {
                    loadTokens {
                        val tokenManager = get<TokenManager>()
                        val token = tokenManager.get()

                        BearerTokens(
                            accessToken = token.accessToken,
                            refreshToken = null
                        )
                    }
                }
            }
        }
    }

    // api services
    single { AuthApi(API_BASE_URL, httpClientConfig = get()) }
    single { GoldApi(API_BASE_URL, httpClientConfig = get()) }
    single { GeneralApi(API_BASE_URL, httpClientConfig = get()) }
}