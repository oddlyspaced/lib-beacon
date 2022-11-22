package com.oddlyspaced.nothing.beacon.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.oddlyspaced.nothing.beacon.ui.theme.Main100
import com.oddlyspaced.nothing.beacon.ui.theme.Main50
import kotlinx.coroutines.launch


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