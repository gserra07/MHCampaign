package com.example.mhcampaign.model.enums

import com.example.mhcampaign.R
import com.example.mhcampaign.model.enums.ExpasionType.*

enum class HunterWeapon(
        val weaponName: String,
        val icon: Int,
        val expasionType: ExpasionType
) {
    GREAT_SWORD(
            "Great Sword",
            R.drawable.weapon_great_sword_icon,
            ANCIENT_FOREST
    ),
    LONG_SWORD(
            "Long Sword",
            R.drawable.weapon_long_sword_icon,
            HUNTER_ARSENAL
    ),
    SWORD_AND_SHIELD(
            "Sword and Shield",
            R.drawable.weapon_sword_shield_icon,
            ANCIENT_FOREST
    ),
    DUAL_BLADES(
            "Dual Blades",
            R.drawable.weapon_dual_blades_icon,
            ANCIENT_FOREST
    ),
    HAMMER(
            "Hammer",
            R.drawable.weapon_hammer_icon,
            HUNTER_ARSENAL
    ),
    HUNTING_HORN(
            "Hunting Horn",
            R.drawable.weapon_hunting_horn_icon,
            HUNTER_ARSENAL
    ),
    LANCE(
            "Lance",
            R.drawable.weapon_lance_icon,
            HUNTER_ARSENAL
    ),
    GUNLANCE(
            "Gunlance",
            R.drawable.weapon_gunlance_icon,
            HUNTER_ARSENAL
    ),
    SWITCH_AXE(
            "Switch Axe",
            R.drawable.weapon_switch_axe_icon,
            WILDSPIRE_WASTE
    ),
    CHARGE_BLADE(
            "Charge Blade",
            R.drawable.weapon_charge_blade_icon,
            WILDSPIRE_WASTE
    ),
    INSECT_GLAIVE(
            "Insect Glaive",
            R.drawable.weapon_insect_glaive_icon,
            WILDSPIRE_WASTE
    ),
    LIGHT_BOWGUN(
            "Light Bowgun",
            R.drawable.weapon_light_bowgun_icon,
            HUNTER_ARSENAL
    ),
    HEAVY_BOWGUN(
            "Heavy Bowgun",
            R.drawable.weapon_heavy_bowgun_icon,
            WILDSPIRE_WASTE
    ),
    BOW(
            "Bow",
            R.drawable.weapon_bow_icon,
            ANCIENT_FOREST
    )
}