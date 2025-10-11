package id.my.rizalanggoro.emasku.presentation.pages.golds.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.my.rizalanggoro.emasku.core.LocalNavBackStack
import id.my.rizalanggoro.emasku.core.extensions.formatDateTime
import id.my.rizalanggoro.emasku.core.isLoading
import id.my.rizalanggoro.emasku.core.onFailure
import id.my.rizalanggoro.emasku.core.onSuccess
import id.my.rizalanggoro.emasku.presentation.components.DatePickerModal
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoldScreen() {
    val viewModel = koinViewModel<CreateGoldViewModel>()
    val backStack = LocalNavBackStack.current

    val snackbarHostState = remember { SnackbarHostState() }

    var weightStr by remember { mutableStateOf("") }
    val weight = weightStr.toDoubleOrNull() ?: 0.0
    var buyPriceStr by remember { mutableStateOf("") }
    val buyPrice = buyPriceStr.toIntOrNull() ?: 0
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var isDatePickerOpen by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.createState) {
        with(viewModel.createState) {
            this
                .onSuccess {
                    backStack.removeLastOrNull()
                }
                .onFailure {
                    snackbarHostState.showSnackbar(
                        message = it
                    )
                }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        backStack.removeLastOrNull()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Tambah Emas")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = weightStr,
                    onValueChange = {
                        if (it.matches(Regex("^\\d*\\.?\\d*\$"))) {
                            weightStr = it
                        }
                    },
                    placeholder = {
                        Text("Masukkan berat")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = buyPriceStr,
                    onValueChange = {
                        if (it.matches(Regex("^\\d*\$"))) {
                            buyPriceStr = it
                        }
                    },
                    placeholder = {
                        Text("Masukkan harga beli")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedButton(
                    onClick = {
                        isDatePickerOpen = true
                    }
                ) {
                    Text(
                        when (selectedDate != null) {
                            true -> selectedDate!!.formatDateTime()
                            else -> "Pilih tanggal beli"
                        }
                    )
                }
            }

            Button(
                onClick = {
                    if (selectedDate != null)
                        viewModel.create(
                            price = buyPrice,
                            purchaseDate = selectedDate!!,
                            weight = weight
                        )
                },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End),
                enabled = !viewModel.createState.isLoading()
            ) {
                Text("Simpan")
            }

            if (viewModel.createState.isLoading())
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
        }

        // modals
        if (isDatePickerOpen)
            DatePickerModal(
                initialMillis = selectedDate,
                onDateSelected = {
                    selectedDate = it
                },
                onDismiss = {
                    isDatePickerOpen = false
                }
            )
    }
}