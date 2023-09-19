package com.example.mhcampaign

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.campaign.CampaignView
import com.example.mhcampaign.campaign.CampaignViewModel
import com.example.mhcampaign.huntersView.HunterView
import com.example.mhcampaign.huntersView.HuntersViewModel
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.MenuItem
import com.example.mhcampaign.model.MonsterData
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.model.enums.PartItem
import com.example.mhcampaign.model.enums.PartModel
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import com.example.mhcampaign.ui.theme.md_theme_dark_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MHDrawer(
    scope: CoroutineScope,
    items: List<MenuItem>,
    selectedItem: MutableState<MenuItem>,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(drawerContainerColor = md_theme_light_primaryContainer) {
            Spacer(Modifier.height(12.dp))
            items.forEach { item ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                    },
                    label = { Text(item.name) },
                    selected = item == selectedItem.value,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                        .width(200.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = md_theme_dark_primary,
                        unselectedContainerColor = md_theme_light_primaryContainer
                    )
                )
            }
        }
    }) {
        content()
    }
}


@SuppressLint("MutableCollectionMutableState")
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
        var newHunterVisibility by remember {
            mutableStateOf(false)
        }
        val newMonsterVisibility by remember {
            mutableStateOf(false)
        }
        var createdHunter: HunterData? by remember {
            mutableStateOf(null)
        }

//        var selectedCampaignIndex: Int by remember {
//            mutableIntStateOf(0)
//        }

        val monsterList = remember {
            mutableStateListOf<MonsterData>()

        }

        monsterList.add(MonsterData(Monster.GREAT_JAGRAS, 1, 2, 0))
        monsterList.add(MonsterData(Monster.PUKEI_PUKEI, 1, 1, 0))
        monsterList.add(MonsterData(Monster.ANJANATH, 1, 1, 0))

        val campaignList = mutableListOf(
            CampaignModel("Campaña 1", 2, 4, list = monsterList).id(0),
            CampaignModel("Campaña 2", 0, 0).id(1),
            CampaignModel("Campaña 3", 0, 0).id(2)
        )
        val hunterDataList = remember {
            mutableStateListOf<HunterData>()
        }

        hunterDataList.add(
            HunterData(
                "Ganexy", HunterWeapon.DUAL_BLADES, mutableListOf(
                    PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
                    PartModel(PartItem.CARBALITE),
                    PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
                    PartModel(PartItem.GREAT_JAGRAS_CLAW),
                )
            ).campaignId(0)
        )
        hunterDataList.add(HunterData("Adriatus", HunterWeapon.HEAVY_BOWGUN).campaignId(0))
        hunterDataList.add(HunterData("Garatoth", HunterWeapon.SWITCH_AXE).campaignId(0))
        hunterDataList.add(HunterData("Ingravitto", HunterWeapon.CHARGE_BLADE))
        hunterDataList.add(HunterData("Guille", HunterWeapon.LONG_SWORD).campaignId(0))
        hunterDataList.add(HunterData("SpiderWolf", HunterWeapon.BOW))

        val campaignViewModel = CampaignViewModel()
        campaignViewModel.init(campaignList, hunterDataList)
        val selectedCampaignIndex: Int by campaignViewModel.selectedCampaignIndex.observeAsState(
            initial = 0
        )

        MHDrawer(scope = scope,
            items = menuItems,
            selectedItem = selectedItem,
            drawerState = drawerState,
            content = {
                when (selectedItem.value.index) {
                    0 -> {
                        //Campaign View
                        MHScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {},
                            onFloatingButtonContent = {
                                FABCampaign(
                                    visible = newMonsterVisibility,
                                    campaignModel = campaignList[selectedCampaignIndex],
                                    onMonsterCreated = {
                                        campaignViewModel.selectedCampaign.value?.addMonster(it)
//                                                    campaignList[selectedCampaignIndex].addMonster(it)
                                    }
                                )
                            },
                            content = {
                                CampaignView(
                                    campaignList,
                                    hunterDataList,
                                    campaignViewModel = campaignViewModel,
                                    selectedCampaign = selectedCampaignIndex
                                ) {
//                                            selectedCampaignIndex = it
                                }
                            })
                    }

                    1 -> {
                        //Hunters View
                        MHScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {},
                            onFloatingButtonContent = {
                                FABHunters(
                                    visible = newHunterVisibility,
                                    hunterDataList = hunterDataList,
                                    onHunterCreated = {
                                        createdHunter = it
                                        newHunterVisibility = false
                                    }
                                )
                            },
                            content = { HunterView(HuntersViewModel(hunterDataList)) })
                    }

                    2 -> {
                        //Quick Guide View
                        val context = LocalContext.current
                        MHScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {
                                Toast.makeText(context, "it.labe", Toast.LENGTH_SHORT).show()
                            },
                            onFloatingButtonContent = { MyFloatingPreview() },

                            content = { MHDropDownPreview() })
                    }

                    else -> {
                        MHScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {},
                            onFloatingButtonContent = { MyFloatingPreview() },

                            content = { MyInventoryPreview() })

                    }
                }
            })
    }
}
