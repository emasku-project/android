package id.my.rizalanggoro.emasku.presentation.pages.golds.index

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Balance
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.Update
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.rizalanggoro.emasku.core.LocalNavBackStack
import id.my.rizalanggoro.emasku.core.Routes
import id.my.rizalanggoro.emasku.core.extensions.formatDateTime
import id.my.rizalanggoro.emasku.core.isLoading
import id.my.rizalanggoro.emasku.core.managers.AuthManager
import id.my.rizalanggoro.emasku.core.onLoading
import id.my.rizalanggoro.emasku.core.onSuccess
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoldsScreen() {
    val viewModel = koinViewModel<GoldsViewModel>()
    val authManager = koinInject<AuthManager>()

    val backStack = LocalNavBackStack.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Emasku")
                },
                actions = {
                    IconButton(
                        onClick = {
                            authManager.update(isAuthenticated = false)
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.Logout,
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
            modifier = Modifier.padding(it),
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                with(viewModel.summaryState) {
                    var sellPrice = 0.0
                    var buyPrice = 0.0
                    var profit = 0.0
                    var profitPercentage = 0.0
                    this.onSuccess {
                        sellPrice = it.totalSellPrice.toDouble()
                        buyPrice = it.totalBuyPrice.toDouble()
                        profit = sellPrice - buyPrice
                        profitPercentage = (profit / buyPrice) * 100
                    }

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Total asset", style = MaterialTheme.typography.labelMedium)
                            Text(
                                "${
                                    sellPrice.let {
                                        NumberFormat.getCurrencyInstance().format(it)
                                    }
                                }",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    "Harga beli ${
                                        buyPrice.let {
                                            NumberFormat.getCurrencyInstance().format(it)
                                        }
                                    }",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.TrendingUp,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    "${
                                        profit.let {
                                            NumberFormat.getCurrencyInstance().format(it)
                                        }
                                    } (${"%.2f%%".format(profitPercentage)})",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
                                )
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Card(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(Icons.Rounded.Balance, contentDescription = null)
                                Column {
                                    with(viewModel.summaryState) {
                                        var totalWeight = 0.0
                                        this.onSuccess {
                                            totalWeight = it.totalWeight.toDouble()
                                        }

                                        Text(
                                            "${totalWeight.toFloat()} gram",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        "Total berat",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
                                    )
                                }
                            }
                        }
                        Card(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(Icons.Rounded.Tag, contentDescription = null)
                                Column {
                                    with(viewModel.goldsState) {
                                        var goldCount = 0
                                        this.onSuccess {
                                            goldCount = it.items.size
                                        }

                                        Text(
                                            goldCount.toString(),
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        "Total emas",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
                                    )
                                }
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Ringkasan harga",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(CardDefaults.shape)
                                    .clickable {}
                            ) {
                                with(viewModel.marketSummaryState) {
                                    var goldPrice = 0.0
                                    var goldUpdate = "-"
                                    this.onSuccess {
                                        goldPrice = it.globalXauPrice?.toDouble() ?: 0.0
                                        goldUpdate =
                                            it.globalXauUpdatedAt?.formatDateTime("dd/MM/yy hh:mm")
                                                ?: "-"
                                    }

                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Text(
                                            "Emas dunia",
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Text(
                                            goldPrice.let {
                                                NumberFormat.getCurrencyInstance(
                                                    Locale.forLanguageTag(
                                                        "us-US"
                                                    )
                                                ).format(it)
                                            },
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(
                                                Icons.Rounded.Update,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp),
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Text(
                                                goldUpdate,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onBackground.copy(
                                                    alpha = .7f
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(CardDefaults.shape)
                                    .clickable {}
                            ) {
                                with(viewModel.marketSummaryState) {
                                    var dollarRate = 0.0
                                    var dollarUpdate = "-"
                                    this.onSuccess {
                                        dollarRate = it.dollarRate?.toDouble() ?: 0.0
                                        dollarUpdate =
                                            it.dollarUpdatedAt?.formatDateTime("dd/MM/yy hh:mm")
                                                ?: "-"
                                    }

                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Text(
                                            "Nilai dollar",
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Text(
                                            dollarRate.let {
                                                NumberFormat.getCurrencyInstance().format(it)
                                            },
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(
                                                Icons.Rounded.Update,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp),
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Text(
                                                dollarUpdate,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onBackground.copy(
                                                    alpha = .7f
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            with(viewModel.marketSummaryState) {
                                var goldPrice = 0.0
                                var goldJewPrice = 0.0
                                this.onSuccess {
                                    goldPrice = it.xauPriceGram?.toDouble() ?: 0.0
                                    goldJewPrice = it.xauJewelryPriceGram?.toDouble() ?: 0.0
                                }

                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text("Harga emas", style = MaterialTheme.typography.labelMedium)
                                    Column {
                                        Text(
                                            goldPrice.let {
                                                NumberFormat.getCurrencyInstance().format(it)
                                            },
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            "Emas murni per gram",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onBackground.copy(
                                                alpha = .7f
                                            )
                                        )
                                    }

                                    Column {
                                        Text(
                                            goldJewPrice.let {
                                                NumberFormat.getCurrencyInstance().format(it)
                                            },
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            "Emas perhiasan per gram (75%)",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onBackground.copy(
                                                alpha = .7f
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    Text(
                        "Daftar emas",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                with(viewModel.goldsState) {
                    this
                        .onLoading {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp)
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                        .onSuccess { data ->
                            itemsIndexed(data.items) { index, it ->
                                OutlinedCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 16.dp,
                                            end = 16.dp,
                                            top = 8.dp,
                                            bottom = if (index == data.items.size - 1) (56 + 32).dp else 0.dp
                                        )
                                        .clip(CardDefaults.shape)
                                        .clickable {
                                            backStack.add(
                                                Routes.DetailGold(
                                                    goldId = it.gold.id
                                                )
                                            )
                                        }
                                ) {
                                    Column {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .clip(CircleShape)
                                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                            ) {
                                                Icon(
                                                    Icons.AutoMirrored.Rounded.TrendingUp,
                                                    contentDescription = null,
                                                    modifier = Modifier.align(Alignment.Center),
                                                    tint = MaterialTheme.colorScheme.primary
                                                )
                                            }

                                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(
                                                    it.gold.purchaseDate.formatDateTime(),
                                                    style = MaterialTheme.typography.labelMedium,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Text(
                                                    it.sellPrice.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    },
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    "${it.gold.weight.toFloat()} gram",
                                                    style = MaterialTheme.typography.labelMedium,
                                                    color = MaterialTheme.colorScheme.onBackground.copy(
                                                        alpha = .7f
                                                    ),
                                                )
                                            }
                                        }

                                        HorizontalDivider()

                                        Column(
                                            modifier = Modifier.padding(16.dp),
                                            verticalArrangement = Arrangement.spacedBy(2.dp)
                                        ) {
                                            Text(
                                                "Harga beli ${
                                                    it.gold.price.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    }
                                                }",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onBackground.copy(
                                                    alpha = .7f
                                                )
                                            )
                                            Text(
                                                "Keuntungan ${
                                                    it.profit.let {
                                                        NumberFormat.getCurrencyInstance()
                                                            .format(it)
                                                    }
                                                }" + " (%.2f%%)".format(((it.profit.toDouble() / it.gold.price.toDouble()) * 100.0)),
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onBackground.copy(
                                                    alpha = .7f
                                                )
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
}