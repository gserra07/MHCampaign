package com.example.mhcampaign.ui.huntersView

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.ui.HunterDialog
import com.example.mhcampaign.ui.HunterViewHolder
import com.example.mhcampaign.ui.Inventory
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon

@Composable
fun HunterView(huntersViewModel: HuntersViewModel, hunterDataList: MutableList<HunterDataModel>) {

    val selectedHunter: HunterDataModel? by huntersViewModel.selectedHunter.observeAsState(
        initial = null
    )
    val hunterDialogVisibility: Boolean by huntersViewModel.hunterDialogVisibility.observeAsState(
        initial = false
    )
    val inventoryVisibility: Boolean by huntersViewModel.inventoryDialogVisibility.observeAsState(
        initial = false
    )

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (hunterList) = createRefs()
        Column(modifier = Modifier.constrainAs(hunterList) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
//            HunterViewHolder(
//                onEditListener = { data, index ->
//                    Log.d(data?.hunterName, "")
//                    huntersViewModel.onSelectedHunterChange(null)
//                    huntersViewModel.onHunterDialogVisibilityChange(true)
//                })
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp),
                modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                itemsIndexed(hunterDataList) { index, item ->
                    HunterViewHolder(
                        data = item,
                        onEditListener = { data, index ->
                            Log.d(data?.hunterName, "")
                            huntersViewModel.onSelectedHunterChange(data)
                            huntersViewModel.onHunterDialogVisibilityChange(true)
                            huntersViewModel.onInventoryDialogVisibilityChange(false)
                        })
//                    if (index != ((huntersViewModel.hunterList.value?.size ?: 0) - 1))
//                        Divider(
//                            color = md_theme_light_primary,
//                            modifier = Modifier
//                                .padding(vertical = 3.dp)
//                                .fillMaxWidth(),
//                            thickness = 1.dp
//                        )
                }
            }
        }
    }
    HunterDialog(
        visibility = hunterDialogVisibility,
        label = if (selectedHunter == null) "New Hunter" else "Edit Hunter",
        data = selectedHunter,
        onDismissListener = {
            huntersViewModel.onHunterDialogVisibilityChange(false)
        },
        onConfirmListener = { item, index ->
            huntersViewModel.onHunterDialogVisibilityChange(false)
            if (selectedHunter == null && item != null) {
                huntersViewModel.onAddHunter(item)
            }else if (selectedHunter !=null)
                item?.let { huntersViewModel.onUpdateHunter(it) }
        },
        onInventoryListener = { huntersViewModel.onInventoryDialogVisibilityChange(true) }
    )
    selectedHunter?.let {
        Inventory(hunterData = it, visibility = inventoryVisibility, onCloseListener = {
            huntersViewModel.onInventoryDialogVisibilityChange(false)
        })
    }
}

@Preview(showSystemUi = true)
@Composable
fun HunterViewPreview() {
    var context = LocalContext.current
    val hunterViewModel =
        HuntersViewModel(context)
    val data = mutableListOf(
        HunterDataModel(0,"hunter 1", HunterWeapon.HAMMER),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 3", HunterWeapon.GREAT_SWORD),
        HunterDataModel(0,"hunter 4", HunterWeapon.BOW),
        HunterDataModel(0,"hunter 5", HunterWeapon.LANCE),
        HunterDataModel(0,"hunter 6", HunterWeapon.INSECT_GLAIVE)
    )
    HunterView(hunterViewModel, data)
}