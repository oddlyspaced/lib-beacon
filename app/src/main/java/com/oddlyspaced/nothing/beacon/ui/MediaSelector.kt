package com.oddlyspaced.nothing.beacon.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.oddlyspaced.nothing.beacon.ui.component.Phone1
import com.oddlyspaced.nothing.beacon.ui.theme.Main50


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MediaSelector() {
    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = Main50) {
        Column(modifier = Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                count = 10,
                state = pagerState,
                contentPadding = PaddingValues(start = 64.dp, end = 64.dp),
                itemSpacing = 16.dp,
                modifier = Modifier
                    .weight(0.47F)
                    .fillMaxWidth()
            ) { page ->
                Phone1(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.47f),
                    0, 0, 0, 0, 0
                )
            }
        }
    }
}