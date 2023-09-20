package com.example.mhcampaign.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.data.CampaignEntity
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.ui.monsters.MonsterViewModel

data class CampaignModel(
    var id: Int = -1,
    var name: String,
    var potions: Int = 0,
    var days: Int = 0,
    var list: MutableList<MonsterDataModel> =
        mutableListOf()
) {
    private val _monsterList = MutableLiveData<MutableList<MonsterDataModel>>(list)
    var monsterList: LiveData<MutableList<MonsterDataModel>> = _monsterList

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
        _monsterList.value?.add(MonsterDataModel(monster))
        _monsterList.value = _monsterList.value?.sortedBy { it.monster.index }?.toMutableList()
    }

    fun updateMonsterList(list: MutableList<MonsterDataModel>) {
        _monsterList.value = list
    }
}

fun CampaignEntity.toDomain() = CampaignModel(
    name = name,
    potions = potions,
    days = days,
    list = monsterList
).id(id)