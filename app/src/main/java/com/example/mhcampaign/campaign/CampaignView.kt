package com.example.mhcampaign.campaign

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.HunterSelector
import com.example.mhcampaign.HunterViewHolder
import com.example.mhcampaign.Inventory
import com.example.mhcampaign.MonsterListView
import com.example.mhcampaign.counter.MySelector
import com.example.mhcampaign.R
import com.example.mhcampaign.counter.CounterViewModel
import com.example.mhcampaign.examples.MyDropDown
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.model.Monster
import com.example.mhcampaign.model.MonsterData
import com.example.mhcampaign.model.MonsterListViewModel
import com.example.mhcampaign.ui.theme.md_theme_light_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CampaignView(
    campaignList: List<CampaignModel>,
    hunterDataList: List<HunterData>,
    campaignViewModel: CampaignViewModel
) {
    val campaignHunters: MutableList<HunterData?> by campaignViewModel.campaignHunters.observeAsState(
        initial = hunterDataList.filter { it.campaignId == campaignList[0].id }
            .toMutableList()
    )
    val selectedCampaignIndex: Int by campaignViewModel.selectedCampaignIndex.observeAsState(
        initial = 0
    )
    val selectedCampaign: CampaignModel by campaignViewModel.selectedCampaign.observeAsState(
        initial = campaignList[selectedCampaignIndex]
    )
    val hunterDialogVisibility: Boolean by campaignViewModel.hunterDialogVisibility.observeAsState(
        initial = false
    )
    val inventoryVisibility: Boolean by campaignViewModel.inventoryDialogVisibility.observeAsState(
        initial = false
    )
    var selectedHunter by remember { mutableStateOf<HunterData?>(null) }
    var selectedHunterPosition by remember {
        mutableStateOf(0)
    }


    Column(
        modifier = Modifier
            .background(
                md_theme_light_primaryContainer
            )
            .fillMaxHeight()
    ) {

        //Campaign selector
        MyDropDown(
            "Campaign",
            campaignList.map { it.name },
            selectedCampaignIndex,
            paddingValues = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
        ) { name, index ->
            Log.d("Dropdown", "$name  $index")
            campaignViewModel.onCampaignIndexChange(index)
        }

        //Potions and days
//        var potions = selectedCampaign2.potions
//        var days = selectedCampaign2.days
//        Log.d("Campaign", "Pociones de bbdd ${campaignList[selectedCampaignIndex]}")
//        Log.d("Campaign", "Pociones de la view $potions")
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Box {
                MySelector(
                    counterViewModel = CounterViewModel(
                        amount = selectedCampaign.potions,
                        maxLimit = 3,
                        minLimit = 0
                    ),
                    R.drawable.potion_icon
                ) {
//                    Log.d("Campaign", "Pociones de bbdd ${campaignList[selectedCampaignIndex]}")
                    Log.d("Campaign", "Pociones de la view $it")
                    campaignViewModel.onPotionsChange(it)
                }
            }
            Box() {
                MySelector(
                    counterViewModel = CounterViewModel(
                        amount = selectedCampaign.days,
                        minLimit = 0
                    ),
                    R.drawable.days_icon
                ) {
                    campaignViewModel.onDaysChange(it)
                }
            }

        }

        //Campaign`s hunters

//        campaignHunters =
//            hunterDataList.filter { it.campaignId == campaignList[selectedCampaignIndex].id }
//                .toMutableList()
//        campaignHunters = makeCampaignHunters(campaignHunters = campaignHunters)


        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
            columns = GridCells.Fixed(2),
            content = {
                itemsIndexed(campaignHunters) { index, hunterData ->
                    HunterViewHolder(data = hunterData,
                        position = index,
                        onEditListener = { data, position ->
                            campaignViewModel.onHunterDialogVisibilityChange(true)
                            selectedHunterPosition = position
                            selectedHunter = data
                        })
                }
            })
        HunterSelector(visibility = hunterDialogVisibility,
            dataList = hunterDataList.filter { !campaignViewModel.campaignHunters.value?.contains(it)!! || selectedHunter == it },
            selectedHunter = selectedHunter,
            context = LocalContext.current,
            onDismissListener = { campaignViewModel.onHunterDialogVisibilityChange(false) },
            onConfirmListener = { hData, index ->
                Log.d(
                    "Hunter selector",
                    if (hData == null) "Removed hunter" else "${hData.hunterName} ${hData.hunterWeapon} indice $index"
                )
                campaignViewModel.onHunterDialogVisibilityChange(false)
                campaignViewModel.addCampaignHunter(selectedHunter, hData)
            },
            onInventoryListener = {
                campaignViewModel.onInventoryDialogVisibilityChange(true)
                selectedHunter = it
            },
            onDeleteListener = { item, index ->
                campaignViewModel.removeCampaignHunter(item)
                item?.campaignId(-1)
                campaignViewModel.onHunterDialogVisibilityChange(false)
            })

        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            color = md_theme_light_primary,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Campaign's monsters
        val monsterList by selectedCampaign.monsterList.observeAsState(initial = mutableListOf())
//        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth(),
//            content = {
//                itemsIndexed(monsterList) { index, monsterData ->
//                    MonsterView(
//                        data = monsterData,
//                        PaddingValues(vertical = 5.dp, horizontal = 20.dp)
//                    )
//
//                }
//            })
        Box {

            MonsterListView(
                monsterList = MonsterListViewModel(monsterList),
                paddingValues = PaddingValues(start = 20.dp),
                onChangeListener = {
                    selectedCampaign.updateMonsterList(it)
                })
        }
        selectedHunter?.let {
            Inventory(hunterData = it, visibility = inventoryVisibility, onCloseListener = {
                campaignViewModel.onInventoryDialogVisibilityChange( false)
            })
        }
    }
}

//fun makeCampaignHunters(
//    campaignHunters: MutableList<HunterData?>,
//): MutableList<HunterData?> {
//    campaignHunters.toMutableList()
//    while (campaignHunters.size < 4) {
//        campaignHunters.add(null)
//    }
//
//    return campaignHunters
//}

@Preview(showSystemUi = true)
@Composable
fun MyCampaignreview() {
    val monsterList = mutableListOf(
        MonsterData(Monster.GREAT_JAGRAS, 1, 2, 0),
        MonsterData(Monster.PUKEI_PUKEI, 1, 1, 0),
        MonsterData(Monster.ANJANATH, 1, 1, 0)
    )
    val campaignList = mutableListOf(
        CampaignModel("Campaña 1", 2, 3, list = monsterList).id(0),
        CampaignModel("Campaña 2", 0, 0).id(1),
        CampaignModel("Campaña 3").id(2)
    )
    val dataList = mutableListOf(
        HunterData("hunter 1", HunterWeapon.BOW).campaignId(0),
        HunterData("hunter 2", HunterWeapon.DUALBLADES).campaignId(0),
        HunterData("hunter 3", HunterWeapon.GREATSWORD).campaignId(0),
        HunterData("hunter 4", HunterWeapon.BOW),
        HunterData("hunter 5", HunterWeapon.LANCE),
        HunterData("hunter 6", HunterWeapon.INSECTGLAIVE)
    )
    CampaignView(campaignList, dataList, CampaignViewModel(campaignList, dataList))
}
