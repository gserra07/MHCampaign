package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.mhcampaign.examples.Inventory
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon

@Composable
fun HunterView(dataList: List<HunterData>) {
    var selectedHunter by remember { mutableStateOf<HunterData?>(dataList[0]) }
    var visible by remember {
        mutableStateOf(false)
    }
    var inventoryVisibility by remember {
        mutableStateOf(false)
    }
    Column {
        HunterViewHolder(
            onEditListener = { data,index ->
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
    HunterDialog(
        visibility = visible,
        label = if (selectedHunter == null) "New Hunter" else "Edit Hunter",
        data = selectedHunter,
        onDismissListener = { visible = false },
        onConfirmListener = { item, index -> visible = false },
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