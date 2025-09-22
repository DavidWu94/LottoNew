package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
    var lucky by remember {
        mutableIntStateOf((1..100).random())
    }

    // 取得目前的 Context，用來顯示 Toast 訊息
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            // 新增 .clickable 修飾符，點擊時顯示 Toast 訊息
            .clickable {
                Toast.makeText(context, "螢幕觸控(資管二A 吳岱威)", Toast.LENGTH_SHORT).show()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "樂透數字(1-100)為 $lucky"
        )

        Button(
            onClick = { lucky = (1..100).random() }
        ) {
            Text("重新產生樂透碼")
        }
    }
}