package com.example.mhcampaign

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mhcampaign.model.PartItem
import com.example.mhcampaign.model.PartModel
import com.example.mhcampaign.model.PartType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartView(
    data: PartModel,
    paddingValues: PaddingValues = PaddingValues(),
    onTextChange: (PartModel) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(paddingValues).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = data.name.type.icon),
            contentDescription = "",
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = data.name.partName, fontSize = 13.sp)
        Spacer(modifier = Modifier.width(5.dp))
        var valueText by remember { mutableStateOf("${data.count}") }
        BasicTextField(
            value = valueText,
            onValueChange = {
                if (it.length < 3) {
                    valueText = it
                    data.count = it.toIntOrNull() ?: 0
                    onTextChange(data)
                }
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Bold, textAlign = TextAlign.End),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(40.dp).defaultMinSize(minWidth = 40.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyPartPreview() {
    var data = listOf(
        PartModel(PartItem.SMALL_BONE).count(60),
        PartModel( PartItem.MEDIUM_BONE).count(60),
        PartModel(PartItem.MACHALITE).count(60),
        PartModel(PartItem.DRAGONITE).count(60),
        PartModel(PartItem.GREAT_JAGRAS_CLAW).count(60),

    )

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier=Modifier.padding(10.dp),content = {
        itemsIndexed(data) { index, item ->
            PartView(item, PaddingValues(horizontal = 10.dp, vertical = 10.dp)) {
                Log.d("PartView", "${it.name}  ${it.count}")
            }
        }

    })
}