package com.example.mhcampaign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun MonsterView(data: MonsterData, paddingValues: PaddingValues = PaddingValues()) {
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
            .fillMaxWidth()
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
                start.linkTo(monsterIcon.end, 50.dp)
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
                }
                MySelector(
                    amountIn = mediumCount,
                    icon = R.drawable.two_stars,
                    minLimit = 0,
                    iconSize = 40.dp
                ) {
                    mediumCount = it
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
                }
            }
        }
    }
}

fun MontserListView(
    monsterList: MutableList<MonsterData>,
    onChangeListener: (MonsterData) -> Unit
) {


}

@Preview(showSystemUi = true)
@Composable
fun MyMonsterPreview() {
    val data = MonsterData(Monster.GREAT_JAGRAS, 1, 0, 0)
    val data2 = MonsterData(Monster.RATHALOS, 0, 2, 0)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MonsterView(data = data)
        //MonsterView(data = data2)
    }
}