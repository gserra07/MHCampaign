package com.example.mhcampaign.ui.counter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.R
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import com.example.mhcampaign.ui.theme.md_theme_dark_primary

@Composable
fun MySelector(
    counterViewModel: CounterViewModel = CounterViewModel(),
    icon: Int? = null,
    iconSize: Dp = 60.dp,
    onValueChange: (Int) -> Unit
) {
    val count: Int by counterViewModel.count.observeAsState(initial = 0)
    Box(contentAlignment = Alignment.Center) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let { it1 -> painterResource(id = it1) }?.let { it2 ->
                Image(
                    painter = it2,
                    contentDescription = "",
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = 10.dp),
                )
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.size(25.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.Black),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.minus_black),
                    contentDescription = "",
                    modifier = Modifier
                        .background(
                            Color.Black,
                            shape = CircleShape
                        )
                        .width(25.dp)
                        .height(25.dp)
                        .clickable {
                            counterViewModel.subtract()
                            onValueChange(count)
                        },
                    colorFilter = ColorFilter.tint(md_theme_dark_primary)
                )
            }
            Text(
                text = "$count",
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .width(25.dp),
                textAlign = TextAlign.Center
            )
            OutlinedButton(
                onClick = { },
                modifier = Modifier.size(25.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.Black),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_black),
                    contentDescription = "",
                    modifier = Modifier
                        .background(
                            Color.Black,
                            shape = CircleShape
                        )
                        .width(25.dp)
                        .height(25.dp)
                        .clickable {
                            counterViewModel.add()
                            onValueChange(count)
                        },
                    colorFilter = ColorFilter.tint(md_theme_dark_primary)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyViewPreview() {
    val potionsModel = CounterViewModel(0)
    val daysModel = CounterViewModel(0)
    MHCampaignTheme(darkTheme = false) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Box {

                MySelector(
                    potionsModel,
                    R.drawable.potion
                ) { }
            }
            Box() {

                MySelector(daysModel, R.drawable.calendar_white) { }
            }

        }
    }
}