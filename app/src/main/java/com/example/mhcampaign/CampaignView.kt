package com.example.mhcampaign

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.examples.MyDropDown
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.model.Monster
import com.example.mhcampaign.model.MonsterData

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CampaignView(campaignList: List<CampaignModel>, hunterDataList: List<HunterData>) {

    var selectedCampaignIndex by remember {
        mutableStateOf(0)
    }
    var campaignHunters by remember {
        mutableStateOf(mutableListOf<HunterData?>())
    }
    var selectedHunter by remember { mutableStateOf<HunterData?>(null) }
    var selectedHunterPosition by remember {
        mutableStateOf(0)
    }
    var inventoryVisibility by remember {
        mutableStateOf(false)
    }
    var editDialogVisibility by remember {
        mutableStateOf(false)
    }


    Column {

        //Campaign selector
        MyDropDown("Campaign", campaignList.map { it.name }, selectedCampaignIndex) { name, index ->
            Log.d("Dropdown", "$name  $index")
            selectedCampaignIndex = index
        }
        //Potions and days
        var potions = campaignList[selectedCampaignIndex].potions
        var days = campaignList[selectedCampaignIndex].days
        Log.d("Campaign", "Pociones de bbdd ${campaignList[selectedCampaignIndex]}")
        Log.d("Campaign", "Pociones de la view $potions")
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Box {
                MySelector(
                    potions, R.drawable.potion_icon, maxLimit = 3, minLimit = 0
                ) {
                    Log.d("Campaign", "Pociones de bbdd ${campaignList[selectedCampaignIndex]}")
                    Log.d("Campaign", "Pociones de la view $it")
                    campaignList[selectedCampaignIndex].potions = it
                }
            }
            Box() {
                MySelector(days, R.drawable.calendar_white, minLimit = 0) {
                    campaignList[selectedCampaignIndex].days = it
                }
            }

        }

        //Campaign`s hunters

        campaignHunters =
            hunterDataList.filter { it.campaignId == campaignList[selectedCampaignIndex].id }
                .toMutableList()
        campaignHunters = makeCampaignHunters(campaignHunters = campaignHunters)


        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 20.dp),
            columns = GridCells.Fixed(2),
            content = {
                itemsIndexed(campaignHunters) { index, hunterData ->
                    HunterViewHolder(data = hunterData,
                        position = index,
                        onEditListener = { data, position ->
                            editDialogVisibility = true
                            selectedHunterPosition = position
                            selectedHunter = data
                        })
                }
            })
        HunterSelector(visibility = editDialogVisibility,
            dataList = hunterDataList.filter { !campaignHunters.contains(it) || selectedHunter == it },
            selectedHunter = selectedHunter,
            context = LocalContext.current,
            onDismissListener = { editDialogVisibility = false },
            onConfirmListener = { hData, index ->
                Log.d(
                    "Hunter selector",
                    if (hData == null) "Removed hunter" else "${hData.hunterName} ${hData.hunterWeapon} indice $index"
                )
                editDialogVisibility = false
                selectedHunter?.campaignId(-1)
                hData?.campaignId(selectedCampaignIndex)
            },
            onInventoryListener = {
                inventoryVisibility = true
                selectedHunter = it
            },
            onDeleteListener = { item, index ->
                item?.campaignId(-1)
                editDialogVisibility = false
            })

        Spacer(modifier = Modifier.height(10.dp))

        //Campaign's monsters
        val monsterList = campaignList[selectedCampaignIndex].monsterList
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
            content = {
                itemsIndexed(monsterList) { index, monsterData ->
                    MonsterView(
                        data = monsterData,
                        PaddingValues(vertical = 5.dp, horizontal = 20.dp)
                    )

                }
            })
        selectedHunter?.let {
            Inventory(hunterData = it, visibility = inventoryVisibility, onCloseListener = {
                inventoryVisibility = false
            })
        }
    }
}

fun makeCampaignHunters(
    campaignHunters: MutableList<HunterData?>,
): MutableList<HunterData?> {
    campaignHunters.toMutableList()
    while (campaignHunters.size < 4) {
        campaignHunters.add(null)
    }

    return campaignHunters
}

@Preview(showSystemUi = true)
@Composable
fun MyCampaignreview() {
    val monsterList = mutableListOf(
        MonsterData(Monster.GREAT_JAGRAS, 1, 2, 0),
        MonsterData(Monster.PUKEI_PUKEI, 1, 1, 0),
        MonsterData(Monster.ANJANATH, 1, 1, 0)
    )
    val campaignList = listOf(
        CampaignModel("Campaña 1", 2, 3, monsterList = monsterList).id(0),
        CampaignModel("Campaña 2", 0, 0).id(1),
        CampaignModel("Campaña 3").id(2)
    )
    val dataList = listOf(
        HunterData("hunter 1", HunterWeapon.BOW).campaignId(0),
        HunterData("hunter 2", HunterWeapon.DUALBLADES).campaignId(0),
        HunterData("hunter 3", HunterWeapon.GREATSWORD).campaignId(0),
        HunterData("hunter 4", HunterWeapon.BOW),
        HunterData("hunter 5", HunterWeapon.LANCE),
        HunterData("hunter 6", HunterWeapon.INSECTGLAIVE)
    )
    CampaignView(campaignList, dataList)
}
