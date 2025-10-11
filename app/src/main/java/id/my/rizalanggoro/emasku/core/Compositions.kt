package id.my.rizalanggoro.emasku.core

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

val LocalNavController = compositionLocalOf<NavController> {
    error("No NavController found")
}

val LocalNavBackStack = compositionLocalOf<NavBackStack<NavKey>> {
    error("No NavBackStack found")
}