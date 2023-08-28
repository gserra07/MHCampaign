package com.example.mhcampaign

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MySelector(
    amountIn: Int,
    imageDrawable: Int? = null,
    maxLimit: Int? = null,
    minLimit: Int? = null,
    iconSize: Dp = 60.dp,
    onValueChange: (Int) -> Unit
) {
    var amount by remember { mutableStateOf(amountIn) }
    Box(contentAlignment = Alignment.Center) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            imageDrawable?.let { it1 -> painterResource(id = it1) }?.let { it2 ->
                Image(
                    painter = it2,
                    contentDescription = "",
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = 10.dp),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.minus_white),
                contentDescription = "",
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .clickable {
                        if (minLimit != null) {
                            if (amount > minLimit)
                                amount--
                        } else
                            amount--
                        onValueChange(amount)
                    },
            )
            Text(
                text = "$amount",
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .width(25.dp),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.add_black),
                contentDescription = "",
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .clickable {
                        if (maxLimit != null) {
                            if (amount < maxLimit)
                                amount++
                        } else
                            amount++
                        onValueChange(amount)
                    },
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyViewPreview() {
    var potions by remember { mutableStateOf(0) }
    var days by remember { mutableStateOf(0) }
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Box {

            MySelector(
                potions,
                R.drawable.potion
            ) { }
        }
        Box() {

            MySelector(days, R.drawable.calendar_white) { }
        }

    }
}