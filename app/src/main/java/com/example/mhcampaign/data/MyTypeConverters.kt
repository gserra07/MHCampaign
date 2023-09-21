package com.example.xGuardiapp.database

import androidx.room.TypeConverter
import com.example.mhcampaign.model.MonsterDataModel
import com.example.mhcampaign.model.enums.ExpasionType
import com.example.mhcampaign.model.enums.HunterWeapon
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.model.enums.PartItem
import com.example.mhcampaign.model.enums.PartModel
import com.example.mhcampaign.model.enums.PartType
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class MyTypeConverters {

    //Campaign converters
    @TypeConverter
    open fun fromJSONToMonsterList(value: String?): MutableList<MonsterDataModel?>? {
        val listType = object : TypeToken<MutableList<MonsterDataModel?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMonsterListToJSON(list: MutableList<MonsterDataModel?>?): String? {
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
    fun fromExpasionTypeToJSON(expansionType: ExpasionType?): String? {
        val gson = Gson()
        return gson.toJson(expansionType)
    }

    //hunter converters
    @TypeConverter
    open fun fromJSONToHunterWeapon(value: String?): HunterWeapon? {
        val listType = object : TypeToken<HunterWeapon?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromHunterWeaponToJSON(hunterWeapon: HunterWeapon): String? {
        val gson = Gson()
        return gson.toJson(hunterWeapon)
    }

    @TypeConverter
    open fun fromJSONToPartModelList(value: String?): MutableList<PartModel> {
        val listType = object : TypeToken<MutableList<PartModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPartModelListToJSON(list: MutableList<PartModel>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    open fun fromJSONToPartItem(value: String?): PartItem? {
        val listType = object : TypeToken<PartItem?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPartItemToJSON(partItem: PartItem): String? {
        val gson = Gson()
        return gson.toJson(partItem)
    }
    @TypeConverter
    open fun fromJSONToPartType(value: String?): PartType? {
        val listType = object : TypeToken<PartType?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPartTypeToJSON(partType: PartType): String? {
        val gson = Gson()
        return gson.toJson(partType)
    }
}