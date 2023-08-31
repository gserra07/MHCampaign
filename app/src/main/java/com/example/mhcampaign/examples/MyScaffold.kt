package com.example.mhcampaign.examples

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mhcampaign.HunterViewHolderPreview
import com.example.mhcampaign.model.MenuItem
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(title: String,
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
