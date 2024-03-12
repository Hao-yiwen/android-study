package com.example.mycity.ui

import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.model.Place

@Composable
fun PlaceDetailScreen(
    myCityViewModel: MyCityViewModel,
    myCityUiState: MyCityUiState,
    modifier: Modifier
) {
    val currentPlace = myCityUiState.currentPlace

    if (currentPlace == null) {
        return
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentPlace.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))) {
            Text(
                text = stringResource(id = currentPlace.name),
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = stringResource(id = currentPlace.location),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
            )
        }
    }
}