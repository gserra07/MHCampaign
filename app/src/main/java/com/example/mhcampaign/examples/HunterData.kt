package com.example.mhcampaign.examples

import com.example.mhcampaign.R

class HunterData(var hunterName: String, var hunterWeapon: HunterWeapon) {
    //    lateinit var hunterName: String
//    lateinit var hunterWeapon: HunterWeapon
    var weaponName: String
    var weaponIcon: Int = 0

    init {
        when (hunterWeapon) {

            HunterWeapon.BOW -> {
                weaponName = "Bow"
                weaponIcon = R.drawable.bow_icon
            }

            HunterWeapon.CHARGEBLADE -> {
                weaponName = "Charge Blade"
                weaponIcon = R.drawable.charge_blade_icon
            }

            HunterWeapon.DUALBLADES -> {
                weaponName = "Dual blades"
                weaponIcon = R.drawable.dual_blades_icon
            }

            HunterWeapon.GREATSWORD -> {
                weaponName = "Great Sword"
                weaponIcon = R.drawable.great_sword_icon
            }

            HunterWeapon.GUNLANCE -> {
                weaponName = "Gunlance"
                weaponIcon = R.drawable.gunlance_icon
            }

            HunterWeapon.HAMMER -> {
                weaponName = "Hammer"
                weaponIcon = R.drawable.hammer_icon
            }

            HunterWeapon.HEAVYBOWGUN -> {
                weaponName = "Heavy Bowgun"
                weaponIcon = R.drawable.heavy_bowgun_icon
            }

            HunterWeapon.HUNTINGHORN -> {
                weaponName = "Hunting Horn"
                weaponIcon = R.drawable.hunting_horn_icon
            }

            HunterWeapon.INSECTGLAIVE -> {
                weaponName = "Insect Glaive"
                weaponIcon = R.drawable.insect_glaive_icon
            }

            HunterWeapon.LANCE -> {
                weaponName = "Lance"
                weaponIcon = R.drawable.lance_icon
            }

            HunterWeapon.LIGHTBOWGUN -> {
                weaponName = "Light Bowgun"
                weaponIcon = R.drawable.light_bowgun_icon
            }

            HunterWeapon.LONGSWORD -> {
                weaponName = "Long Sword"
                weaponIcon = R.drawable.long_sword_icon
            }

            HunterWeapon.SWITCHAXE -> {
                weaponName = "Switch Axe"
                weaponIcon = R.drawable.switch_axe_icon
            }

            HunterWeapon.SWORDSHIELD -> {
                weaponName = "Sword and Shield"
                weaponIcon = R.drawable.sword_shield_icon
                CardType.GOLD
            }
        }
    }
}

enum class HunterWeapon(val weaponName: String, icon: Int) {
    BOW(
        "Bow",
        R.drawable.bow_icon
    ),
    CHARGEBLADE(
        "",

    ),
    DUALBLADES(
        "",

        ),
    GREATSWORD(
        "",

        ), GUNLANCE(
        "",

        ), HAMMER(
        "",

        ), HEAVYBOWGUN(
        "",

        ), HUNTINGHORN(
        "",

        ), INSECTGLAIVE(
        "",

        ), LANCE(
        "",

        ), LIGHTBOWGUN(
        "",

        ), LONGSWORD(
        "",

        ), SWITCHAXE(
        "",

        ), SWORDSHIELD(
        "",

        )
}

enum class CardType(val color: String, val icon: Int) {
    SILVER("gray", 1),
    GOLD("gray", 1)
}