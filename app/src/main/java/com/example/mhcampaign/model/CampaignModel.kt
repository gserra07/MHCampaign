package com.example.mhcampaign.model

data class CampaignModel(
    var name: String,
    var potions: Int = 0,
    var days: Int = 0,
    var monsterList: MutableList<MonsterData> = mutableListOf()
) {
    var id: Int? = null
    fun potions(i: Int): CampaignModel {
        potions = i
        return this
    }

    fun days(i: Int): CampaignModel {
        days = i
        return this
    }

    fun id(i: Int): CampaignModel {
        id = i
        return this
    }

    fun addMonster(monster: Monster) {
        monsterList.add(MonsterData(monster))
    }
}