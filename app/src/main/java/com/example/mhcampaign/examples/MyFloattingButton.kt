package com.example.mhcampaign.examples

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun MyFloatingActionButton(onFloatingActionButtonClick: () -> Unit) {
    FloatingActionButton(onClick = { onFloatingActionButtonClick() }, shape = CircleShape) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}