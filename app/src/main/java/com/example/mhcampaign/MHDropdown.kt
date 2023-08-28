package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mhcampaign.examples.MyDropDown
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MHDropDown(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    itemModelList: List<MHDropdownItemModel>,
    selectedIndex: Int = -1,
    grouped: Boolean,
    onItemSelected: (index: Int, item: MHDropdownItemModel) -> Unit,
    selectedItemToString: (MHDropdownItemModel) -> String = { it.itemName },
    drawItem: @Composable (MHDropdownItemModel, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        MHDropDownMenuItem(
            itemData = item,
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },

    ) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            label = { Text(label) },
            value = itemModelList.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: "",
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            onValueChange = { },
            readOnly = true,
        )

        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(enabled = enabled) { expanded = true },
            color = Color.Transparent,
        ) {}
    }

    if (expanded) {
        var filter by remember {
            mutableStateOf("")
        }
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
            ) {
                val listState = rememberLazyListState()
                if (selectedIndex > -1) {
                    LaunchedEffect("ScrollToSelected") {
                        listState.scrollToItem(index = selectedIndex)
                    }
                }
                Column(
                    modifier = Modifier
                        .background(
                            color = md_theme_light_primaryContainer,
                        )
                ) {
                    OutlinedTextField(
                        label = { Text(text = label) },
                        value = filter,
                        onValueChange = { filter = it },
                        singleLine = true,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = md_theme_light_primaryContainer), state = listState
                    ) {
                        if (notSetLabel != null) {
                            item {
                                MHDropDownMenuItem(
                                    itemData = itemModelList[0],
                                    selected = false,
                                    enabled = false,
                                    onClick = { },
                                )
                            }
                        }
                        var filteredList =
                            itemModelList.filter { it.itemName.contains(filter, true) }
                        var groupedList: Map<String, List<MHDropdownItemModel>> =
                            filteredList.sortedBy { it.itemName }.groupBy { it.category }
                        groupedList.forEach { (category, itemList) ->
                            stickyHeader {
                                Text(
                                    text = category,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 5.dp, vertical = 5.dp)
                                        .background(color = md_theme_light_primaryContainer),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            itemsIndexed(itemList) { index, item ->
                                val selectedItem = item.index == selectedIndex
                                drawItem(item, selectedItem, true) {
                                    onItemSelected(item.index, item)
                                    expanded = false
                                }

                                if (index < itemModelList.lastIndex) {
                                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MHDropDownMenuItem(
    itemData: MHDropdownItemModel,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        selected -> MaterialTheme.colorScheme.primary.copy(alpha = 1f)
        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(modifier = Modifier
            .clickable(enabled) { onClick() }
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = md_theme_light_primaryContainer)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                itemData.itemIcon?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = it,
                        contentDescription = "Item icon",
                        modifier = Modifier.size(25.dp)
                    )
                }
                Spacer(Modifier.width(15.dp))
                Text(
                    text = itemData.itemName,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

data class MHDropdownItemModel(
    var itemName: String,
    var itemIcon: Int? = null,
    var category: String = "",
    var index: Int
)

@Preview(showSystemUi = true)
@Composable
fun MHDropDownPreview() {
    MHCampaignTheme(darkTheme = false) {

        val coffeeDrinks = listOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
        Column {

            MyDropDown("Campaign", coffeeDrinks) { name, index ->
                Log.d(
                    "Dropdown",
                    "$name  $index"
                )
            }
            var selectedIndex by remember { mutableStateOf(-1) }
            var dataList = mutableListOf<MHDropdownItemModel>()
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
            MHDropDown(
                label = "Filter",
                itemModelList = dataList,
                selectedIndex = selectedIndex,
                onItemSelected = { index, _ -> selectedIndex = index },
                modifier = Modifier.padding(horizontal = 20.dp),
                grouped = true
            )
        }
    }
}