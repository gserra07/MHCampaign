package com.example.mhcampaign.model.enums

import com.example.mhcampaign.R

enum class PartType(
    val typeName: String,
    val icon: Int,
    var indexOrder: Int
) {

    ORE("Ore", R.drawable.ore_white_icon,0),
    BONE("Bone", R.drawable.bone_white_icon,1),
    HIDE("Hide", R.drawable.hide_white_icon,2),

    SAC("Sac", R.drawable.sac_white_icon,3),
    SCALE("Scale", R.drawable.scale_white_icon,4),
    CLAW("Claw", R.drawable.claw_white_icon,5),
    HORN("Horns", R.drawable.claw_white_icon,6),
    GEM("Gem", R.drawable.gem_white_icon,7),

    MONSTER_PART("Monster Part", R.drawable.part_monster_grey_icon,8),

    //MONSTERS
    //ANCIENT FOREST
    GREAT_JAGRAS("Great Jagras", R.drawable.monster_jagras_icon,9),
    TOBI_KADACHI("Tobi Kadachi", R.drawable.monster_tobi_kadachi_icon,10),
    ANJANATH("Anjanath", R.drawable.monster_anjanath_icon,11),
    RATHALOS("Rathalos", R.drawable.monster_rathalos_icon,12),
    AZURE_RATHALOS("Azure Rathalos", R.drawable.monster_azure_rathalos_icon,13),

    //WILDSPIRE WASTE
    BARROTH("Barroth", R.drawable.monster_barroth_icon,14),
    PUKEI_PUKEI("Pukei-pukei", R.drawable.monster_pukei_pukei_icon,15),
    JYURATODUS("Jyuratodus", R.drawable.monster_jyuratodus_icon,16),
    DIABLOS("Diablos", R.drawable.monster_diablos_icon,17),
    BLACK_DIABLOS("Black Diablos", R.drawable.monster_black_diablos_icon,18),

    //EXPANSIONS
    KULU_YA_KU("Kulu-ya-ku", R.drawable.monster_kulu_yaku_icon,19),
    KUSHALA_DAORA("Kushala Daora", R.drawable.monster_kushala_icon,20),
    TEOSTRA("Teostra", R.drawable.monster_teostra_icon,21),
    NERGIGANTE("Nergigante", R.drawable.monster_nergigante_icon,22),

}