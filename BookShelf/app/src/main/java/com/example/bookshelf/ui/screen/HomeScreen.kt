package com.example.bookshelf.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.state.BookShelfType
import com.example.bookshelf.state.BookShelfViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: BookShelfViewModel) {
    val ImageLinkList = viewModel.uiState.collectAsState().value.ImageLinkList
    when (ImageLinkList) {
        is BookShelfType.Loading -> {
            LoadingScreen(modifier = modifier)
        }

        is BookShelfType.Success -> {
            BookShelfList(modifier = modifier, ImageLinkList = ImageLinkList.data)
        }

        is BookShelfType.Error -> {
            ErrorScreen(modifier = modifier)
        }
    }
}

@Composable
fun BookShelfList(modifier: Modifier, ImageLinkList: List<ImageLinks>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(ImageLinkList) { ImageLink ->
            BookShelfItem(modifier = Modifier.padding(10.dp), ImageLink = ImageLink)
        }
    }
}

@Composable
fun BookShelfItem(modifier: Modifier, ImageLink: ImageLinks) {
    Log.d("BookShelfModule", "BookShelfItem: ${ImageLink.thumbnail}")
    Card(modifier = modifier.padding(2.dp)) {
        AsyncImage(
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            model = ImageRequest.Builder(LocalContext.current).data(ImageLink.thumbnail.replace("http://", "https://"))
                .crossfade(true)
                .build(), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
    }
}