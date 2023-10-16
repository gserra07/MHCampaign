package com.example.mhcampaign.examples

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.ui.theme.md_theme_dark_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer
import com.example.mhcampaign.ui.theme.mhFont
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String, scope: CoroutineScope, drawerState: DrawerState, onClickListener: (String) -> Unit) {
    Surface(shadowElevation = 3.dp) {
        TopAppBar(title = { Text(text = title, fontFamily = mhFont) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = md_theme_dark_primary),
                modifier = Modifier.background(color = md_theme_light_primaryContainer),
                navigationIcon = {
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                    }
                }, actions = {
            IconButton(onClick = { onClickListener("Menu") }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
            }
        })
    }
}