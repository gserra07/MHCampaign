package com.example.mhcampaign.examples

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mhcampaign.R
import com.example.mhcampaign.ui.theme.LightSand
import com.example.mhcampaign.ui.theme.Sand

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
    onDismissListener: () -> Unit,
    onConfirmListener: (String) -> Unit
) {
    if (visibility) {
        var hunterName by remember {
            mutableStateOf("Hunter 1")
        }
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = LightSand,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Hola", fontSize = 25.sp)
//                MyDropDown(
//                    itemList = HunterWeapon.values().map { it.name },
//                    label = "Weapon",
//                    onSelectoptionListener = {}
//                )
                var selectedIndex by remember { mutableStateOf(-1) }
                LargeDropdownMenu(
                    label = "Sample",
                    items = HunterWeapon.values().map { it.name },
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, _ -> selectedIndex = index },
                )
                TextField(
                    label = { Text(text = "Hunter name") },
                    value = hunterName,
                    onValueChange = { hunterName = it },
                    modifier = Modifier.padding(horizontal = 32.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Sand,
                        unfocusedLabelColor = Sand,
                        focusedIndicatorColor = Sand,
                        unfocusedIndicatorColor = Sand
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { }) {
                        Text(text = "Cancelar")
                    }
                    TextButton(onClick = { onConfirmListener }) {
                        Text(text = "Guardar")
                    }
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyAlertPreview() {
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
//    MyAlertDialog(visibility = visible, onConfirmListener = onConfrim, onDismissListener = {})
    MyCustomDialog(visibility = visible, onConfirmListener = onConfrim, onDismissListener = {})
}