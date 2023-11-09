package com.kirillborichevskiy.ayolo.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyoloTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
