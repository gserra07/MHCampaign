package com.example.mhcampaign.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintExample() {
    ConstraintLayout {
        val (boxRed, boxMagenta, boxCyan, boxYellow, boxGreen) = createRefs()
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Yellow)
            .constrainAs(boxYellow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Magenta)
            .constrainAs(boxMagenta) {
                bottom.linkTo(boxYellow.top)
                start.linkTo(boxYellow.end)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .constrainAs(boxRed) {
                bottom.linkTo(boxYellow.top)
                end.linkTo(boxYellow.start)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Cyan)
            .constrainAs(boxCyan) {
                top.linkTo(boxYellow.bottom)
                end.linkTo(boxYellow.start)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Green)
            .constrainAs(boxGreen) {
                top.linkTo(boxYellow.bottom)
                start.linkTo(boxYellow.end)
            })
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConstraintGuide() {
    ConstraintLayout {
        val boxRed = createRef()
        val topGuide = createGuidelineFromTop(0.2f)
        val startGuide = createGuidelineFromStart(0.1f)

        Box(modifier = Modifier
            .size(120.dp)
            .background(color = Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            }) {

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConstraintBarrier() {
    ConstraintLayout {
        val (boxRed, boxGreen, boxYellow, boxBlue) = createRefs()
        val barrier = createEndBarrier(boxRed, boxGreen, boxBlue, margin = 10.dp)

        Box(modifier = Modifier
            .size(120.dp)
            .background(color = Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
            })
        Box(modifier = Modifier
            .size(20.dp)
            .background(color = Color.Green)
            .constrainAs(boxGreen) {
                top.linkTo(boxRed.bottom)
            })
        Box(modifier = Modifier
            .size(20.dp)
            .background(color = Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxGreen.bottom)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(color = Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(barrier)
            })
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConstraintChain() {
    ConstraintLayout {
        val (boxRed, boxGreen, boxYellow, boxBlue) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(color = Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(parent.start)
                end.linkTo(boxGreen.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(color = Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(boxRed.end)
                end.linkTo(boxBlue.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(color = Color.Blue)
            .constrainAs(boxBlue) {
                start.linkTo(boxGreen.end)
                end.linkTo(boxYellow.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(color = Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxBlue.end)
                end.linkTo(parent.end)
            })
        createHorizontalChain(boxRed,boxGreen,boxBlue,boxYellow, chainStyle = ChainStyle.Packed)
//        createHorizontalChain(boxRed,boxGreen,boxBlue,boxYellow, chainStyle = ChainStyle.Spread)
//        createHorizontalChain(boxRed,boxGreen,boxBlue,boxYellow, chainStyle = ChainStyle.SpreadInside)

    }
}