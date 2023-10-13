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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.ui.EditHunterDialog
import com.example.mhcampaign.ui.HunterViewHolder
import com.example.mhcampaign.ui.inventory.Inventory
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.ui.inventory.InventoryViewModel

@Composable
fun HunterView(huntersViewModel: HuntersViewModel, hunterDataList: MutableList<HunterDataModel>) {

    val selectedHunter: HunterDataModel? by huntersViewModel.selectedHunter.observeAsState(
        initial = null
    )
    val hunterDialogVisibility: Boolean by huntersViewModel.hunterDialogVisibility.observeAsState(
        initial = false
    )
    val inventoryViewModel by remember {
        mutableStateOf(InventoryViewModel(selectedHunter, false))
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (hunterList) = createRefs()
        Column(modifier = Modifier.constrainAs(hunterList) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                itemsIndexed(hunterDataList) { _, item ->
                    HunterViewHolder(
                        data = item
                    ) { data, _ ->
                        Log.d(data?.hunterName, "")
                        huntersViewModel.onSelectedHunterChange(data)
                        huntersViewModel.onHunterDialogVisibilityChange(true)
                        inventoryViewModel.onParentVisibilityChange(false)
                    }
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
    EditHunterDialog(
        visibility = hunterDialogVisibility,
        label = if (selectedHunter == null) "New Hunter" else "Edit Hunter",
        data = selectedHunter,
        onDismissListener = {
            huntersViewModel.onHunterDialogVisibilityChange(false)
        },
        onConfirmListener = { item, _ ->
            huntersViewModel.onHunterDialogVisibilityChange(false)
            if (selectedHunter == null && item != null) {
                huntersViewModel.onAddHunter(item)
            } else if (selectedHunter != null)
                item?.let { huntersViewModel.onUpdateHunter(it) }
        },
        onInventoryListener = {
            inventoryViewModel.onParentVisibilityChange(true)
        }
    )

    selectedHunter?.let {
        inventoryViewModel.setHunter(it)
        Inventory(inventoryViewModel = inventoryViewModel, onCloseListener = {
            inventoryViewModel.onParentVisibilityChange(false)
        },
            onInventoryChanged = {huntersViewModel.onUpdateHunter(selectedHunter!!)})
    }
}

@Preview(showSystemUi = true)
@Composable
fun HunterViewPreview() {
    val context = LocalContext.current
    val hunterViewModel =
        HuntersViewModel(context)
    val data = mutableListOf(
        HunterDataModel(0, "hunter 1", HunterWeapon.HAMMER),
        HunterDataModel(0, "hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0, "hunter 3", HunterWeapon.GREAT_SWORD),
        HunterDataModel(0, "hunter 4", HunterWeapon.BOW),
        HunterDataModel(0, "hunter 5", HunterWeapon.LANCE),
        HunterDataModel(0, "hunter 6", HunterWeapon.INSECT_GLAIVE)
    )
    HunterView(hunterViewModel, data)
}