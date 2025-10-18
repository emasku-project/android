package id.my.rizalanggoro.emasku.core.di

import id.my.rizalanggoro.emasku.presentation.pages.auth.index.AuthViewModel
import id.my.rizalanggoro.emasku.presentation.pages.golds.create.CreateGoldViewModel
import id.my.rizalanggoro.emasku.presentation.pages.golds.detail.DetailGoldViewModel
import id.my.rizalanggoro.emasku.presentation.pages.golds.index.GoldsViewModel
import id.my.rizalanggoro.emasku.presentation.pages.setting.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::GoldsViewModel)
    viewModelOf(::CreateGoldViewModel)
    viewModelOf(::DetailGoldViewModel)
    viewModelOf(::SettingViewModel)
}