package com.example.mhcampaign.model

import com.example.mhcampaign.model.enums.Monster

data class MonsterDataModel(
    val monster: Monster,
    var easy: Int = 0,
    var medium: Int = 0,
    var hard: Int = 0
) {
    fun updateValues(easy: Int, medium: Int, hard: Int) {
        this.easy = easy
        this.medium = medium
        this.hard = hard
    }

}

