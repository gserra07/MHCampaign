package com.example.mhcampaign.ui.monsters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.model.MonsterDataModel

data class MonsterViewModel(
    val monsterData: MonsterDataModel
) {
    val monster = monsterData.monster
    private val _easy: Int = monsterData.easy
    private val _medium: Int = monsterData.medium
    private val _hard: Int = monsterData.hard

    private val _easyCount: MutableLiveData<Int> = MutableLiveData(_easy)
    val easyCount: MutableLiveData<Int> = _easyCount

    private val _mediumCount: MutableLiveData<Int> = MutableLiveData(_medium)
    val mediumCount: MutableLiveData<Int> = _mediumCount

    private val _hardCount: MutableLiveData<Int> = MutableLiveData(_hard)
    val hardCount: MutableLiveData<Int> = _hardCount


    fun onValuesChanges(easy: Int, medium: Int, hard: Int) {
        _easyCount.value = easy
        _mediumCount.value = medium
        _hardCount.value = hard
        monsterData.updateValues(easy,medium,hard)
    }

}

class MonsterListViewModel(list: MutableList<MonsterDataModel>) {
    var _monsterList = MutableLiveData<MutableList<MonsterDataModel>>(list)
    var monsterList: LiveData<MutableList<MonsterDataModel>> = _monsterList

    fun updateMonster(index: Int, monster: MonsterDataModel) {
        _monsterList.value?.set(index, monster)
    }
}
