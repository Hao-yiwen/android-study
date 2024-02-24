package com.example.kotlinandroid

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinandroid.ui.theme.KotlinAndroidTheme

class FirstComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            Surface(modifier = Modifier.fillMaxSize()) {
                Example()
            }
        }
    }

}

@Composable
fun Example() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(200.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.yiwen_teacher),
                contentDescription = "Image",
                modifier = Modifier.width(100.dp),
            )
            Text(
                text = "Full Name", style = MaterialTheme.typography.titleLarge,
            )
            Text(text = "Title", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(100.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RowContainer("电话","10321321")
            RowContainer("邮箱","yiwenlemo@gmail.com")
        }
    }
}

@Composable
fun RowContainer(title: String, content: String) {
    Row {
        Text(text = title + ": ")
        Text(text = content)
    }
}

@Preview(showBackground = true)
@Composable
fun ExamplePreview() {
    KotlinAndroidTheme {
        Example()
    }
}
