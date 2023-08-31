package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon

@Composable
fun HunterView(dataList: MutableList<HunterData>) {
    var hunterList by remember {
        mutableStateOf(dataList)
    }
    var selectedHunter by remember { mutableStateOf<HunterData?>(hunterList[0]) }
    var visible by remember {
        mutableStateOf(false)
    }
    var inventoryVisibility by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (hunterList, floatingButton) = createRefs()
        Column(modifier = Modifier.constrainAs(hunterList) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
            HunterViewHolder(
                onEditListener = { data, index ->
                    Log.d(data?.hunterName, "")
                    selectedHunter = null
                    visible = true
                })
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp),
                modifier = Modifier.weight(1f)
            ) {
                itemsIndexed(dataList) { index, item ->
                    HunterViewHolder(
                        data = item,
                        onEditListener = { data, index ->
                            Log.d(data?.hunterName, "")
                            selectedHunter = data
                            visible = true
                            inventoryVisibility = false
                        })
                    if (index != dataList.size - 1)
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                        )
                }
            }
        }
//        Box(modifier = Modifier.constrainAs(floatingButton) {
//            bottom.linkTo(parent.bottom, 20.dp)
//            end.linkTo(parent.end, 20.dp)
//        }) {
//            MultiFloatingActionButton(
//                items = listOf(
//                    MultiFabItem(
//                        id = 0,
//                        label = "Add Hunter"
//                    )
//                ),
//                fabIcon = FabIcon(
//                    iconRes = R.drawable.add_black,
//                    iconRotate = 45f
//                ),
//                onFabItemClicked = {
//                    when (it.id) {
//                        0 -> {
//                            selectedHunter = null
//                            visible = true
//                        }
//                    }
//                },
//                fabOption = FabOption(
//                    iconTint = Color.White,
//                    showLabel = true
//                )
//            )
//        }
    }
    HunterDialog(
        visibility = visible,
        label = if (selectedHunter == null) "New Hunter" else "Edit Hunter",
        data = selectedHunter,
        onDismissListener = { visible = false },
        onConfirmListener = { item, index ->
            visible = false
            if (selectedHunter == null && item != null) {
                hunterList.add(item)
            }
        },
        onInventoryListener = { inventoryVisibility = true }
    )
    selectedHunter?.let {
        Inventory(hunterData = it, visibility = inventoryVisibility, onCloseListener = {
            inventoryVisibility = false
        })
    }
}

@Preview(showSystemUi = true)
@Composable
fun HunterViewPreview() {
    var data = mutableListOf<HunterData>(
        HunterData("hunter 1", HunterWeapon.HAMMER),
        HunterData("hunter 2", HunterWeapon.DUALBLADES),
        HunterData("hunter 3", HunterWeapon.GREATSWORD),
        HunterData("hunter 4", HunterWeapon.BOW),
        HunterData("hunter 5", HunterWeapon.LANCE),
        HunterData("hunter 6", HunterWeapon.INSECTGLAIVE)
    )
    HunterView(dataList = data)
}