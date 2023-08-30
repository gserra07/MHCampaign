package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mhcampaign.model.enums.PartItem
import com.example.mhcampaign.model.enums.PartModel
import com.example.mhcampaign.ui.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartView(
    data: PartModel,
    paddingValues: PaddingValues = PaddingValues(),
    onTextChange: (PartModel) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .defaultMinSize(minHeight = 30.dp)
    ) {
        val (partIcon, partName, quantity, addIcon, minusIcon) = createRefs()
        var valueText by remember { mutableStateOf("${data.quantity}") }

        Image(
            painter = painterResource(id = data.name.partIcon),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .constrainAs(partIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = data.name.partName,
            fontSize = 12.sp,
            lineHeight = 13.sp,
            modifier = Modifier.constrainAs(partName) {
                top.linkTo(partIcon.top)
                start.linkTo(partIcon.end, 2.dp)
                bottom.linkTo(partIcon.bottom)
                end.linkTo(quantity.start)
                width = Dimension.fillToConstraints
            })
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            tint = md_theme_light_primary,
            contentDescription = "Add icon",
            modifier = Modifier
                .constrainAs(addIcon) {
                    top.linkTo(partIcon.top)
                    start.linkTo(quantity.start)
                    end.linkTo(quantity.end)
                    bottom.linkTo(partIcon.top)
                }
                .clickable { valueText = (valueText.toInt() + 1).toString() }
        )
        BasicTextField(
            value = valueText,
            onValueChange = {
                if (it.length < 3) {
                    valueText = it
                    data.quantity = it.toIntOrNull() ?: 0
                    onTextChange(data)
                }
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(20.dp)
                .defaultMinSize(minWidth = 40.dp)
                .constrainAs(quantity) {
                    top.linkTo(partIcon.top)
                    end.linkTo(parent.end, 5.dp)
                    bottom.linkTo(partIcon.bottom)
                },
            enabled = false,
            readOnly = true
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            tint = md_theme_light_primary,
            contentDescription = "Substract icon",
            modifier = Modifier
                .constrainAs(minusIcon) {
                    top.linkTo(partIcon.bottom)
                    start.linkTo(quantity.start)
                    end.linkTo(quantity.end)
                    bottom.linkTo(partIcon.bottom)
                }
                .clickable {
                    if (valueText.toInt() > 0)
                        valueText = (valueText.toInt() - 1).toString()
                }

        )
    }
}

@Preview(showSystemUi = true, device = "spec:width=351dp,height=891dp")
@Composable
fun MyPartPreview() {
    val data = listOf(
        PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE).count(60),
        PartModel(PartItem.MEDIUM_BONE).count(60),
        PartModel(PartItem.MACHALITE).count(60),
        PartModel(PartItem.DRAGONITE).count(60),
        PartModel(PartItem.GREAT_JAGRAS_CLAW).count(60),

        )

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp), content = {
        itemsIndexed(data) { index, item ->
            PartView(item, PaddingValues(horizontal = 10.dp, vertical = 10.dp)) {
                Log.d("PartView", "${it.name}  ${it.quantity}")
            }
        }

    })
}