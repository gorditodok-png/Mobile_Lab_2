package com.zeninlab2.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeninlab2.R
import com.zeninlab2.data.Artwork
import com.zeninlab2.ui.theme.Mobile_Lab_2Theme

@Composable
fun ArtGalleryScreen() {
    val artworks = listOf(
        Artwork(R.drawable.mona_lisa, R.string.artwork_title_1, R.string.artwork_artist_1, R.string.artwork_year_1),
        Artwork(R.drawable.eiffel_tower, R.string.artwork_title_2, R.string.artwork_artist_2, R.string.artwork_year_2),
        Artwork(R.drawable.david, R.string.artwork_title_3, R.string.artwork_artist_3, R.string.artwork_year_3),
        Artwork(R.drawable.star_night, R.string.artwork_title_4, R.string.artwork_artist_4, R.string.artwork_year_4),
        Artwork(R.drawable.tadj_mahal, R.string.artwork_title_5, R.string.artwork_artist_5, R.string.artwork_year_5)
    )

    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    ArtworkImage(artwork = currentArtwork, modifier = Modifier.width(300.dp))
                    ArtworkDescription(artwork = currentArtwork)
                }
            } else { // Portrait
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ArtworkImage(artwork = currentArtwork, modifier = Modifier.height(400.dp).padding(bottom = 32.dp))
                    ArtworkDescription(artwork = currentArtwork, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { currentIndex-- },
                enabled = currentIndex > 0,
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.previous_button))
            }
            Button(
                onClick = { currentIndex++ },
                enabled = currentIndex < artworks.size - 1,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.next_button))
            }
        }
    }
}

@Composable
fun ArtworkImage(artwork: Artwork, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.shadow(elevation = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Image(
            painter = painterResource(id = artwork.imageResId),
            contentDescription = stringResource(id = artwork.titleResId),
            modifier = Modifier
                .fillMaxSize()
                .border(width = 2.dp, color = Color.Gray)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ArtworkDescription(artwork: Artwork, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = artwork.titleResId),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "${stringResource(id = artwork.artistResId)} (${stringResource(id = artwork.yearResId)})",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun DefaultPreview() {
    Mobile_Lab_2Theme {
        ArtGalleryScreen()
    }
}

@Preview(showBackground = true, device = "spec:width=891dp,height=411dp")
@Composable
fun LandscapePreview() {
    Mobile_Lab_2Theme {
        ArtGalleryScreen()
    }
}
