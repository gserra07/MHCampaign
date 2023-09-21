package com.example.mhcampaign.ui.monsters

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.R
import com.example.mhcampaign.model.MonsterDataModel
import com.example.mhcampaign.ui.counter.CounterViewModel
import com.example.mhcampaign.ui.counter.MySelector
import com.example.mhcampaign.model.enums.Monster

@Composable
fun MonsterView(
    data: MonsterViewModel,
    paddingValues: PaddingValues = PaddingValues(),
    onChangeListener: (MonsterViewModel) -> Unit
) {
    val easyCount: Int by data.easyCount.observeAsState(initial = 0)
    val mediumCount: Int by data.mediumCount.observeAsState(initial = 0)
    val hardCount: Int by data.hardCount.observeAsState(initial = 0)
    var context = LocalContext.current

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
                        amount = easyCount,
                        maxLimit = null,
                        minLimit = 0
                    ),
                    icon = R.drawable.blue_star_24,
                    iconSize = 40.dp
                ) {
                    data.onValuesChanges(it, mediumCount, hardCount)
                    onChangeListener(data)
                }
                MySelector(
                    counterViewModel = CounterViewModel(
                        amount = mediumCount,
                        maxLimit = null,
                        minLimit = 0
                    ),
                    icon = R.drawable.two_stars,
                    iconSize = 40.dp
                ) {
                    data.onValuesChanges(easyCount, it, hardCount)
                    onChangeListener(data)
                }
                MySelector(
                    counterViewModel = CounterViewModel(
                        amount = hardCount,
                        maxLimit = null,
                        minLimit = 0
                    ), icon = if (data.monster.isFourStars)
                        R.drawable.four_stars
                    else
                        R.drawable.three_stars,
                    iconSize = 40.dp
                ) {
                    data.onValuesChanges(easyCount, mediumCount, it)
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
    onChangeListener: (index: Int, MonsterViewModel) -> Unit
) {
    val list: MutableList<MonsterDataModel> by monsterList.monsterList.observeAsState(
        initial = mutableListOf()
    )
    var viewModelList = list.map { MonsterViewModel(it) }
    viewModelList = viewModelList.sortedBy { it.monster.index }?.toMutableList()!!
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(viewModelList) { i, monster ->
            MonsterView(data = monster) {
                //monsterList.updateMonster(i, monster)
                onChangeListener(i, monster)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyMonsterPreview() {
    val data = MonsterDataModel(Monster.GREAT_JAGRAS, 1, 0, 0)
    val data2 = MonsterDataModel(Monster.RATHALOS, 0, 2, 0)
    val monsterList = mutableListOf(data, data2)

    MonsterListView(
        monsterList = MonsterListViewModel(monsterList),
        paddingValues = PaddingValues(start = 20.dp),
        onChangeListener = { i, m -> })
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        MonsterView(data = data)
        //MonsterView(data = data2)
    }
}