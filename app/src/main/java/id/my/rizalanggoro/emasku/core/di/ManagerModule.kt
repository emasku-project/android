package id.my.rizalanggoro.emasku.core.di

import id.my.rizalanggoro.emasku.core.managers.AuthManager
import id.my.rizalanggoro.emasku.core.managers.TokenManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::AuthManager)
    singleOf(::TokenManager)
}