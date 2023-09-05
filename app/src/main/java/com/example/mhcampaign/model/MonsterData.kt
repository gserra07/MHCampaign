package com.example.mhcampaign.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.R

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

enum class Monster(
    val monsterName: String,
    val icon: Int,
    val isFourStars: Boolean = false,
    val isElderDragon: Boolean = false
) {
    GREAT_JAGRAS("Great Jagras", R.drawable.monster_jagras_icon),
    TOBI_KADACHI("Tobi Kadachi", R.drawable.monster_tobi_kadachi_icon),
    ANJANATH("Anjanath", R.drawable.monster_anjanath_icon),
    RATHALOS("Rathalos", R.drawable.monster_rathalos_icon, true),
    AZURE_RATHALOS("Azure Rathalos", R.drawable.monster_azure_rathalos_icon, true),
    BARROTH("Barroth", R.drawable.monster_barroth_icon),
    PUKEI_PUKEI("Pukei-pukei", R.drawable.monster_pukei_pukei_icon),
    JYURATODUS("Jyuratodus", R.drawable.monster_jyuratodus_icon),
    DIABLOS("Diablos", R.drawable.monster_diablos_icon, true),
    BLACK_DIABLOS("Black Diablos", R.drawable.monster_black_diablos_icon, true),
}