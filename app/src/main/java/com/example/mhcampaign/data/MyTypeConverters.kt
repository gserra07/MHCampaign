package com.example.xGuardiapp.database

import androidx.room.TypeConverter
import com.example.mhcampaign.model.MonsterDataModel
import com.example.mhcampaign.model.enums.ExpasionType
import com.example.mhcampaign.model.enums.Monster
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    open fun fromJSONToDayList(value: String?): MutableList<MonsterDataModel?>? {
        val listType = object : TypeToken<MutableList<MonsterDataModel?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromDayListToJSON(list: MutableList<MonsterDataModel?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    open fun fromJSONToMonster(value: String?): Monster? {
        val listType = object : TypeToken<Monster?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMonsterToJSON(monster: Monster?): String? {
        val gson = Gson()
        return gson.toJson(monster)
    }
    @TypeConverter
    open fun fromJSONToExpasionType(value: String?): ExpasionType? {
        val listType = object : TypeToken<ExpasionType?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromExpasionTypeToJSON(monster: ExpasionType?): String? {
        val gson = Gson()
        return gson.toJson(monster)
    }
}