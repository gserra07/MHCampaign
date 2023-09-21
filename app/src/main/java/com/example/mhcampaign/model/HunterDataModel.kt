package com.example.mhcampaign.model

import com.example.mhcampaign.data.hunters.HunterEntity
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.enums.PartModel

class HunterDataModel(
    var id: Int,
    var hunterName: String,
    var hunterWeapon: HunterWeapon,
    var inventory: MutableList<PartModel> = mutableListOf(),
    var campaignId: Int = -1
) {
    fun campaignId(i: Int): HunterDataModel {
        campaignId = i
        return this
    }
}

fun HunterEntity.toDomain() = HunterDataModel(
    id = id,
    hunterName = hunterName,
    hunterWeapon = hunterWeapon,
    inventory = inventory,
    campaignId = campaignId
)