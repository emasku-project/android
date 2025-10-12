package id.my.rizalanggoro.emasku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.my.rizalanggoro.emasku.core.application.ComposeApplication
import id.my.rizalanggoro.emasku.core.managers.AuthManager
import id.my.rizalanggoro.emasku.core.managers.TokenManager
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val tokenManager by inject<TokenManager>()
    private val authManager by inject<AuthManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        authManager.update(
            isAuthenticated = !tokenManager.isEmpty()
        )

        setContent {
            ComposeApplication()
        }
    }
}