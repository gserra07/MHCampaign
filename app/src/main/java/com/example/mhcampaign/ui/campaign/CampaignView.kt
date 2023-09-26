package com.example.mhcampaign.ui.campaign

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
import com.example.mhcampaign.ui.HunterSelector
import com.example.mhcampaign.ui.HunterViewHolder
import com.example.mhcampaign.ui.inventory.Inventory
import com.example.mhcampaign.ui.MHSimpleDropDown
import com.example.mhcampaign.ui.monsters.MonsterListView
import com.example.mhcampaign.ui.counter.MySelector
import com.example.mhcampaign.R
import com.example.mhcampaign.ui.counter.CounterViewModel
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.MonsterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.ui.inventory.InventoryViewModel
import com.example.mhcampaign.ui.monsters.MonsterListViewModel
import com.example.mhcampaign.ui.theme.md_theme_light_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CampaignView(
    campaignList: MutableList<CampaignModel>,
    hunterDataList: MutableList<HunterDataModel>,
    campaignViewModel: CampaignViewModel,
    selectedCampaign: Int = 0,
    onCampaignChange: (index: Int) -> Unit,
    onMonsterChange: (MutableList<MonsterDataModel>) -> Unit,
    onHunterChange: (HunterDataModel) -> Unit
) {
    val selectedCampaignIndex: Int by campaignViewModel.selectedCampaignIndex.observeAsState(
        initial = selectedCampaign
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
    var selectedHunter by remember { mutableStateOf<HunterDataModel?>(null) }
    var selectedHunterPosition by remember {
        mutableStateOf(0)
    }
    val logName = "Campaign"


    Column(
        modifier = Modifier
            .background(
                md_theme_light_primaryContainer
            )
            .fillMaxHeight()
    ) {

        //Campaign selector
        MHSimpleDropDown(
            "Campaign",
            campaignList.map { it.name },
            selectedCampaignIndex,
            paddingValues = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
        ) { name, index ->
            Log.d("Dropdown", "$name  $index")
            onCampaignChange(index)
            campaignViewModel.onCampaignIndexChange(index, campaignList[index])
        }

        //Potions and days
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

        var campaignHunters = makeCampaignHunters(
            hunterDataList = hunterDataList,
            selectedCampaignId = selectedCampaign.id
        )

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
            dataList = hunterDataList.filter { !campaignHunters.contains(it) || selectedHunter == it },
            selectedHunter = selectedHunter,
            context = LocalContext.current,
            onDismissListener = { campaignViewModel.onHunterDialogVisibilityChange(false) },
            onConfirmListener = { hData, index ->
                Log.d(
                    "Hunter selector",
                    if (hData == null) "Removed hunter" else "${hData.hunterName} ${hData.hunterWeapon} indice $index"
                )
                campaignViewModel.onHunterDialogVisibilityChange(false)
                if (hData != null) {
                    onHunterChange(
                        hData.campaignId(
                            campaignViewModel.selectedCampaign.value?.id ?: -1
                        )
                    )
                }
            },
            onInventoryListener = {
                campaignViewModel.onInventoryDialogVisibilityChange(true)
                selectedHunter = it
            },
            onDeleteListener = { item, _ ->
                if (item != null) {
                    onHunterChange(item.campaignId(-1))
                }
                item?.campaignId(-1)
                campaignViewModel.onHunterDialogVisibilityChange(false)
            })

        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            color = md_theme_light_primary,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            thickness = 1.dp
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
                onChangeListener = { i, m ->
                    onMonsterChange(monsterList)
                    // Log.d(logName, "Monster: ${m.monster.monsterName} easy ${m.easyCount.value} medium ${m.mediumCount.value}")
                })
        }
        selectedHunter?.let {
            var inventoryViewModel = InventoryViewModel(it, inventoryVisibility)
            Inventory(inventoryViewModel = inventoryViewModel, onCloseListener = {
                campaignViewModel.onInventoryDialogVisibilityChange(false)
            })
        }
    }
}

fun makeCampaignHunters(
    hunterDataList: MutableList<HunterDataModel>, selectedCampaignId: Int
): MutableList<HunterDataModel?> {
    val list = mutableListOf<HunterDataModel?>()
    hunterDataList.filter { it.campaignId == selectedCampaignId }?.forEach {
        list.add(it)
    }

    while (list.size < 4) {
        list.add(null)
    }
    return list
}

@Preview(showSystemUi = true)
@Composable
fun MyCampaignPreview() {
    val monsterList = mutableListOf(
        MonsterDataModel(Monster.GREAT_JAGRAS, 1, 2, 0),
        MonsterDataModel(Monster.PUKEI_PUKEI, 1, 1, 0),
        MonsterDataModel(Monster.ANJANATH, 1, 1, 0)
    )
    val campaignList = mutableListOf(
        CampaignModel(id = 1, name = "Campaña 1", potions = 2, days = 4, list = monsterList),
        CampaignModel(id = 2, name = "Campaña 2"),
        CampaignModel(id = 3, name = "Campaña 3"),

        )
    val dataList = mutableListOf(
        HunterDataModel(0, "hunter 1", HunterWeapon.BOW).campaignId(0),
        HunterDataModel(0, "hunter 2", HunterWeapon.DUAL_BLADES).campaignId(0),
        HunterDataModel(0, "hunter 3", HunterWeapon.GREAT_SWORD).campaignId(0),
        HunterDataModel(0, "hunter 4", HunterWeapon.BOW),
        HunterDataModel(0, "hunter 5", HunterWeapon.LANCE),
        HunterDataModel(0, "hunter 6", HunterWeapon.INSECT_GLAIVE)
    )
//    var campaignViewModel = CampaignViewModel()
//    campaignViewModel.init(campaignList,dataList)
//    CampaignView(
//        campaignList,
//        dataList,
//        campaignViewModel,
//    ) {}
}
