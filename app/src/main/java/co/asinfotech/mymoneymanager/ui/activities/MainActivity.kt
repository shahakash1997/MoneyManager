package co.asinfotech.mymoneymanager.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.asinfotech.mymoneymanager.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", onClicked = {
                        startNewActivity()
                    })
                }
            }
        }
    }

    fun startNewActivity() {
        val intent = Intent(this, TestComposeActivity::class.java)
        startActivity(intent)
    }

}


@Composable
fun Greeting(name: String, onClicked: () -> Unit) {
    Column {
        Button(onClick = {
            onClicked()
        }) {

            Text(text = "Navigate")

        }
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android", onClicked = {})
    }
}