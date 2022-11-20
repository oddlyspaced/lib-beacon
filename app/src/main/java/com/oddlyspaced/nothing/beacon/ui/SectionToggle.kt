package com.oddlyspaced.nothing.beacon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oddlyspaced.nothing.beacon.ui.theme.*

@Composable
fun SectionToggle(modifier: Modifier, text: String, defaultSwitchState: Boolean) {
    val switchState = remember {
        mutableStateOf(defaultSwitchState)
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = if (switchState.value) Main50 else Main10,
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, color = Main1000, fontSize = 20.sp)
            Switch(
                checked = switchState.value, onCheckedChange = {
                    switchState.value = it
                }, colors = SwitchDefaults.colors(
                    checkedThumbColor = Main1000,
                    uncheckedThumbColor = Main1000,
                    checkedTrackColor = Main200,
                    uncheckedTrackColor = Main800,
                )
            )
        }
    }
}