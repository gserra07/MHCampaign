package com.example.mhcampaign.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class CampaignModel(
    var name: String,
    var potions: Int = 0,
    var days: Int = 0,
    var list: MutableList<MonsterData> =
        mutableListOf()
) {
    private val _monsterList = MutableLiveData<MutableList<MonsterData>>(list)
    var monsterList: LiveData<MutableList<MonsterData>> = _monsterList
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
        _monsterList.value?.add(MonsterData(monster))
    }

    fun updateMonsterList(list: MutableList<MonsterData>) {
        _monsterList.value = list
    }
}