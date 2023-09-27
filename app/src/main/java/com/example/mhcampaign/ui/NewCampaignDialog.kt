package com.example.mhcampaign.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mhcampaign.R
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.ui.theme.GetTextFieldColors
import com.example.mhcampaign.ui.theme.MHCampaignTheme
import com.example.mhcampaign.ui.theme.md_theme_light_primary
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer
import com.example.mhcampaign.ui.theme.mhFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCampaignDialog(
    visibility: Boolean,
    onDismissListener: () -> Unit,
    onConfirmListener: (hunterData: String) -> Unit,
    context: Context = LocalContext.current
) {
    MHCampaignTheme(darkTheme = false) {

        if (visibility) {

            var campaignName by remember {
                mutableStateOf("")
            }

            Dialog(
                onDismissRequest = { onDismissListener() },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = md_theme_light_primaryContainer,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .border(
                            BorderStroke(1.dp, md_theme_light_primary),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "New Campaign", fontSize = 25.sp, fontFamily = mhFont)
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        label = { Text(text = "Campaign name") },
                        value = campaignName,
                        onValueChange = { campaignName = it },
                        singleLine = true,
                        colors = GetTextFieldColors()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { onDismissListener() }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            if (campaignName != "") {

                                onConfirmListener(campaignName)

                            } else {
                                Toast.makeText(context, "Empty campaign name", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }) {
                            Text(text = context.getString(R.string.save_string))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyNewCampaignDialogPreview() {
    val data = listOf<HunterDataModel>(
        HunterDataModel(0, "hunter 1", HunterWeapon.BOW),
        HunterDataModel(0, "hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0, "hunter 3", HunterWeapon.GREAT_SWORD),
        HunterDataModel(0, "hunter 3", HunterWeapon.GREAT_SWORD),
        HunterDataModel(0, "hunter 3", HunterWeapon.GREAT_SWORD),
        HunterDataModel(0, "hunter 4", HunterWeapon.INSECT_GLAIVE),
    )
    var visible by remember {
        mutableStateOf(true)
    }
    TextButton(onClick = { visible = !visible }) {
        Text(text = "boton para ver")
    }
    var onConfrim: (String) -> Unit = { body: String ->
        visible = false
        Log.d("Preview", body)
    }
    NewCampaignDialog(
        visibility = visible,
//            selectedHunter = data[1],
        context = LocalContext.current,
        onDismissListener = { },
        onConfirmListener = { },
    )
}