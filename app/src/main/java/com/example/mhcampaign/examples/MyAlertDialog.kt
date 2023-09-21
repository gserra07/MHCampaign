package com.example.mhcampaign.examples

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import com.example.mhcampaign.ui.theme.md_theme_light_primaryContainer

@Composable
fun MyAlertDialog(
    visibility: Boolean,
    onDismissListener: () -> Unit,
    onConfirmListener: (String) -> Unit
) {
    if (visibility) {
        var body by remember {
            mutableStateOf("Cuerpo de texto")
        }
        AlertDialog(

            onDismissRequest = { body = "no hagas trampas" },
            confirmButton = {
                TextButton(onClick = { onConfirmListener(body) }) {
                    Text(text = "Aceptar")
                }
            },
            title = { Text(text = "titlulo de la alerta") },
            text = { Text(text = body) },
            dismissButton = {
                TextButton(onClick = { onDismissListener() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomDialog(
    visibility: Boolean,
    data: HunterDataModel? = null,
    onDismissListener: () -> Unit,
    onConfirmListener: (String, Int) -> Unit,
    context: Context = LocalContext.current
) {
    MHCampaignTheme(darkTheme = false) {

        if (visibility) {
            var hunterName by remember {
                mutableStateOf(data?.hunterName ?: "")
            }
            var selectedIndex by remember {
                mutableStateOf(
                    if (data != null) HunterWeapon.values().indexOf(data.hunterWeapon) else -1
                )
            }

            Dialog(
                onDismissRequest = { },
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
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Edit Hunter", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(20.dp))
                    LargeDropdownMenu(
                        label = "Hunter Weapon",
                        items = HunterWeapon.values().asList(),
                        selectedIndex = selectedIndex,
                        onItemSelected = { index, _ -> selectedIndex = index },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        label = { Text(text = "Hunter name") },
                        value = hunterName,
                        onValueChange = { hunterName = it },
                        singleLine = true,
                        colors = GetTextFieldColors()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { onDismissListener() }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            onConfirmListener(hunterName, selectedIndex)
                            data?.hunterName = hunterName
                            data?.hunterWeapon = HunterWeapon.values()[selectedIndex]
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
fun MyAlertPreview() {
    var data by remember {
        mutableStateOf(HunterDataModel(0,"Hunter1", HunterWeapon.CHARGE_BLADE))
    }
    var visible by remember {
        mutableStateOf(false)
    }
    TextButton(onClick = { visible = !visible }) {
        Text(text = "boton para ver")
    }
    var onConfrim: (String) -> Unit = { body: String ->
        visible = false
        Log.d("Preview", body)
    }
//    MyAlertDialog(visibility = visible, onConfirmListener = onConfrim, onDismissListener = {})
    MHCampaignTheme(darkTheme = false) {

        MyCustomDialog(
            visibility = visible,
            data,
            onConfirmListener = { hunterName, selectedIndex ->
                data.hunterName = hunterName
                data.hunterWeapon = HunterWeapon.values()[selectedIndex]
                visible = false
            },
            onDismissListener = {
                visible = false
            },
            context = LocalContext.current
        )
    }
}