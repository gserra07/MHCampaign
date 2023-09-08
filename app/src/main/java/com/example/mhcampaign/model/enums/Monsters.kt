package com.example.mhcampaign.model.enums

import com.example.mhcampaign.R
import com.example.mhcampaign.model.enums.ExpasionType.*

enum class Monster(
    val monsterName: String,
    val icon: Int,
    val isFourStars: Boolean = false,
    val isElderDragon: Boolean = false,
    val expansionType: ExpasionType,
    val index: Int
) {
    //ANCIENT FOREST
    GREAT_JAGRAS(
        monsterName = "Great Jagras",
        icon = R.drawable.monster_jagras_icon,
        expansionType = ANCIENT_FOREST,
        index = 0
    ),
    TOBI_KADACHI(
        monsterName = "Tobi Kadachi",
        icon = R.drawable.monster_tobi_kadachi_icon,
        expansionType = ANCIENT_FOREST,
        index = 1
    ),
    ANJANATH(
        monsterName = "Anjanath",
        icon = R.drawable.monster_anjanath_icon,
        expansionType = ANCIENT_FOREST,
        index = 2
    ),
    RATHALOS(
        monsterName = "Rathalos",
        icon = R.drawable.monster_rathalos_icon,
        true,
        expansionType = ANCIENT_FOREST,
        index = 3
    ),
    AZURE_RATHALOS(
        monsterName = "Azure Rathalos",
        icon = R.drawable.monster_azure_rathalos_icon,
        isFourStars = true,
        expansionType = ANCIENT_FOREST,
        index = 4
    ),

    //WILDSPIRE WASTE
    BARROTH(
        monsterName = "Barroth",
        icon = R.drawable.monster_barroth_icon,
        expansionType = WILDSPIRE_WASTE,
        index = 5
    ),
    PUKEI_PUKEI(
        monsterName = "Pukei-pukei",
        icon = R.drawable.monster_pukei_pukei_icon,
        expansionType = WILDSPIRE_WASTE,
        index = 6
    ),
    JYURATODUS(
        monsterName = "Jyuratodus",
        icon = R.drawable.monster_jyuratodus_icon,
        expansionType = WILDSPIRE_WASTE,
        index = 7
    ),
    DIABLOS(
        monsterName = "Diablos",
        icon = R.drawable.monster_diablos_icon,
        expansionType = WILDSPIRE_WASTE,
        isFourStars = true,
        index = 8
    ),
    BLACK_DIABLOS(
        monsterName = "Black Diablos",
        icon = R.drawable.monster_black_diablos_icon,
        expansionType = WILDSPIRE_WASTE,
        isFourStars = true,
        index = 9
    ),

    //EXPANSIONS
    KULU_YA_KU(
        monsterName = "Kulu-ya-ku",
        icon = R.drawable.monster_kulu_yaku_icon,
        expansionType = ExpasionType.KULU_YA_KU,
        index = 10
    ),
    KUSHALA_DAORA(
        monsterName = "Kushala Daora",
        icon = R.drawable.monster_kushala_icon,
        isElderDragon = true,
        expansionType = ExpasionType.KUSHALA_DAORA,
        index = 11
    ),
    TEOSTRA(
        monsterName = "Teostra",
        icon = R.drawable.monster_teostra_icon,
        isElderDragon = true,
        expansionType = ExpasionType.TEOSTRA,
        index = 12
    ),
    NERGIGANTE(
        monsterName = "Nergigante",
        icon = R.drawable.monster_nergigante_icon,
        isElderDragon = true,
        expansionType = ExpasionType.NERGIGANTE,
        index = 13
    )
}