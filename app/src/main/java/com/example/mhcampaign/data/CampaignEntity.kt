package com.example.mhcampaign.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mhcampaign.model.MonsterData

@Entity(tableName = "campaign_table")
data class CampaignEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
//    @ColumnInfo(name = "potions") var potions: Int = 0,
//    @ColumnInfo(name = "days") var days: Int = 0,
//    @ColumnInfo(name = "list") var list: MutableList<MonsterData> =
//        mutableListOf()
) {

}