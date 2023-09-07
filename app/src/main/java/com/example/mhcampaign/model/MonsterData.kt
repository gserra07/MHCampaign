package com.example.mhcampaign.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.model.enums.Monster

data class MonsterData(
    private val _monster: Monster,
    private val _easy: Int = 0,
    private val _medium: Int = 0,
    private val _hard: Int = 0
) {
    val monster = _monster

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
    }

}

class MonsterListViewModel(list: MutableList<MonsterData>) {
    var _monsterList = MutableLiveData<MutableList<MonsterData>>(list)
    var monsterList: LiveData<MutableList<MonsterData>> = _monsterList

    fun updateMonster(index: Int, monster: MonsterData) {
        _monsterList.value?.set(index, monster)
    }
}
