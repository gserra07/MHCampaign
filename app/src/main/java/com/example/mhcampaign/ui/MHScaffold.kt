package com.example.mhcampaign.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mhcampaign.examples.MyTopAppBar
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MHScaffold(title: String,
               scope: CoroutineScope,
               drawerState: DrawerState,
               onFloatingActionButtonClick: (() -> Unit)? = null,
               onFloatingButtonContent: @Composable (() -> Unit),
               content: @Composable () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    MHCampaignTheme(darkTheme = false, dynamicColor = false) {
        Scaffold(topBar = {
            MyTopAppBar(title, scope, drawerState) { message ->
                scope.launch {
                    snackbarHostState.showSnackbar(message = message)
                }
            }
        },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                floatingActionButton = {
                    if (onFloatingActionButtonClick != null)
                        onFloatingButtonContent()
                    //MyFloatingActionButton(onFloatingActionButtonClick)
                },
                content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        content()
                    }
                })
    }
}
