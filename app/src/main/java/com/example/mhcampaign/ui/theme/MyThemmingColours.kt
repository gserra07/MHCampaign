package com.example.mhcampaign.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTextFieldColors(
        textColor: Color = Color.White,
        disabledTextColor: Color = Color.White,
        backgroundColor: Color = Color.White,
        cursorColor: Color = Color.White,
        errorCursorColor: Color = Color.White,
) = TextFieldDefaults.textFieldColors(
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        containerColor = backgroundColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
)

@Composable
fun MyAppTextButtonColors(
        textColor: Color = Color.Black,
        disabledTextColor: Color = Color.Gray,
        backgroundColor: Color = Color.Transparent,
        disabledBackgroundColor: Color = Color.LightGray,
        ) = ButtonDefaults.buttonColors(
        contentColor = textColor,
        containerColor = backgroundColor,
        disabledContainerColor = disabledBackgroundColor,
        disabledContentColor = disabledTextColor
)