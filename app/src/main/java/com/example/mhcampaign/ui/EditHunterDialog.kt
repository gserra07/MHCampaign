package com.example.mhcampaign.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import com.example.mhcampaign.R
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.ui.theme.GetTextFieldColors
import com.example.mhcampaign.ui.theme.md_theme_light_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer
import com.example.mhcampaign.ui.theme.mhFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditHunterDialog(
    visibility: Boolean,
    data: HunterDataModel? = null,
    label: String,
    onDismissListener: () -> Unit,
    onConfirmListener: (HunterDataModel?, Int) -> Unit,
    onInventoryListener: (HunterDataModel) -> Unit,
    context: Context = LocalContext.current
) {
    if (visibility) {
        var hunterName by remember {
            mutableStateOf(data?.hunterName ?: "")
        }
        var selectedIndex by remember {
            mutableStateOf(
                if (data != null) HunterWeapon.values().indexOf(data.hunterWeapon) else -1
            )
        }
        val dataList = mutableListOf<MHDropdownItemModel>()
        HunterWeapon.values().forEachIndexed { index, it ->
            dataList.add(
                MHDropdownItemModel(
                    itemName = it.weaponName,
                    itemIcon = it.icon,
                    category = if (it.weaponName.contains("bow", true)) "Range" else "Melee",
                    index = index
                )
            )
        }

        Dialog(
            onDismissRequest = { onDismissListener() },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        BorderStroke(1.dp, md_theme_light_primary),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = label, fontSize = 25.sp, fontFamily = mhFont)
                Spacer(modifier = Modifier.height(20.dp))
                MHLargeDropDown(
                    label = "Hunter Weapon",
                    itemModelList = dataList,
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, _ -> selectedIndex = index },
                    groupEnable = false,
                    searchEnable = false,
                    heightPercentage = 0.8f
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    label = { Text(text = "Hunter name") },
                    value = hunterName,
                    onValueChange = { hunterName = it },
                    singleLine = true,
                    colors = GetTextFieldColors()
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (data != null) {

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        TextButton(
                            onClick = {
                                onInventoryListener(data)
                            },
                            modifier = Modifier.border(
                                BorderStroke(1.dp, md_theme_light_primary),
                                shape = RoundedCornerShape(50.dp)
                            )
                        ) {
                            Text(
                                text = "Go Inventory",
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { onDismissListener() }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        if (hunterName != "") {
                            data?.hunterName = hunterName
                            data?.hunterWeapon = HunterWeapon.values()[selectedIndex]
                            if (data != null) {
                                onConfirmListener(data, selectedIndex)
                            } else
                                onConfirmListener(
                                    HunterDataModel(
                                        hunterName = hunterName,
                                        hunterWeapon = HunterWeapon.values()[selectedIndex]
                                    ), selectedIndex
                                )
                        } else {
                            Toast.makeText(context, "Empty hunter name", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text(text = context.getString(R.string.save_string))
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditHunterDialogPreview() {

    var hunterDataModel = HunterDataModel(
        id = -1,
        hunterName = "Jose",
        hunterWeapon = HunterWeapon.DUAL_BLADES,
        inventory = mutableListOf(),
        campaignId = -1
    )
    EditHunterDialog(
        visibility = true,
        label = "Edit Hunter",
        onDismissListener = { },
        onConfirmListener = { hunterDataModel, i -> },
        onInventoryListener = {}
    )
}