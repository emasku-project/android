package id.my.rizalanggoro.emasku.presentation.pages.golds.index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.rizalanggoro.emasku.core.LocalNavBackStack
import id.my.rizalanggoro.emasku.core.Routes
import id.my.rizalanggoro.emasku.core.UiState
import id.my.rizalanggoro.emasku.core.extensions.formatDateTime
import id.my.rizalanggoro.emasku.core.isLoading
import id.my.rizalanggoro.emasku.core.onLoading
import id.my.rizalanggoro.emasku.core.onSuccess
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoldsScreen() {
    val viewModel = koinViewModel<GoldsViewModel>()

    val backStack = LocalNavBackStack.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Daftar Emas")
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.Rounded.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                backStack.add(Routes.CreateGold)
            }) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        PullToRefreshBox(
            isRefreshing = viewModel.marketSummaryState.isLoading() ||
                    viewModel.summaryState.isLoading() ||
                    viewModel.goldsState.isLoading(),
            onRefresh = {
                viewModel.getMarketSummary()
                viewModel.getSummary()
                viewModel.getAllGolds()
            },
        ) {
            LazyColumn(
                modifier = Modifier.padding(it),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // global price
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            with(viewModel.marketSummaryState) {
                                when (this) {
                                    is UiState.Loading -> CircularProgressIndicator()
                                    is UiState.Success -> {
                                        with(this.data) {
                                            Text(
                                                "Harga dunia: ${
                                                    globalXauPrice.let {
                                                        NumberFormat.getCurrencyInstance(
                                                            Locale.forLanguageTag("us-US")
                                                        ).format(it)
                                                    }
                                                } per ons"
                                            )
                                            Text("Update: ${globalXauUpdatedAt?.formatDateTime()}")
                                            Text(
                                                "Nilai tukar dollar: $1 -> Rp${
                                                    dollarRate.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    }
                                                }"
                                            )
                                            Text("Update: ${dollarUpdatedAt?.formatDateTime()}")
                                            Text(
                                                "Harga emas: Rp${
                                                    xauPriceOunce.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    }
                                                } per ons"
                                            )
                                            Text(
                                                "Harga emas: Rp${
                                                    xauPriceGram.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    }
                                                } per gram"
                                            )
                                            Text(
                                                "Harga perhiasan: Rp${
                                                    xauJewelryPriceGram.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    }
                                                } per gram"
                                            )
                                        }
                                    }

                                    else -> Text("Terjadi kesalahan!")
                                }
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            with(viewModel.summaryState) {
                                this
                                    .onLoading {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(
                                                Alignment.CenterHorizontally
                                            )
                                        )
                                    }
                                    .onSuccess {
                                        Text(
                                            "Total harga beli: ${
                                                it.totalBuyPrice.let {
                                                    NumberFormat.getCurrencyInstance().format(it)
                                                }
                                            }"
                                        )
                                        Text("Total berat: ${it.totalWeight.toFloat()}gram")
                                        Text(
                                            "Total harga jual: ${
                                                it.totalSellPrice.let {
                                                    NumberFormat.getCurrencyInstance().format(it)
                                                }
                                            }"
                                        )
                                        Text(
                                            "Total profit: ${
                                                it.totalProfit.let {
                                                    NumberFormat.getCurrencyInstance().format(it)
                                                }
                                            }"
                                        )
                                    }
                            }
                        }
                    }
                }

                with(viewModel.goldsState) {
                    this
                        .onLoading {
                            item {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                        .onSuccess {
                            items(it.items) {
                                OutlinedCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .clickable {
                                            backStack.add(
                                                Routes.DetailGold(
                                                    goldId = it.gold.id
                                                )
                                            )
                                        }
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text("Tanggal beli: ${it.gold.purchaseDate?.formatDateTime()}")
                                        Text("Lama: ${it.durationInDays} hari")
                                        Text("Berat: ${it.gold.weight} gram")
                                        Text(
                                            "Harga beli: ${
                                                it.gold.price.let {
                                                    NumberFormat.getCurrencyInstance().format(it)
                                                }
                                            }"
                                        )
                                        Text(
                                            "Harga jual: ${
                                                it.sellPrice.let {
                                                    NumberFormat.getCurrencyInstance().format(it)
                                                }
                                            }"
                                        )
                                        Text(
                                            "Keuntungan: ${
                                                it.profit.let {
                                                    NumberFormat.getCurrencyInstance().format(it)
                                                }
                                            }"
                                        )
                                    }
                                }
                            }
                        }
                }
            }
        }
    }
}