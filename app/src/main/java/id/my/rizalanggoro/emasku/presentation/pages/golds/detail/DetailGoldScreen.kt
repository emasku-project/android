package id.my.rizalanggoro.emasku.presentation.pages.golds.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailGoldScreen(key: Routes.DetailGold) {
    val viewModel = koinViewModel<DetailGoldViewModel>(parameters = { parametersOf(key) })

    val backStack = LocalNavBackStack.current
    val snackbarHostState = remember { SnackbarHostState() }

    var isDeleteOpen by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.deleteState) {
        with(viewModel.deleteState) {
            this
                .onSuccess {
                    isDeleteOpen = false
                    backStack.removeLastOrNull()
                }
                .onFailure {
                    isDeleteOpen = false
                    snackbarHostState.showSnackbar(message = it)
                    viewModel.resetDeleteState()
                }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            backStack.removeLastOrNull()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Detail")
                },
                actions = {
                    IconButton(
                        onClick = {
                            isDeleteOpen = true
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        PullToRefreshBox(isRefreshing = false, onRefresh = {}) {
            LazyColumn(modifier = Modifier.padding(it)) {
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text("Tanggal beli: ")
                        Text("Lama: ")
                        Text("Berat: ")
                        Text("Harga beli: ")
                        Text("Harga jual: ")
                        Text("Keuntungan: ")
                    }
                }
            }
        }

        // modals
        if (isDeleteOpen || viewModel.deleteState.isLoading())
            AlertDialog(
                title = {
                    Text(
                        when (viewModel.deleteState.isLoading()) {
                            true -> "Menghapus"
                            else -> "Konfirmasi Hapus"
                        }
                    )
                },
                text = {
                    when (viewModel.deleteState.isLoading()) {
                        true -> Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        else -> Text(
                            "Apakah Anda yakin akan menghapus emas ini? " +
                                    "Tindakan yang Anda lakukan tidak dapat dipulihkan."
                        )
                    }
                },
                onDismissRequest = {
                    if (!viewModel.deleteState.isLoading())
                        isDeleteOpen = false
                },
                confirmButton = {
                    when (viewModel.deleteState.isLoading()) {
                        true -> Unit
                        else -> TextButton(
                            onClick = {
                                viewModel.delete()
                            }
                        ) {
                            Text("Hapus")
                        }
                    }
                },
                dismissButton = {
                    when (viewModel.deleteState.isLoading()) {
                        true -> Unit
                        else -> TextButton(
                            onClick = {
                                isDeleteOpen = false
                            }
                        ) {
                            Text("Batal")
                        }
                    }
                },
            )
    }
}