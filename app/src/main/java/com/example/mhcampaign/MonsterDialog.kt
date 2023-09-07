package com.example.mhcampaign

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mhcampaign.model.MonsterData
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@Composable
fun MonsterDialog(
    visibility: Boolean,
    dataList: MutableList<Monster>,
    onDismissListener: () -> Unit,
    onConfirmListener: ( index: Int,hunterData: Monster) -> Unit,
    context: Context = LocalContext.current
) {
    MHCampaignTheme(darkTheme = false) {

        if (visibility) {
            var selectedIndex by remember {
                mutableStateOf(-1)
            }
            val availableMonsters = Monster.values().filter {
                !dataList.map { m ->
                    m
                }.contains(it)
            }.sortedBy { it.index }
            Dialog(
                onDismissRequest = { onDismissListener() },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
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
                    val dropdownMonsterList = mutableListOf<MHDropdownItemModel>()
                    Text(text = "Select Monster", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(20.dp))
                    availableMonsters.forEachIndexed { index, it ->
                        dropdownMonsterList.add(
                            MHDropdownItemModel(
                                itemName = it.monsterName,
                                itemIcon = it.icon,
                                index = index,
                            )
                        )
                    }
                    MHLargeDropDown(
                        label = "New Monster",
                        itemModelList = dropdownMonsterList,
                        selectedIndex = selectedIndex,
                        onItemSelected = { index, _ -> selectedIndex = index },
                        modifier = Modifier.padding(horizontal = 20.dp),
                        groupEnable = false,
                        heightPercentage = 0.6f
                    )

                    Spacer(modifier = Modifier.height(20.dp))
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
                            if (selectedIndex >= 0) {
                                onConfirmListener(
                                    selectedIndex,
                                    availableMonsters[selectedIndex]
                                )
                            }
                            else{

                            }
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
fun MyMonsterSelectorPreview() {
    val monsterList = mutableListOf(
        Monster.GREAT_JAGRAS,
        Monster.PUKEI_PUKEI,
        Monster.ANJANATH,
    )
    var visible by remember {
        mutableStateOf(true)
    }
    TextButton(onClick = { visible = !visible }) {
        Text(text = "boton para ver")
    }
    var onConfrim: (String) -> Unit = { body: String ->
        visible = false
        Log.d("Preview", body)
    }
    MonsterDialog(visibility = visible,
        dataList = monsterList,
        onDismissListener = { visible = false},
        onConfirmListener = { hData, index ->
            visible = false
        },)
}