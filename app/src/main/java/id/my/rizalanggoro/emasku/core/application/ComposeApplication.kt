package id.my.rizalanggoro.emasku.core.application

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import id.my.rizalanggoro.emasku.core.LocalNavBackStack
import id.my.rizalanggoro.emasku.core.Routes
import id.my.rizalanggoro.emasku.core.managers.AuthManager
import id.my.rizalanggoro.emasku.presentation.pages.auth.index.AuthScreen
import id.my.rizalanggoro.emasku.presentation.pages.golds.create.CreateGoldScreen
import id.my.rizalanggoro.emasku.presentation.pages.golds.detail.DetailGoldScreen
import id.my.rizalanggoro.emasku.presentation.pages.golds.index.GoldsScreen
import id.my.rizalanggoro.emasku.presentation.pages.market.detail.MarketDetailScreen
import id.my.rizalanggoro.emasku.presentation.pages.setting.SettingScreen
import id.my.rizalanggoro.emasku.ui.theme.EmaskuTheme
import org.koin.compose.koinInject

@Composable
fun ComposeApplication() {
    val authManager = koinInject<AuthManager>()

    val backStack = rememberNavBackStack(
        when (authManager.isAuthenticated) {
            true -> Routes.Setting
            else -> Routes.Auth
        }
    )

    LaunchedEffect(authManager.isAuthenticated) {
        if (!authManager.isAuthenticated) {
            backStack.removeAll { true }
            backStack.add(Routes.Auth)
        }
    }

    EmaskuTheme {
        CompositionLocalProvider(LocalNavBackStack provides backStack) {
            Surface {
                NavDisplay(
                    entryDecorators = listOf(
                        rememberSceneSetupNavEntryDecorator(),
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    backStack = backStack,
                    entryProvider = entryProvider {
                        // auth
                        entry<Routes.Auth> { AuthScreen() }

                        // golds
                        entry<Routes.Golds> { GoldsScreen() }
                        entry<Routes.CreateGold> { CreateGoldScreen() }
                        entry<Routes.DetailGold> { DetailGoldScreen(key = it) }

                        // market
                        entry<Routes.MarketDetail> { MarketDetailScreen(key = it) }

                        // setting
                        entry<Routes.Setting> { SettingScreen() }
                    }
                )
            }
        }
    }
}