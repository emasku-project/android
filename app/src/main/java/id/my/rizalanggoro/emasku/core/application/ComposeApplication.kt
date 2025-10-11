package id.my.rizalanggoro.emasku.core.application

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import id.my.rizalanggoro.emasku.core.LocalNavBackStack
import id.my.rizalanggoro.emasku.core.Routes
import id.my.rizalanggoro.emasku.presentation.pages.auth.index.AuthScreen
import id.my.rizalanggoro.emasku.presentation.pages.golds.create.CreateGoldScreen
import id.my.rizalanggoro.emasku.presentation.pages.golds.detail.DetailGoldScreen
import id.my.rizalanggoro.emasku.presentation.pages.golds.index.GoldsScreen
import id.my.rizalanggoro.emasku.ui.theme.EmaskuTheme

@Composable
fun ComposeApplication(isAuthenticated: Boolean = false) {
    val backStack = rememberNavBackStack(
        when (isAuthenticated) {
            true -> Routes.Golds
            else -> Routes.Auth
        }
    )

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
                    }
                )
            }
        }
    }
}