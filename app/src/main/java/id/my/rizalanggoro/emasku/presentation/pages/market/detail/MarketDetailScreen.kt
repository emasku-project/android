package id.my.rizalanggoro.emasku.presentation.pages.market.detail

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.common.ChartColor
import com.himanshoe.charty.common.LabelConfig
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.model.LineData
import id.my.rizalanggoro.emasku.core.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketDetailScreen(key: Routes.MarketDetail) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Detail Pasar")
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                LineChart(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .aspectRatio(3 / 2f),
                    data = {
                        listOf(
                            LineData(10f, "Jan"),
                            LineData(40f, "Feb"),
                            LineData(25f, "Mar")
                        )
                    },
                    labelConfig = LabelConfig(
                        showXLabel = true,
                        showYLabel = true,
                        textColor = ChartColor.Solid(MaterialTheme.colorScheme.onBackground),
                        labelTextStyle = null,
                        xAxisCharCount = null,
                    ),
                    smoothLineCurve = true,
                    showFilledArea = true,
                    showLineStroke = true,
                    onClick = { lineData -> println("Clicked: ${lineData.xValue} -> ${lineData.yValue}") }
                )
            }
        }
    }
}