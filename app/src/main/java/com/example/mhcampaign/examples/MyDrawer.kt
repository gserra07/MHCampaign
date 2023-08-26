package com.example.mhcampaign.examples

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.CampaignView
import com.example.mhcampaign.HunterView
import com.example.mhcampaign.MyHunterSelectorPreview
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.model.MenuItem
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDrawer(
    scope: CoroutineScope,
    items: List<MenuItem>,
    selectedItem: MutableState<MenuItem>,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet {
            Spacer(Modifier.height(12.dp))
            items.forEach { item ->
                NavigationDrawerItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                    label = { Text(item.name) },
                    selected = item == selectedItem.value,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                        .width(200.dp),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }
    }) {
        content()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MyDrawerPreview() {
    MHCampaignTheme(darkTheme = false, dynamicColor = false) {
        val scope = rememberCoroutineScope()
        val menuItems = listOf(
            MenuItem(0, "Campaign", Icons.Rounded.DateRange),
            MenuItem(1, "Hunters", Icons.Rounded.AccountCircle),
            MenuItem(2, "Quick Guide", Icons.Rounded.Build),
            MenuItem(3, "Parts", Icons.Rounded.Build),
        )
        val selectedItem = remember { mutableStateOf(menuItems[0]) }
        val drawerState = rememberDrawerState(DrawerValue.Closed)

        val dataList = listOf(
            HunterData("Ganexy", HunterWeapon.DUALBLADES),
            HunterData("Adriatus", HunterWeapon.HEAVYBOWGUN),
            HunterData("Garatoth", HunterWeapon.SWITCHAXE),
            HunterData("Ingravitto", HunterWeapon.CHARGEBLADE),
            HunterData("Guille", HunterWeapon.LONGSWORD),
            HunterData("SpiderWolf", HunterWeapon.BOW)
        )

        MyDrawer(
            scope = scope,
            items = menuItems,
            selectedItem = selectedItem,
            drawerState = drawerState,
            content = {
                when (selectedItem.value.index) {
                    0 -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {
                            },
                            content = { CampaignView(dataList) })
                    }

                    1 -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {
                            },
                            content = { HunterView(dataList = dataList) })
                    }

                    2 -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {
                            },
                            content = { MyHunterSelectorPreview() })
                    }

                    else -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {
                            },
                            content = { MyInventoryPreview() })

                    }
                }
            })
    }
}