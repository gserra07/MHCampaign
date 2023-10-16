package com.example.mhcampaign.data.hunters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.enums.PartModel

@Entity(tableName = "hunters_table")
class HunterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "hunterName") var hunterName: String,
    @ColumnInfo(name = "hunterWeapon") var hunterWeapon: HunterWeapon,
    @ColumnInfo(name = "inventory") var inventory: MutableList<PartModel> = mutableListOf(),
    @ColumnInfo(name = "campaignId") var campaignId: Int = -1,

    )

fun HunterDataModel.toDataBase(isNew: Boolean = false): HunterEntity {
    var hunterEntity = HunterEntity(
        hunterName = hunterName,
        hunterWeapon = hunterWeapon,
        inventory = inventory,
        campaignId = campaignId
    )
    if (!isNew)
        hunterEntity.id = id
    return hunterEntity
}

