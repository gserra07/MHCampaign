package com.example.mhcampaign.model

import com.example.mhcampaign.R

class HunterData(
    var hunterName: String,
    var hunterWeapon: HunterWeapon,
    var inventory: MutableList<PartModel> = mutableListOf()
) {
    var campaignId: Int? = null
    fun campaignId(i: Int): HunterData {
        campaignId = i
        return this
    }
}

enum class HunterWeapon(val weaponName: String, val icon: Int) {
    BOW("Bow", R.drawable.bow_icon),
    CHARGEBLADE(
        "Charge Blade",
        R.drawable.charge_blade_icon
    ),
    DUALBLADES(
        "Dual blades",
        R.drawable.dual_blades_icon
    ),
    GREATSWORD(
        "Great Sword",
        R.drawable.great_sword_icon
    ),
    GUNLANCE(
        "Gunlance",
        R.drawable.gunlance_icon
    ),
    HAMMER(
        "Hammer",
        R.drawable.hammer_icon
    ),
    HEAVYBOWGUN(
        "Heavy Bowgun",
        R.drawable.heavy_bowgun_icon
    ),
    HUNTINGHORN(
        "Hunting Horn",
        R.drawable.hunting_horn_icon
    ),
    INSECTGLAIVE(
        "Insect Glaive",
        R.drawable.insect_glaive_icon
    ),
    LANCE(
        "Lance",
        R.drawable.lance_icon
    ),
    LIGHTBOWGUN(
        "Light Bowgun",
        R.drawable.light_bowgun_icon
    ),
    LONGSWORD(
        "Long Sword",
        R.drawable.long_sword_icon
    ),
    SWITCHAXE(
        "Switch Ax",
        R.drawable.switch_axe_icon
    ),
    SWORDSHIELD(
        "Sword and Shield",
        R.drawable.sword_shield_icon
    )
}