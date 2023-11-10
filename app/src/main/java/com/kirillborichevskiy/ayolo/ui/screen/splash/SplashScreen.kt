package com.kirillborichevskiy.ayolo.ui.screen.splash

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.navigation.NavigationRoute
import com.kirillborichevskiy.ayolo.ui.screen.chats.ChatsViewModel
import com.kirillborichevskiy.ayolo.ui.screen.destinations.ChatsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@RootNavGraph(start = true)
@Destination(route = NavigationRoute.SPLASH_SCREEN)
internal fun SplashScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<ChatsViewModel>(LocalContext.current as ComponentActivity)
    val isChatsLoading by viewModel.isChatsLoading.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = isChatsLoading) {
        if (isChatsLoading.not()) navigator.navigate(ChatsScreenDestination)
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_ayolo),
            contentDescription = null,
        )
    }
}
