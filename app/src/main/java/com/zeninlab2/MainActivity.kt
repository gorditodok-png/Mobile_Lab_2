package com.zeninlab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zeninlab2.ui.ArtGalleryScreen
import com.zeninlab2.ui.theme.Mobile_Lab_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile_Lab_2Theme {
                ArtGalleryScreen()
            }
        }
    }
}
