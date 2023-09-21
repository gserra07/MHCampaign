package com.example.mhcampaign.data.campaign

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.MonsterDataModel

@Entity(tableName = "campaign_table")
data class CampaignEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "potions") var potions: Int = 0,
    @ColumnInfo(name = "days") var days: Int = 0,
    @ColumnInfo(name = "monsterList") var monsterList: MutableList<MonsterDataModel> =
        mutableListOf<MonsterDataModel>()
)

fun CampaignModel.toDataBase() =
    CampaignEntity(id = id , name = name, potions = potions, days = days, monsterList = list)