package id.my.rizalanggoro.emasku.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Routes {
    @Serializable
    data object Auth : NavKey

    // golds
    @Serializable
    data object Golds : NavKey

    @Serializable
    data object CreateGold : NavKey

    @Serializable
    data class DetailGold(
        val goldId: Int
    ) : NavKey

    // market
    @Serializable
    data class MarketDetail(
        val marketType: Int,
    ) : NavKey

    // setting
    @Serializable
    data object Setting : NavKey
}
