package com.example.mhcampaign.examples

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun HunterViewHolder(data: HunterData, onEditListener: (HunterData) -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        val (weaponIcon, spacer, name, weaponName, editIcon) = createRefs()
        Image(
            painter = painterResource(id = data.weaponIcon),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .constrainAs(weaponIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Spacer(modifier = Modifier
            .width(15.dp)
            .constrainAs(spacer) {
                start.linkTo(weaponIcon.end)
            })
        Text(text = data.hunterName, fontSize = 20.sp, modifier = Modifier.constrainAs(name) {
            top.linkTo(weaponIcon.top)
            start.linkTo(spacer.end)
            bottom.linkTo(weaponName.top)
        })
        Text(
            text = data.weaponName,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(weaponName) {
                top.linkTo(name.bottom)
                start.linkTo(spacer.end)
                bottom.linkTo(weaponIcon.bottom)
            })
        Icon(
            imageVector = Icons.Rounded.Edit,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .constrainAs(editIcon) {
                    end.linkTo(parent.end, 20.dp)
                    top.linkTo(weaponIcon.top)
                    bottom.linkTo(weaponIcon.bottom)
                }
                .clickable { onEditListener(data) })
    }


}

@Preview(showSystemUi = true)
@Composable
fun HunterViewHolderPreview() {

    Column {
        HunterWeapon.values().forEachIndexed { index, value ->
            HunterViewHolder(
                data = HunterData(hunterName = "Asesino $index", value),
                onEditListener = { data -> Log.d(data.hunterName, "") })
            if (index != HunterWeapon.values().size - 1)
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp))
        }
    }
}