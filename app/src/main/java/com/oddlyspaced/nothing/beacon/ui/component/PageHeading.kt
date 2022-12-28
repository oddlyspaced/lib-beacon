package com.oddlyspaced.nothing.beacon.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PageHeading(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, fontSize = 44.sp, fontWeight = FontWeight.Medium)
}
