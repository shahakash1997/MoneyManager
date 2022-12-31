package co.asinfotech.mymoneymanager.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.asinfotech.mymoneymanager.ui.components.listView.CustomItem
import co.asinfotech.mymoneymanager.ui.components.listView.PersonRepository
import co.asinfotech.mymoneymanager.ui.theme.MyApplicationTheme

class TestComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val personRepository = PersonRepository()
                val getAllData = personRepository.getAllData()

                Column {
                   /* LazyColumn(
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(items = getAllData) { person ->
                            CustomItem(person = person)
                        }
                    }*/
                    SectionList()
                }
            }
        }
    }

}


//use for showing detailsed txns month wise
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionList(){
    MyApplicationTheme() {
        val sections = listOf("A", "B", "C", "D", "E", "F", "G")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            sections.forEach { section ->
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(12.dp),
                        text = "Section $section"
                    )
                }
                items(10) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Item $it from the section $section"
                    )
                }
            }
        }
    }
}