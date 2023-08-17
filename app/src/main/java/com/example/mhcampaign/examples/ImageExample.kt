package com.example.mhcampaign.examples

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.R

@Composable
fun MyImageExample() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "pocion",
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                //.clip(shape = CircleShape)
                //.border(5.dp, Color.Red, CircleShape)
                .clip(shape = RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun MyIconExample() {
    Icon(
        painter = painterResource(id = R.drawable.add_white),
        contentDescription = "",
        tint = Color.Blue
    )
}

@Preview(showSystemUi = true)
@Composable
fun MyPreviewImage() {
    Column {
        MyImageExample()
        MyIconExample()
    }
}