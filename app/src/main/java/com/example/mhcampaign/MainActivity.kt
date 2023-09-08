package com.example.mhcampaign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mhcampaign.ui.theme.MHCampaignTheme


class MainActivity : ComponentActivity() {
    private var amount1 = mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MHCampaignTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    MySelector(amount1, R.drawable.potion)
                    var text by remember {
                        mutableStateOf("")
                    }
//                    MyTextField(text) { text = it }
//                    exampleSelector()
//                    HunterViewHolderPreview()
//                    DropDownPreview()
//                    MyAlertPreview()
//                    MyRecyclePreview()
                    MyDrawerPreview()
                }
            }
        }
    }

}


@Composable
fun SuperText() {
    Text(
        text = "Penesaurio",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(top = 15.dp)
            .background(color = Color.Cyan)
    )
}


@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(color = Color.Cyan)
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                SuperText()
                SuperText()

            }
        }
    }
}
