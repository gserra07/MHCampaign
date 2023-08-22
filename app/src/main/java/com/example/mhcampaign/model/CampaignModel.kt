package com.example.mhcampaign.model

data class CampaignModel(var name: String, var potions: Int = 0, var days: Int = 0) {
    var index: Int? = null
    fun potions(i: Int): CampaignModel {
        potions = i
        return this
    }
    fun days(i: Int): CampaignModel {
        days = i
        return this
    }
}