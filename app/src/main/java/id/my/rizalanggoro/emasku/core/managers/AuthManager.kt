package id.my.rizalanggoro.emasku.core.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AuthManager {
    var isAuthenticated by mutableStateOf(false)
        private set

    fun update(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }
}