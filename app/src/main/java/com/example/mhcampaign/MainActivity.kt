package com.example.mhcampaign

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.mhcampaign.ui.campaign.CampaignView
import com.example.mhcampaign.ui.campaign.CampaignViewModel
import com.example.mhcampaign.ui.huntersView.HunterView
import com.example.mhcampaign.ui.huntersView.HuntersViewModel
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.MenuItem
import com.example.mhcampaign.model.MonsterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.model.enums.PartItem
import com.example.mhcampaign.model.enums.PartModel
import com.example.mhcampaign.ui.CampaignUIState
import com.example.mhcampaign.ui.FABCampaign
import com.example.mhcampaign.ui.FABHunters
import com.example.mhcampaign.ui.HuntersUIState
import com.example.mhcampaign.ui.MHDrawer
import com.example.mhcampaign.ui.MHDropDownPreview
import com.example.mhcampaign.ui.MHScaffold
import com.example.mhcampaign.ui.MyFloatingPreview
import com.example.mhcampaign.ui.MyInventoryPreview
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var amount1 = mutableStateOf(0)
    private val campaignViewModel: CampaignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MHCampaignTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    MySelector(amount1, R.drawable.potion)
                    var text by remember {
                        mutableStateOf("")
                    }
                    val hunterViewModel =
                        HuntersViewModel(baseContext)
                    val campaignViewModel =
                        CampaignViewModel(baseContext)
                    AddDrawer(
                        campaignViewModel,
                        hunterViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun AddDrawer(campaignViewModel: CampaignViewModel, huntersViewModel: HuntersViewModel) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uistateHunters by produceState<HuntersUIState>(
        initialValue = HuntersUIState.Loading,
        key1 = lifecycle,
        key2 = huntersViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            huntersViewModel.uiStateHunters.collect {
                value = it
            }
        }
    }
    val uistateCampaign by produceState<CampaignUIState>(
        initialValue = CampaignUIState.Loading,
        key1 = lifecycle,
        key2 = campaignViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            campaignViewModel.uiStateCampaign.collect {
                value = it
            }
        }
    }
    when (uistateCampaign) {
        is CampaignUIState.Error -> {}
        CampaignUIState.Loading -> {}
        is CampaignUIState.Success -> {
            when (uistateHunters){
                is HuntersUIState.Error -> {}
                HuntersUIState.Loading -> {}
                is HuntersUIState.SuccessHunters -> InitDrawer(
                    campaignViewModel,
                    (uistateCampaign as CampaignUIState.Success).campaigns,
                    huntersViewModel,
                    (uistateHunters as HuntersUIState.SuccessHunters).hunters
                )
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitDrawer(
    campaignViewModel: CampaignViewModel,
    campaigns: List<CampaignModel>,
    huntersViewModel: HuntersViewModel,
    hunters: List<HunterDataModel>
) {
    var context = LocalContext.current

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
    var createdHunter: HunterDataModel? by remember {
        mutableStateOf(null)
    }

//        var selectedCampaignIndex: Int by remember {
//            mutableIntStateOf(0)
//        }

    val monsterList = remember {
        mutableStateListOf(
            MonsterDataModel(Monster.GREAT_JAGRAS, 1, 2, 0),
            MonsterDataModel(Monster.PUKEI_PUKEI, 1, 1, 0),
            MonsterDataModel(Monster.ANJANATH, 1, 1, 0)
        )

    }

//    monsterList.add(MonsterData(Monster.GREAT_JAGRAS, 1, 2, 0))
//    monsterList.add(MonsterData(Monster.PUKEI_PUKEI, 1, 1, 0))
//    monsterList.add(MonsterData(Monster.ANJANATH, 1, 1, 0))

    val campaignList = campaigns.ifEmpty {
        mutableListOf(
            CampaignModel(id = 1, name = "Campaña 1", potions = 2, days = 4, list = monsterList),
            CampaignModel(id = 2, name = "Campaña 2"),
            CampaignModel(id = 3, name = "Campaña 3"),

            )
    }
    val hunterList = hunters.ifEmpty {
        mutableStateListOf<HunterDataModel>(
            HunterDataModel(
                0,
                "Ganexy", HunterWeapon.DUAL_BLADES, mutableListOf(
                    PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
                    PartModel(PartItem.CARBALITE),
                    PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
                    PartModel(PartItem.GREAT_JAGRAS_CLAW),
                )
            ).campaignId(1),
            HunterDataModel(0, "Adriatus", HunterWeapon.HEAVY_BOWGUN).campaignId(1),
            HunterDataModel(0, "Garatoth", HunterWeapon.SWITCH_AXE).campaignId(1),
            HunterDataModel(0, "Ingravitto", HunterWeapon.CHARGE_BLADE),
            HunterDataModel(0, "Guille", HunterWeapon.LONG_SWORD).campaignId(1),
            HunterDataModel(0, "SpiderWolf", HunterWeapon.BOW),
        )
    }.toMutableList()

//    hunterDataList.add(
//        HunterData(
//            "Ganexy", HunterWeapon.DUAL_BLADES, mutableListOf(
//                PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
//                PartModel(PartItem.CARBALITE),
//                PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
//                PartModel(PartItem.GREAT_JAGRAS_CLAW),
//            )
//        ).campaignId(0)
//    )
//    hunterDataList.add(HunterData("Adriatus", HunterWeapon.HEAVY_BOWGUN).campaignId(1))
//    hunterDataList.add(HunterData("Garatoth", HunterWeapon.SWITCH_AXE).campaignId(1))
//    hunterDataList.add(HunterData("Ingravitto", HunterWeapon.CHARGE_BLADE))
//    hunterDataList.add(HunterData("Guille", HunterWeapon.LONG_SWORD).campaignId(1))
//    hunterDataList.add(HunterData("SpiderWolf", HunterWeapon.BOW))

    campaignViewModel.init(
        campaignList.toMutableList(),
        hunterList
    )
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
                                    campaignViewModel.onAddMonster(it)

                                    //campaignViewModel.selectedCampaign.value?.addMonster(it)
//                                                    campaignList[selectedCampaignIndex].addMonster(it)
                                },
                                addCampaignClick = {
                                    ///////////////////////////////
                                    campaignViewModel.onAddCampaign(campaignList[0])
                                }
                            )
                        },
                        content = {
                            CampaignView(
                                campaignList.toMutableList(),
                                hunterList,
                                campaignViewModel = campaignViewModel,
                                selectedCampaign = selectedCampaignIndex,
                                onCampaignChange = {},
                                onMonsterChange = { campaignViewModel.onChangeMonster(it) },
                                onHunterChange = { huntersViewModel.onUpdateHunter(it) }
                            )
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
                                hunterDataList = hunterList,
                                onHunterCreated = {
                                    //createdHunter = it
                                    huntersViewModel.onAddHunter(it)
                                    newHunterVisibility = false
                                }
                            )
                        },
                        content = { HunterView(huntersViewModel, hunterList) })
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


@Composable
fun SuperText() {
    Text(
        text = "Penesaurio",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(top = 15.dp)
            .background(color = Color.Cyan)
    )
}


@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(color = Color.Cyan)
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                SuperText()
                SuperText()

            }
        }
    }
}
