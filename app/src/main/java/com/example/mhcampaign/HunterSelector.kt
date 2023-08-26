package com.example.mhcampaign

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mhcampaign.examples.MyDropDown
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@Composable
fun HunterSelector(
    visibility: Boolean,
    dataList: List<HunterData>,
    selectedHunter: HunterData? = null,
    onDismissListener: () -> Unit,
    onConfirmListener: (hunterData: HunterData?, index: Int) -> Unit,
    onDeleteListener: (hunterData: HunterData?, index: Int) -> Unit,
    onInventoryListener: (HunterData) -> Unit,
    context: Context = LocalContext.current
) {
    MHCampaignTheme(darkTheme = false) {

        if (visibility) {
            val hunterName by remember {
                mutableStateOf(selectedHunter?.hunterName ?: "")
            }
            var selectedIndex by remember {
                mutableStateOf(
                    if (selectedHunter != null)
                        dataList.map { it.hunterName }.indexOf(selectedHunter.hunterName)
                    else -1
                )
            }
            Dialog(
                onDismissRequest = { },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = md_theme_light_primaryContainer,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Select Hunter", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(20.dp))
                    MyDropDown(
                        label = "Hunter Name",
                        itemList = dataList.map { it.hunterName },
                        selectedIndex = selectedIndex,
                        paddingValues = PaddingValues(horizontal = 32.dp)
                    ) { name, index ->
                        Log.d("Dropdown", "$name  $index")
                        selectedIndex = index
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        TextButton(onClick = {
                            if (selectedHunter != null) {
                                onInventoryListener(selectedHunter)
                            }
                        }
                        ) {
                            Text(text = "Go Inventory")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Button(onClick = { onDeleteListener(selectedHunter, selectedIndex) }) {
                            Text(text = "Remove Hunter")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { onDismissListener() },
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            onConfirmListener(
                                if (selectedIndex >= 0) dataList[selectedIndex] else null,
                                selectedIndex
                            )
                            selectedHunter?.hunterName = hunterName
                            selectedHunter?.hunterWeapon = HunterWeapon.values()[selectedIndex]
                        }, modifier = Modifier.width(100.dp)) {
                            Text(text = context.getString(R.string.save_string))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyHunterSelectorPreview() {
    val data = listOf<HunterData>(
        HunterData("hunter 1", HunterWeapon.BOW),
        HunterData("hunter 2", HunterWeapon.DUALBLADES),
        HunterData("hunter 3", HunterWeapon.GREATSWORD),
        HunterData("hunter 3", HunterWeapon.GREATSWORD),
        HunterData("hunter 3", HunterWeapon.GREATSWORD),
        HunterData("hunter 4", HunterWeapon.INSECTGLAIVE),
    )
    var visible by remember {
        mutableStateOf(false)
    }
    TextButton(onClick = { visible = !visible }) {
        Text(text = "boton para ver")
    }
    var onConfrim: (String) -> Unit = { body: String ->
        visible = false
        Log.d("Preview", body)
    }
    HunterSelector(visibility = visible,
        dataList = data,
//            selectedHunter = data[1],
        context = LocalContext.current,
        onDismissListener = { },
        onConfirmListener = { hData, index -> },
        onInventoryListener = {},
        onDeleteListener = { data, index -> })
}