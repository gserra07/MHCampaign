package com.example.mhcampaign

import android.view.RoundedCorner
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.ui.theme.md_theme_dark_primary
import com.example.mhcampaign.ui.theme.md_theme_light_inversePrimary
import com.example.mhcampaign.ui.theme.md_theme_light_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@Composable
fun MySelector(
    amountIn: Int,
    icon: Int? = null,
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
                onClick = { /*TODO*/ },
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
                            if (minLimit != null) {
                                if (amount > minLimit)
                                    amount--
                            } else
                                amount--
                            onValueChange(amount)
                        },
                    colorFilter = ColorFilter.tint(md_theme_dark_primary)
                )
            }
            Text(
                text = "$amount",
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .width(25.dp),
                textAlign = TextAlign.Center
            )
            OutlinedButton(
                onClick = { /*TODO*/ },
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
                            if (maxLimit != null) {
                                if (amount < maxLimit)
                                    amount++
                            } else
                                amount++
                            onValueChange(amount)
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