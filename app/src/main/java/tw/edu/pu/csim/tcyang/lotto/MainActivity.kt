package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoordinateDisplay(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CoordinateDisplay(modifier: Modifier = Modifier) {
    var coordinates by remember { mutableStateOf(Offset(0f, 0f)) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitEachGesture {
                    val down = awaitFirstDown() // 等待按下
                    coordinates = down.position
                    while (true) {
                        val event = awaitPointerEvent()
                        val pos = event.changes.first().position
                        coordinates = pos
                        if (event.changes.all { !it.pressed }) break // 放開時結束
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "觸控螢幕顯示座標 (即時追蹤)")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "X: ${"%.2f".format(coordinates.x)}",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Y: ${"%.2f".format(coordinates.y)}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LottoTheme {
        CoordinateDisplay()
    }
}
