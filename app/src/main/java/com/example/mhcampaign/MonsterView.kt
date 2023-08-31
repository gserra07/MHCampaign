package com.example.mhcampaign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.model.Monster
import com.example.mhcampaign.model.MonsterData

@Composable
fun MonsterView(data: MonsterData, paddingValues: PaddingValues = PaddingValues(), onChangeListener: (MonsterData) -> Unit) {
    var easyCount by remember {
        mutableStateOf(data.easyCount)
    }
    var mediumCount by remember {
        mutableStateOf(data.mediumCount)
    }
    var hardCount by remember {
        mutableStateOf(data.hardCount)
    }
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
//            .fillMaxWidth()
    ) {
        val (monsterIcon, counters) = createRefs()

        Image(painter = painterResource(id = data.monster.icon),
            contentDescription = "Monster Icon",
            modifier = Modifier
                .size(120.dp)
                .constrainAs(monsterIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
        Box(modifier = Modifier
            .constrainAs(counters) {
                top.linkTo(monsterIcon.top)
                start.linkTo(monsterIcon.end, 30.dp)
                bottom.linkTo(monsterIcon.bottom)
            }) {
            Column {
                MySelector(
                    amountIn = easyCount,
                    icon = R.drawable.blue_star_24,
                    minLimit = 0,
                    iconSize = 40.dp
                ) {
                    easyCount = it
                    onChangeListener(data)
                }
                MySelector(
                    amountIn = mediumCount,
                    icon = R.drawable.two_stars,
                    minLimit = 0,
                    iconSize = 40.dp
                ) {
                    mediumCount = it
                    onChangeListener(data)
                }
                MySelector(
                    amountIn = hardCount,
                    icon = if (data.monster.isFourStars)
                        R.drawable.four_stars
                    else
                        R.drawable.three_stars,
                    minLimit = 0,
                    iconSize = 40.dp
                ) {
                    hardCount = it
                    onChangeListener(data)
                }
            }
        }
    }
}

@Composable
fun MonsterListView(
    monsterList: MutableList<MonsterData>,
    paddingValues: PaddingValues = PaddingValues(),
    onChangeListener: (MonsterData) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(monsterList) { i, monster ->
            MonsterView(data = monster){}
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyMonsterPreview() {
    val data = MonsterData(Monster.GREAT_JAGRAS, 1, 0, 0)
    val data2 = MonsterData(Monster.RATHALOS, 0, 2, 0)
    val monsterList = mutableListOf(data, data2)

    MonsterListView(
        monsterList = monsterList,
        paddingValues = PaddingValues(start = 20.dp),
        onChangeListener = {})
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        MonsterView(data = data)
        //MonsterView(data = data2)
    }
}