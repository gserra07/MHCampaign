package com.example.mhcampaign.examples

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.ui.HunterViewHolder
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import kotlinx.coroutines.launch

@Composable
fun SimpleRecycleView(data: List<HunterDataModel>) {
    val rvState = rememberLazyListState()
    val corutinesScope = rememberCoroutineScope()
    Column {

        LazyColumn(state = rvState, verticalArrangement = Arrangement.spacedBy(100.dp), modifier = Modifier.weight(1f)) {
            itemsIndexed(data) { index, hunterData ->
                HunterViewHolder(
                        data = hunterData,
                        onEditListener = { data ,index-> Log.d(data?.hunterName, "") })
                if (index < data.size - 1)
                    Divider(modifier = Modifier
                            .fillMaxWidth())
            }
        }
//        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
//            itemsIndexed(data) { index, hunterData ->
//                HunterViewHolder(
//                        data = hunterData,
//                        onEditListener = { data -> Log.d(data.hunterName, "") })
//                if (index < data.size - 1)
//                    Divider(modifier = Modifier
//                            .fillMaxWidth())
//            }
//        })

        rvState.firstVisibleItemIndex
        val showbutton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }
        if (showbutton) {

            Button(onClick = {
                corutinesScope.launch {
                    rvState.scrollToItem(0)
                }
            }, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(16.dp)) {
                Text(text = "Hola")
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun MyRecyclePreview() {
    var data = listOf<HunterDataModel>(HunterDataModel(0,"hunter 1", HunterWeapon.BOW),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
            HunterDataModel(0,"hunter 3", HunterWeapon.CHARGE_BLADE))
    SimpleRecycleView(data)
}