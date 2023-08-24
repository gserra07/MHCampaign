package com.example.mhcampaign.model

import com.example.mhcampaign.R

data class MonsterData(val monster: Monster,
                       val easyCount: Int = 0,
                       val mediumCount: Int = 0,
                       val hardCount: Int = 0) {
}

enum class Monster(val monsterName: String, val icon: Int, var isFourStars: Boolean) {
    GREATJAGRAS("Great Jagras", R.drawable.jagras, false),
    RATHALOS("Rathalos", R.drawable.rathalos, true),
}