package com.example.mhcampaign.examples

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mhcampaign.MHDropDown
import com.example.mhcampaign.MHDropdownItemModel
import com.example.mhcampaign.PartView
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.model.PartItem
import com.example.mhcampaign.model.PartModel
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@Composable
fun Inventory(
    hunterData: HunterData,
    visibility: Boolean,
    context: Context = LocalContext.current,
    onCloseListener: (HunterData) -> Unit
) {
    var childVisibility by remember {
        mutableStateOf(false)
    }

    if (visibility) {
        Dialog(
            onDismissRequest = { onCloseListener(hunterData) },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
////                        .padding(10.dp)
                        .weight(1f),
                    content = {
                        itemsIndexed(hunterData.inventory) { index, item ->
                            Box(
                                modifier = Modifier
//                                    .padding(horizontal = 20.dp)
                                    .fillMaxWidth()
                            ) {

                                PartView(item, PaddingValues(horizontal = 5.dp, vertical = 10.dp)) {
                                    Log.d("PartView", "${it.name}  ${it.count}")
                                }
                            }
                        }

                    })
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            childVisibility = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(imageVector = Icons.Filled.Add, contentDescription = "")
                        Text(text = "New item", fontSize = 14.sp)
                    }
                }
                Button(
                    onClick = { onCloseListener(hunterData) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Cerrar", textAlign = TextAlign.Center)
                }
                NewItemDialog(
                    visibility = childVisibility,
                    hunterData = hunterData,
                    onSaveListener = { item, quantity ->
                        hunterData.inventory.add(
                            PartModel(item, quantity)
                        )
                        childVisibility = false

                    },
                    onCloseListener = {
                        childVisibility = false
                    })
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemDialog(
    visibility: Boolean,
    hunterData: HunterData,
    onSaveListener: (partItem: PartItem, quantity: Int) -> Unit,
    onCloseListener: () -> Unit
) {
    var availableItems =
        PartItem.values().filter { !hunterData.inventory.map { it.name }.contains(it) }
//    var availableItems = PartItem.values().map { it.partName }
    var selectedIndex by remember {
        mutableStateOf(
            -1
        )
    }
    if (visibility) {
        var quantity by remember {
            mutableStateOf("1")
        }
        Dialog(
            onDismissRequest = {
                onCloseListener()
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                var dropdownItemModelList = mutableListOf<MHDropdownItemModel>()
                availableItems.forEachIndexed { index, it ->
                    dropdownItemModelList.add(
                        MHDropdownItemModel(
                            itemName = it.partName,
                            itemIcon = it.type.icon,
                            index = index,
                            category = it.type.name
                        )
                    )
                }
                MHDropDown(
                    label = "New Item",
                    itemModelList = dropdownItemModelList,
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, item -> selectedIndex = index },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    grouped = true
                )
//                MyDropDown(
//                    "New Item",
//                    availableItems.map { it.partName },
//                    selectedIndex,
//                    paddingValues = PaddingValues(20.dp)
//                ) { name, index ->
//                    Log.d("Dropdown", "$name  $index")
//                    selectedIndex = index
//                }
                OutlinedTextField(
                    label = { Text(text = "Quantity") },
                    value = quantity,
                    onValueChange = { quantity = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Button(onClick = {
                        onCloseListener()
                        selectedIndex = -1
                    }, modifier = Modifier.weight(1f)) {
//                        Text(text = context.getString(R.string.cancel_string))
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = {
                        if (selectedIndex != -1)
                            onSaveListener(
                                availableItems[selectedIndex],
                                quantity.toIntOrNull() ?: 0
                            )
                        selectedIndex = -1
                    }, modifier = Modifier.weight(1f)) {
//                        Text(text =context.getString(R.string.add_item_string)
                        Text(text = "Add Item")
                    }
                }
            }
        }
    }
}

//@Preview(showSystemUi = true, device ="spec:width=280dp,height=891dp" )
@Preview(showSystemUi = true)
@Composable
fun MyInventoryPreview() {
    var context = LocalContext.current
    var visible by remember {
        mutableStateOf(false)
    }
    Box() {
        Button(onClick = {
            visible = true
        }) {
            Text(text = "Abrir inventario")
        }

    }
    var data = HunterData(
        "Hunter 1",
        HunterWeapon.LANCE,
        mutableListOf(
            PartModel(PartItem.SMALL_BONE, 0), PartModel(PartItem.HARDBONE, 3),
            PartModel(PartItem.DRAGONITE, 1), PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
            PartModel(PartItem.GREAT_JAGRAS_CLAW),
        )
    )
    Inventory(
        hunterData = data,
        visibility = visible,
        context = context,
        onCloseListener = { visible = false })
}