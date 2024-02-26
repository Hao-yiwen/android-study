package com.example.gridexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridexample.data.DataSource
import com.example.gridexample.model.Topic
import com.example.gridexample.ui.theme.GridExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GridList(DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun GridList(topics: List<Topic>, modifier: Modifier = Modifier.fillMaxSize()) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(topics) {
            TopicCard(
                topic = it, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    })
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier.fillMaxSize()) {
    Card(modifier = modifier) {
        Row() {
            Image(painter = painterResource(id = topic.image), contentDescription = null)
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = stringResource(id = topic.title))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Text(text = topic.followers.toString(),modifier=Modifier.padding(start = 4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GridExampleTheme {

    }
}