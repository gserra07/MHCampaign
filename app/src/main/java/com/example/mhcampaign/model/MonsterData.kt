package com.example.mhcampaign.model

import com.example.mhcampaign.R

data class MonsterData(
    val monster: Monster,
    val easyCount: Int = 0,
    val mediumCount: Int = 0,
    val hardCount: Int = 0
) {
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
    JYURATODUS("Jyuratodus", R.drawable.monster_jyuratodus_icon,),
    DIABLOS("Diablos", R.drawable.monster_diablos_icon, true),
    BLACK_DIABLOS("Black Diablos", R.drawable.monster_black_diablos_icon, true),
}