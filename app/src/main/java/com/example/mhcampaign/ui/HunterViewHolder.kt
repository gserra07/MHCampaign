package com.example.mhcampaign.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.ui.theme.MHCampaignTheme

@Composable
fun HunterViewHolder(
    data: HunterDataModel? = null,
    position: Int = -1,
    onEditListener: (data:HunterDataModel?, position:Int) -> Unit
) {
    MHCampaignTheme(darkTheme = false) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable { onEditListener(data,position) }
        ) {
            if (data != null) {
                val (weaponIcon, spacer, name, weaponName, editIcon) = createRefs()
                Image(
                    painter = painterResource(id = data.hunterWeapon.icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(weaponIcon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )
                Spacer(modifier = Modifier
                    .width(10.dp)
                    .constrainAs(spacer) {
                        start.linkTo(weaponIcon.end)
                    })
                Text(
                    text = data.hunterName,
                    fontSize = 20.sp,
                    modifier = Modifier.constrainAs(name) {
                        top.linkTo(weaponIcon.top)
                        start.linkTo(spacer.end)
                        bottom.linkTo(weaponName.top)
                    })
                Text(
                    text = data.hunterWeapon.weaponName,
                    fontSize = 14.sp,
                    modifier = Modifier.constrainAs(weaponName) {
                        top.linkTo(name.bottom)
                        start.linkTo(spacer.end)
                        bottom.linkTo(weaponIcon.bottom)
                    })
//            Icon(
//                    imageVector = Icons.Rounded.Edit,
//                    contentDescription = "",
//                    modifier = Modifier
//                            .size(30.dp)
//                            .constrainAs(editIcon) {
//                                end.linkTo(parent.end, 20.dp)
//                                top.linkTo(weaponIcon.top)
//                                bottom.linkTo(weaponIcon.bottom)
//                            }
//                            .clickable { onEditListener(data) })
            } else {
                val (addIcon, spacer, addHunter) = createRefs()
                Image(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(addIcon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )
                Spacer(modifier = Modifier
                    .width(15.dp)
                    .constrainAs(spacer) {
                        start.linkTo(addIcon.end)
                    })
                Text(
                    text = "Add Hunter",
                    fontSize = 16.sp,
                    modifier = Modifier.constrainAs(addHunter) {
                        top.linkTo(addIcon.top)
                        start.linkTo(spacer.end)
                        bottom.linkTo(addIcon.bottom)
                    })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HunterViewHolderPreview() {
    val rvState = rememberLazyListState()
    val corutinesScope = rememberCoroutineScope()
    var data = listOf<HunterDataModel>(
        HunterDataModel(0,"hunter 1", HunterWeapon.BOW),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 2", HunterWeapon.DUAL_BLADES),
        HunterDataModel(0,"hunter 3", HunterWeapon.CHARGE_BLADE)
    )
    Column {
        HunterViewHolder(
            onEditListener = { data, int -> Log.d(data?.hunterName, "") })
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(HunterWeapon.values()) { index, value ->
                HunterViewHolder(
                    data = HunterDataModel(id=0,hunterName = "Hunter $index", value),
                    onEditListener = { data,int -> Log.d(data?.hunterName, "") })
                if (index != HunterWeapon.values().size - 1)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    )
            }
        }
    }

//    Column {
//        HunterWeapon.values().forEachIndexed { index, value ->
//            HunterViewHolder(
//                data = HunterData(hunterName = "Asesino $index", value),
//                onEditListener = { data -> Log.d(data.hunterName, "") })
//            if (index != HunterWeapon.values().size - 1)
//                Divider(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 3.dp))
//        }
//    }
}