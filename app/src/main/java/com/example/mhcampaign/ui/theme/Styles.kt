package com.example.mhcampaign.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.mhcampaign.R
import com.example.mhcampaign.ui.MHSimpleDropDown
import com.example.mhcampaign.examples.LargeDropdownMenu
import com.example.mhcampaign.model.enums.HunterWeapon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
//                        textColor = Black,
        containerColor = Color.Transparent,
        unfocusedSupportingTextColor = Color.Black,
//                        focusedLabelColor = Sand,
        unfocusedLabelColor = md_theme_light_primary,
//                        focusedBorderColor = Sand,
        unfocusedBorderColor = md_theme_light_primary,
//                        focusedTrailingIconColor = Sand,
//                        unfocusedTrailingIconColor = Sand)
    )
}

@Preview(showSystemUi = true)
@Composable
fun DropDownPreview() {
    val coffeeDrinks = listOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    Column {

        MHSimpleDropDown("Campaign", coffeeDrinks) { name, index -> Log.d("Dropdown", "$name  $index") }
        var selectedIndex by remember { mutableStateOf(-1) }
        LargeDropdownMenu(
            label = "Hunter Weapon",
            items = HunterWeapon.values().asList(),
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ -> selectedIndex = index },
        )
    }
}

val mhFont = FontFamily(Font(R.font.monsterhunter))

