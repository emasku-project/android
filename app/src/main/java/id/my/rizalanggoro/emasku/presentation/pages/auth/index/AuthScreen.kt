package id.my.rizalanggoro.emasku.presentation.pages.auth.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.rizalanggoro.emasku.core.LocalNavBackStack
import id.my.rizalanggoro.emasku.core.Routes
import id.my.rizalanggoro.emasku.core.isLoading
import id.my.rizalanggoro.emasku.core.onFailure
import id.my.rizalanggoro.emasku.core.onSuccess
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {
    val viewModel = koinViewModel<AuthViewModel>()

    val backStack = LocalNavBackStack.current
    val snackbarHostState = remember { SnackbarHostState() }

    var isLogin by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.loginState, viewModel.registerState) {
        with(viewModel) {
            loginState
                .onSuccess {
                    backStack.removeAll { true }
                    backStack.add(Routes.Golds)
                }
                .onFailure {
                    snackbarHostState.showSnackbar(it)
                    viewModel.resetLoginState()
                }

            registerState
                .onSuccess {
                    backStack.removeAll { true }
                    backStack.add(Routes.Golds)
                }
                .onFailure {
                    snackbarHostState.showSnackbar(it)
                    viewModel.resetRegisterState()
                }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Autentikasi")
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!isLogin)
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text("Masukkan nama lengkap") },
                        modifier = Modifier.fillMaxWidth()
                    )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Masukkan alamat email") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Masukkan kata sandi") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = {
                        isLogin = !isLogin
                    },
                    enabled = !viewModel.loginState.isLoading() ||
                            !viewModel.registerState.isLoading()
                ) {
                    Text(
                        when (isLogin) {
                            true -> "Menu registrasi"
                            else -> "Menu masuk"
                        }
                    )
                }

                Button(
                    onClick = {
                        when (isLogin) {
                            true -> viewModel.login(
                                email = email,
                                password = password
                            )

                            else -> viewModel.register(
                                name = name,
                                email = email,
                                password = password
                            )
                        }
                    },
                    enabled = !viewModel.loginState.isLoading() ||
                            !viewModel.registerState.isLoading()
                ) {
                    Text(
                        when (isLogin) {
                            true -> "Masuk"
                            else -> "Daftar"
                        }
                    )
                }
            }

            if (viewModel.loginState.isLoading() || viewModel.registerState.isLoading())
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
        }
    }
}