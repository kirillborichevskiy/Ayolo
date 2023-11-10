package com.kirillborichevskiy.ayolo.ui.component.compound

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.component.AyoloPivotButton
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloText
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing

@Composable
internal fun NoChatsStub(
    modifier: Modifier = Modifier,
    onCreateNewChat: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_chats_stub))

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        AyoloText(
            text = stringResource(R.string.create_first_chat_now),
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        AyoloPivotButton(
            text = stringResource(R.string.create_new_chat),
            enabled = true,
            onClick = onCreateNewChat,
        )
    }
}
