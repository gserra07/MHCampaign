package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mhcampaign.examples.Inventory
import com.example.mhcampaign.examples.MyDropDown
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.model.Monster
import com.example.mhcampaign.model.MonsterData

@Composable
fun CampaignView(dataList: List<HunterData>) {
    val coffeeDrinks = listOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    val campaignList = listOf(
        CampaignModel("Campaña 1", 2, 4),
        CampaignModel("Campaña 2"),
        CampaignModel("Campaña 3")
    )
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    var selectedHunter by remember { mutableStateOf<HunterData?>(dataList[0]) }
    var inventoryVisibility by remember {
        mutableStateOf(false)
    }

    Column {

        //Campaign selector
        MyDropDown("Campaign", campaignList.map { it.name }, selectedIndex) { name, index ->
            Log.d("Dropdown", "$name  $index")
            selectedIndex = index
        }
        //Potions and days
        val potions by remember { mutableStateOf(campaignList[selectedIndex].potions) }
        val days by remember { mutableStateOf(campaignList[selectedIndex].days) }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Box {
                MySelector(
                    potions,
                    R.drawable.potion
                ) { Log.d("Campaign", "pociones $it") }
            }
            Box() {

                MySelector(days, R.drawable.calendar_white) { }
            }

        }

        //Campaing`s hunters

        var campaignHunters by remember {
            mutableStateOf(listOf<HunterData?>(dataList[0], dataList[1], dataList[2]))
        }
        var editDialogVisibility by remember {
            mutableStateOf(false)
        }
        var edittingHunter by remember {
            mutableStateOf(dataList[0])
        }
        var isEmptyHunter by remember {
            mutableStateOf(false)
        }
        var hunterPosition by remember {
            mutableStateOf(0)
        }
        campaignHunters =
            makeCampaignHunters(campaignHunters = campaignHunters, isNewHunter = false)
        LazyVerticalGrid(horizontalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(2),
            content = {
                itemsIndexed(campaignHunters) { index, hunterData ->
                    HunterViewHolder(
                        data = hunterData,
                        onEditListener = { data ->
                            editDialogVisibility = true
                            hunterPosition = index
                            selectedHunter = hunterData
                            if (data != null) {
                                edittingHunter = data
                                isEmptyHunter = false
                                hunterPosition = campaignHunters.indexOf(data)
                                campaignHunters =
                                    makeCampaignHunters(
                                        campaignHunters = campaignHunters,
                                        data = hunterData,
                                        indexIn = selectedIndex,
                                        isNewHunter = false
                                    )
                            } else {
                                isEmptyHunter = true
                            }
                        })
                }
            })
        HunterSelector(visibility = editDialogVisibility,
            dataList = dataList.filter { !campaignHunters.contains(it) || selectedHunter == it },
            selectedHunter = if (!isEmptyHunter) edittingHunter else null,
            context = LocalContext.current,
            onDismissListener = { editDialogVisibility = false },
            onConfirmListener = { hData, index ->
                Log.d(
                    "Hunter selector",
                    if (hData == null) "Removed hunter" else "${hData.hunterName} ${hData.hunterWeapon} indice $index"
                )
                editDialogVisibility = false
                campaignHunters =
                    makeCampaignHunters(
                        campaignHunters,
                        hData,
                        hunterPosition,
                        changeHunter = true,
                        isNewHunter = isEmptyHunter
                    )
            },
            onInventoryListener = {
                inventoryVisibility = true
                selectedHunter = it
            },
            onDeleteListener = { item, index ->
                campaignHunters = makeCampaignHunters(
                    campaignHunters = campaignHunters,
                    data = item,
                    indexIn = index,
                    delete = true,
                    isNewHunter = false
                )
                editDialogVisibility = false
            })
//        MyCustomDialog(visibility = editDialogVisibility,
//                data = if (!isEmptyHunter) edittingHunter else null,
//                onDismissListener = { editDialogVisibility = false },
//                onConfirmListener = { name, index ->
//                    Log.d("$name", "$index")
//                    editDialogVisibility = false
//                }
//        )

        val monster1 = MonsterData(Monster.GREATJAGRAS, 1, 0, 0)
        val monster2 = MonsterData(Monster.RATHALOS, 0, 2, 0)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            MonsterView(data = monster1)
            //MonsterView(data = monster2)
        }
        selectedHunter?.let {
            Inventory(hunterData = it, visibility = inventoryVisibility, onCloseListener = {
                inventoryVisibility = false
            })
        }
    }
}

fun makeCampaignHunters(
    campaignHunters: List<HunterData?>,
    data: HunterData? = null,
    indexIn: Int = -1,
    changeHunter: Boolean = false,
    delete: Boolean = false,
    isNewHunter: Boolean
): List<HunterData?> {
    var hunterlist = mutableListOf<HunterData?>()

    campaignHunters.forEachIndexed { index, hunter ->
        if (isNewHunter) {
            hunterlist = campaignHunters.toMutableList()
            hunterlist[indexIn] = data
        } else {
                hunterlist.add(hunter)
        }
    }
    if (delete)
        hunterlist.remove(data)
    if(changeHunter)
        hunterlist[indexIn]= data
    while (hunterlist.size < 4) {
        hunterlist.add(null)
    }

    return hunterlist
}

@Preview(showSystemUi = true)
@Composable
fun MyCampaignreview() {
    val dataList = listOf(
        HunterData("hunter 1", HunterWeapon.BOW),
        HunterData("hunter 2", HunterWeapon.DUALBLADES),
        HunterData("hunter 3", HunterWeapon.GREATSWORD),
        HunterData("hunter 4", HunterWeapon.BOW),
        HunterData("hunter 5", HunterWeapon.LANCE),
        HunterData("hunter 6", HunterWeapon.INSECTGLAIVE)
    )
    CampaignView(dataList)
}
