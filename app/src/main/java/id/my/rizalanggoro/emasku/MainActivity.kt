package id.my.rizalanggoro.emasku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.my.rizalanggoro.emasku.core.TokenManager
import id.my.rizalanggoro.emasku.core.application.ComposeApplication
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val tokenManager by inject<TokenManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplication(
                isAuthenticated = !tokenManager.isEmpty()
            )
        }
    }
}