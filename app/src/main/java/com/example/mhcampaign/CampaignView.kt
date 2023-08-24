package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.examples.MyCustomDialog
import com.example.mhcampaign.examples.MyDropDown
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.HunterWeapon
import com.example.mhcampaign.model.Monster
import com.example.mhcampaign.model.MonsterData

@Preview(showSystemUi = true)
@Composable
fun CampaignView() {
    val coffeeDrinks = listOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    val campaignList = listOf(CampaignModel("Campaña 1", 2, 4),
            CampaignModel("Campaña 2"),
            CampaignModel("Campaña 3"))
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Column {

        MyDropDown("Campaign", campaignList.map { it.name }, selectedIndex) { name, index ->
            Log.d("Dropdown", "$name  $index")
            selectedIndex = index
        }

        val potions by remember { mutableStateOf(campaignList[selectedIndex].potions) }
        val days by remember { mutableStateOf(campaignList[selectedIndex].days) }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Box {
                MySelector(
                        potions,
                        R.drawable.potion
                ) { Log.d("Campaign", "pociones $it") }
            }
            Box() {

                MySelector(days, R.drawable.calendar_white) { }
            }

        }
        val data = listOf<HunterData>(HunterData("hunter 1", HunterWeapon.BOW),
                HunterData("hunter 2", HunterWeapon.DUALBLADES),
                HunterData("hunter 3", HunterWeapon.GREATSWORD),
                HunterData("hunter 4", HunterWeapon.INSECTGLAIVE))
        var editDialogVisibility by remember {
            mutableStateOf(false)
        }
        var edittingHunter by remember {
            mutableStateOf(data[0])
        }
        LazyVerticalGrid(horizontalArrangement = Arrangement.Center,
                columns = GridCells.Fixed(2),
                content = {
                    itemsIndexed(data) { index, hunterData ->
                        HunterViewHolder(
                                data = hunterData,
                                onEditListener = { data ->
                                    editDialogVisibility = true
                                    edittingHunter = data
                                })
                    }
                })

        MyCustomDialog(visibility = editDialogVisibility,
                data = edittingHunter,
                onDismissListener = { editDialogVisibility = false },
                onConfirmListener = { name, index ->
                    Log.d("$name", "$index")
                    editDialogVisibility = false
                }
        )

        val monster1 = MonsterData(Monster.GREATJAGRAS, 1, 0, 0)
        val monster2 = MonsterData(Monster.RATHALOS, 0, 2, 0)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            MonsterView(data = monster1)
            //MonsterView(data = monster2)
        }
    }
}