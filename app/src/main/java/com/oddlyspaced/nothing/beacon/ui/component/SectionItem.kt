package com.oddlyspaced.nothing.beacon.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SectionItem(modifier: Modifier, title: String, subtitle: String, onClick: () -> Unit = {}) {
    Column(modifier = modifier.fillMaxWidth().clickable {
        onClick()
    }) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Text(subtitle, fontSize = 14.sp)
    }
}
