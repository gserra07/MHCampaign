package com.example.mhcampaign.model.enums

import com.example.mhcampaign.R

enum class PartType(
    val typeName: String,
    val icon: Int
) {

    ORE("Ore", R.drawable.ore_white_icon),
    BONE("Bone", R.drawable.bone_white_icon),
    HIDE("Hide", R.drawable.hide_white_icon),

    SAC("Sac", R.drawable.sac_white_icon),
    SCALE("Scale", R.drawable.scale_white_icon),
    CLAW("Claw", R.drawable.claw_white_icon),
    HORN("Horns", R.drawable.claw_white_icon),
    GEM("Gem", R.drawable.gem_white_icon),

    MONSTER_PART("Monster Part", R.drawable.part_monster_grey_icon),

    //MONSTERS
    //ANCIENT FOREST
    GREAT_JAGRAS("Great Jagras", R.drawable.monster_jagras_icon),
    TOBI_KADACHI("Tobi Kadachi", R.drawable.monster_tobi_kadachi_icon),
    ANJANATH("Anjanath", R.drawable.monster_anjanath_icon),
    RATHALOS("Rathalos", R.drawable.monster_rathalos_icon),
    AZURE_RATHALOS("Azure Rathalos", R.drawable.monster_azure_rathalos_icon),

    //WILDSPIRE WASTE
    BARROTH("Barroth", R.drawable.monster_barroth_icon),
    PUKEI_PUKEI("Pukei-pukei", R.drawable.monster_pukei_pukei_icon),
    JYURATODUS("Jyuratodus", R.drawable.monster_jyuratodus_icon),
    DIABLOS("Diablos", R.drawable.monster_diablos_icon),
    BLACK_DIABLOS("Black Diablos", R.drawable.monster_black_diablos_icon),

    //EXPANSIONS
    KULU_YA_KU("Kulu-ya-ku", R.drawable.part_monster_grey_icon),
    KUSHALA_DAORA("Kushala Daora", R.drawable.monster_kushala_icon),
    TEOSTRA("Teostra", R.drawable.monster_teostra_icon),
    NERGIGANTE("Nergigante", R.drawable.monster_nergigante_icon),

}