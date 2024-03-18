package com.example.amphibians.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amohibians

@Composable
fun AmohibiansScreen(amohibiansList: List<Amohibians>, modifier: Modifier) {
    Log.d("AmohibiansScreen", "AmohibiansScreen: ${amohibiansList}")
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(
            amohibiansList,
            key = { index, amohibians -> "${index}-${amohibians.name}" }) { index, amohibians ->
            AmohibiansItem(amohibians, Modifier.padding(10.dp))
        }
    }
}

@Composable
fun AmohibiansItem(amphibian: Amohibians, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(text = amphibian.name, modifier = Modifier.padding(10.dp))
        AsyncImage(
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            model = ImageRequest.Builder(context = LocalContext.current).data(amphibian.img_src)
                .crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Text(text = amphibian.description, modifier = Modifier.padding(10.dp))
    }
}