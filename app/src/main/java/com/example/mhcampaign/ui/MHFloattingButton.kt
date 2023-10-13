package com.example.mhcampaign.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mhcampaign.R
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.ui.theme.md_theme_dark_primary


data class MultiFabItem(
    val id: Int,
    @DrawableRes val iconRes: Int? = null,
    val label: String = ""
)

sealed class MultiFabState {
    object Collapsed : MultiFabState()
    object Expand : MultiFabState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expand
    }
}

@Composable
fun rememberMultiFabState() = remember { mutableStateOf<MultiFabState>(MultiFabState.Collapsed) }


@Immutable
interface FabIcon {
    @Stable
    val iconRes: Int

    @Stable
    val iconRotate: Float?
}

private class FabIconImpl(
    override val iconRes: Int,
    override val iconRotate: Float?
) : FabIcon

fun FabIcon(@DrawableRes iconRes: Int, iconRotate: Float? = null): FabIcon =
    FabIconImpl(iconRes, iconRotate)

@Immutable
interface FabOption {
    @Stable
    val iconTint: Color

    @Stable
    val backgroundTint: Color

    @Stable
    val showLabel: Boolean
}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
    override val showLabel: Boolean
) : FabOption

@SuppressLint("ComposableNaming")
@Composable
fun FabOption(
    backgroundTint: Color = MaterialTheme.colorScheme.primary,
    iconTint: Color = contentColorFor(backgroundColor = backgroundTint),
    showLabel: Boolean = false
): FabOption = FabOptionImpl(iconTint, backgroundTint, showLabel)


@Composable
fun MultiFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<MultiFabItem>,
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabIcon: FabIcon,
    fabOption: FabOption = FabOption(),
    onFabItemClicked: (fabItem: MultiFabItem) -> Unit,
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expand) {
            fabIcon.iconRotate ?: 0f
        } else {
            0f
        }, label = ""
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(items.size) { index ->
                    MiniFabItem(
                        item = items[index],
                        fabOption = fabOption,
                        onFabItemClicked = {
                            fabState.value = fabState.value.toggleValue()
                            onFabItemClicked(it)
                        }
                    )
                }

                item {}
            }
        }

        FloatingActionButton(
            onClick = {
                fabState.value = fabState.value.toggleValue()
                stateChanged(fabState.value)
            },
            modifier = Modifier
                .size(40.dp)
                .align(alignment = End),
            containerColor = md_theme_dark_primary,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(16.dp)
                    ), contentAlignment = Center
            ) {

                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    modifier = Modifier
                        .rotate(rotation),

                    tint = Color.Black
                )
            }

            /*Icon(
                painter = painterResource(id = fabIcon.iconRes),
                contentDescription = "FAB",
                modifier = Modifier.rotate(rotation),
                tint = fabOption.iconTint
            )*/
        }
    }
}

@Composable
fun MiniFabItem(
    item: MultiFabItem,
    fabOption: FabOption,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .width(140.dp)
            .background(
                color = md_theme_dark_primary,
                shape = RoundedCornerShape(50.dp)
            )
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(50.dp))
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (fabOption.showLabel) {
            Text(
                text = item.label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .clickable { onFabItemClicked(item) }
            )
        }
        if (item.iconRes != null)
            FloatingActionButton(
                onClick = {
                    onFabItemClicked(item)
                },
                modifier = Modifier.size(40.dp),
                contentColor = fabOption.iconTint
            ) {
                Icon(
                    painter = painterResource(id = item.iconRes),
                    contentDescription = "Float Icon",
                    tint = fabOption.iconTint
                )
            }
    }
}


@Composable
fun FABCampaign(
    visible: Boolean,
    campaignModel: CampaignModel,
    onMonsterCreated: (Monster) -> Unit,
    addCampaignClick: () -> Unit
) {
    var newMonsterVisibility by remember {
        mutableStateOf(visible)
    }

    MultiFloatingActionButton(
        items = listOf(
            MultiFabItem(
                id = 0, label = "Add Monster"
            ), MultiFabItem(
                id = 1, label = "Add Campaign"
            )
        ), fabIcon = FabIcon(
            iconRes = R.drawable.add_black, iconRotate = 45f
        ), onFabItemClicked = {
            when (it.id) {
                0 -> {
                    newMonsterVisibility = true
                }

                1 -> {
                    addCampaignClick()
                }
            }
        }, fabOption = FabOption(
            iconTint = Color.White, showLabel = true
        )
    )
    campaignModel.monsterList.value?.map { it.monster }?.let {
        MonsterDialog(
            visibility = newMonsterVisibility,
            dataList = it.toMutableStateList(),
            onDismissListener = { newMonsterVisibility = false },
            onConfirmListener = { i, m ->
                onMonsterCreated(m)
                newMonsterVisibility = false
            }
        )
    }
}

@Composable
fun FABHunters(
    visible: Boolean,
    hunterDataList: MutableList<HunterDataModel>,
    onHunterCreated: (HunterDataModel) -> Unit
) {
    var newHunterVisibility by remember {
        mutableStateOf(visible)
    }
    var createdHunter: HunterDataModel? by remember {
        mutableStateOf(null)
    }
    MultiFloatingActionButton(
        items = listOf(
            MultiFabItem(
                id = 0, label = "Add Hunter"
            )
        ), fabIcon = FabIcon(
            iconRes = R.drawable.add_black, iconRotate = 45f
        ), onFabItemClicked = {
            when (it.id) {
                0 -> {
                    newHunterVisibility = true
                }
            }
        }, fabOption = FabOption(
            iconTint = Color.White, showLabel = true
        )
    )
    EditHunterDialog(visibility = newHunterVisibility,
        label = "New Hunter",
        data = null,
        onDismissListener = { newHunterVisibility = false },
        onConfirmListener = { item, index ->
            if (item != null) {
                Log.d("drawer", item.hunterName)
                createdHunter = item
                onHunterCreated(item)
                //hunterDataList.add(item)
            }
            newHunterVisibility = false
        },
        onInventoryListener = { })
}


@Preview(showSystemUi = true)
@Composable
fun MyFloatingPreview() {
    val context = LocalContext.current
    MultiFloatingActionButton(
        items = listOf(
            MultiFabItem(
                id = 1,
//                iconRes = R.drawable.potion_icon,
                label = "Add Campaign"
            ),
            MultiFabItem(
                id = 2,
//                iconRes = R.drawable.potion_icon,
                label = "Add Monster"
            )
        ),
        fabIcon = FabIcon(iconRes = R.drawable.add_black, iconRotate = 45f),
        onFabItemClicked = {
            Toast.makeText(context, it.label, Toast.LENGTH_SHORT).show()
        },
        fabOption = FabOption(
            iconTint = Color.White,
            showLabel = true
        )
    )
}
