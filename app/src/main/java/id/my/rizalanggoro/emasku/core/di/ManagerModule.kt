package id.my.rizalanggoro.emasku.core.di

import id.my.rizalanggoro.emasku.core.TokenManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::TokenManager)
}