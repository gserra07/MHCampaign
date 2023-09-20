package com.example.mhcampaign.model

import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.enums.PartModel

class HunterData(
        var hunterName: String,
        var hunterWeapon: HunterWeapon,
        var inventory: MutableList<PartModel> = mutableListOf()
) {
    var campaignId: Int = -1
    fun campaignId(i: Int): HunterData {
        campaignId = i
        return this
    }
}