package com.example.mhcampaign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.counter.CounterViewModel
import com.example.mhcampaign.counter.MySelector
import com.example.mhcampaign.model.Monster
import com.example.mhcampaign.model.MonsterData
import com.example.mhcampaign.model.MonsterListViewModel

@Composable
fun MonsterView(
    data: MonsterData,
    paddingValues: PaddingValues = PaddingValues(),
    onChangeListener: (MonsterData) -> Unit
) {
    val easyCount2: Int by data.easyCount.observeAsState(initial = 0)
    val mediumCount2: Int by data.mediumCount.observeAsState(initial = 0)
    val hardCount2: Int by data.hardCount.observeAsState(initial = 0)
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
                    counterViewModel = CounterViewModel(
                        amount = easyCount2,
                        maxLimit = null,
                        minLimit = 0
                    ),
                    icon = R.drawable.blue_star_24,
                    iconSize = 40.dp
                ) {
                    data.onValuesChanges(easyCount2, mediumCount2, hardCount2)
                    onChangeListener(data)
                }
                MySelector(
                    counterViewModel = CounterViewModel(
                        amount = mediumCount2,
                        maxLimit = null,
                        minLimit = 0
                    ),
                    icon = R.drawable.two_stars,
                    iconSize = 40.dp
                ) {
                    data.onValuesChanges(easyCount2, mediumCount2, hardCount2)
                    onChangeListener(data)
                }
                MySelector(
                    counterViewModel = CounterViewModel(
                        amount = hardCount2,
                        maxLimit = null,
                        minLimit = 0
                    ), icon = if (data.monster.isFourStars)
                        R.drawable.four_stars
                    else
                        R.drawable.three_stars,
                    iconSize = 40.dp
                ) {
                    data.onValuesChanges(easyCount2, mediumCount2, hardCount2)
                    onChangeListener(data)
                }
            }
        }
    }
}

@Composable
fun MonsterListView(
    monsterList: MonsterListViewModel,
    paddingValues: PaddingValues = PaddingValues(),
    onChangeListener: (MutableList<MonsterData>) -> Unit
) {
    val list: MutableList<MonsterData> by monsterList.monsterList.observeAsState(
        initial = mutableListOf()
    )
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(list) { i, monster ->
            MonsterView(data = monster) {
                monsterList.updateMonster(i, monster)
                onChangeListener(list)
            }
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
        monsterList = MonsterListViewModel(monsterList),
        paddingValues = PaddingValues(start = 20.dp),
        onChangeListener = {})
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        MonsterView(data = data)
        //MonsterView(data = data2)
    }
}