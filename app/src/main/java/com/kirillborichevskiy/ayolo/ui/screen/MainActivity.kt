package com.kirillborichevskiy.ayolo.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Alignment
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyoloTheme {
                val navController = rememberNavController()

                val navHostEngine = rememberAnimatedNavHostEngine(
                    navHostContentAlignment = Alignment.TopCenter,
                    rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
                    defaultAnimationsForNestedNavGraph = mapOf(
                        NavGraphs.root to NestedNavGraphDefaultAnimations(
                            enterTransition = { slideInHorizontally() },
                            exitTransition = { slideOutHorizontally() },
                        ),
                    ),
                )
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    engine = navHostEngine,
                )
            }
        }
    }
}
