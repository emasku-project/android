package id.my.rizalanggoro.emasku.presentation.pages.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import id.my.rizalanggoro.emasku.core.onSuccess
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    val viewModel = koinViewModel<SettingViewModel>()
    val backStack = LocalNavBackStack.current

    var taxStr by remember { mutableStateOf("") }
    val tax = taxStr.toFloatOrNull() ?: 0.0
//    var correctionStr by remember { mutableStateOf("") }
//    val correction = correctionStr.toFloatOrNull() ?: 0.0

    LaunchedEffect(viewModel.settingsState) {
        with(viewModel) {
            settingsState.onSuccess {
                taxStr = it.taxPercentage.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            backStack.removeLastOrNull()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text("Pengaturan")
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
                Text("Persentase pajak", style = MaterialTheme.typography.titleSmall)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = taxStr,
                        onValueChange = {
                            if (it.matches(Regex("^\\d*\\.?\\d*\$"))) {
                                taxStr = it
                            }
                        },
                        placeholder = {
                            Text("Masukkan persentase pajak")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    FilledIconButton(onClick = {}) {
                        Icon(Icons.Rounded.Check, contentDescription = null)
                    }
                }
            }

//            Column(
//                modifier = Modifier.padding(horizontal = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                Text("Persentase koreksi", style = MaterialTheme.typography.titleSmall)
//                Row(
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    TextField(
//                        value = correctionStr,
//                        onValueChange = {
//                            if (it.matches(Regex("^\\d*\\.?\\d*\$"))) {
//                                correctionStr = it
//                            }
//                        },
//                        placeholder = {
//                            Text("Masukkan persentase koreksi")
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    )
//                    FilledIconButton(onClick = {}) {
//                        Icon(Icons.Rounded.Check, contentDescription = null)
//                    }
//                }
//            }
        }
    }
}