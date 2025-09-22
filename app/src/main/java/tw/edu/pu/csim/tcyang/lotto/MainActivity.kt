package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Play(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier = Modifier) {
    var lucky by remember { mutableIntStateOf((1..100).random()) }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            // 監聽整個 Box 的點擊事件，但不吃掉 Button 的事件
            .pointerInput(Unit) {
                detectTapGestures { offset: Offset ->
                    Toast.makeText(
                        context,
                        "螢幕觸控座標\nX: %.2f, Y: %.2f".format(offset.x, offset.y),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "樂透數字(1-100)為 $lucky")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { lucky = (1..100).random() }
            ) {
                Text("重新產生樂透碼")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LottoTheme {
        Play()
    }
}
