package com.example.mhcampaign.model.enums

import com.example.mhcampaign.R

enum class PartItem(var partName: String, var partGroup: Group, var type: PartType, var partIcon: Int) {

    //COMMON
    //ORES
    CARBALITE("Carbalite Ore", Group.COMMONS, PartType.ORE, R.drawable.ore_purple_icon),
    MACHALITE("Machalite Ore", Group.COMMONS, PartType.ORE, R.drawable.ore_blue_icon),
    DRAGONITE("Dragonite Ore", Group.COMMONS, PartType.ORE, R.drawable.ore_green_icon),
    FUCIUM("Fucium Ore", Group.COMMONS, PartType.ORE, R.drawable.ore_pink_icon),
    DRAGONVEIN_CRYSTAL("Dragonvein Crystal", Group.COMMONS, PartType.ORE, R.drawable.ore_red_icon),

    //BONE
    QUALITY_BONE("Quality Bone", Group.COMMONS, PartType.BONE, R.drawable.bone_white_icon),
    SMALL_BONE("Monster Bone Small", Group.COMMONS, PartType.BONE, R.drawable.bone_yellow_icon),
    MEDIUM_BONE("Monster Bone Medium", Group.COMMONS, PartType.BONE, R.drawable.bone_yellow_icon),
    LARGE_BONE("Monster Bone Large", Group.COMMONS, PartType.BONE, R.drawable.bone_yellow_icon),
    KEENBONE("Monster Keenbone", Group.COMMONS, PartType.BONE, R.drawable.bone_pink_icon),
    HARDBONE("Monstter Hardbone", Group.COMMONS, PartType.BONE, R.drawable.bone_orange_icon),
    ANCIENT_BONE("Ancient Bone", Group.COMMONS, PartType.BONE, R.drawable.bone_grey_icon),
    BOULDER_BONE("Boulder Bone", Group.COMMONS, PartType.BONE, R.drawable.bone_grey_icon),

    //HIDE
    WINGDRAKE_HIDE("Wingdrake hide", Group.COMMONS, PartType.HIDE, R.drawable.hide_green_icon),

    //OTHER
    WARM_PELT("Warm Pelt", Group.OTHER, PartType.HIDE, R.drawable.hide_orange_icon),
    SHARP_CLAW("Sharp Claw", Group.OTHER,  PartType.CLAW, R.drawable.claw_white_icon),
    GAJAU_SCALE("Gajau Scale", Group.OTHER, PartType.SCALE, R.drawable.scale_white_icon),
    ELECTRODE("Electrode", Group.OTHER, PartType.SAC, R.drawable.sac_yellow_icon),
    FERTILE_MUD("Fertile Mud", Group.OTHER, PartType.MONSTER_PART, R.drawable.mud_icon),

    //ORES
    CORAL_CRYSTAL("Coral Crystal", Group.OTHER, PartType.ORE, R.drawable.ore_pink_icon),
    EARTH_CRYSTAL("Earth Crystal", Group.OTHER, PartType.ORE, R.drawable.ore_white_icon),
    NOVACRYSTAL("Novacrystal", Group.OTHER, PartType.ORE, R.drawable.ore_white_icon),


    //GEMS
    WYVERN_GEM("Wyvern Gem", Group.OTHER, PartType.GEM, R.drawable.gem_white_icon),
    BIRD_WYVERN_GEM("Bird Wyvern Gem",  Group.OTHER, PartType.GEM, R.drawable.gem_blue_icon),

    //DIABLOS
    BLOS_MEDULLA("Blos Medulla", Group.OTHER, PartType.MONSTER_PART, R.drawable.part_monster_grey_icon),
    MAJESTIC_HORN("Majestic Horn", Group.OTHER, PartType.HORN, R.drawable.claw_white_icon),
    TWISTED_HORN("Twisted Horn", Group.OTHER, PartType.HORN, R.drawable.claw_yellow_icon),
    BLACK_SPIRAL_HORN("Black Spiral Horn", Group.OTHER, PartType.HORN, R.drawable.claw_grey_icon),

    //FIRE DRAGON
    FIRECELL_STONE("Firecell Stone", Group.OTHER, PartType.ORE, R.drawable.ore_orange_icon),
    FIRE_DRAGON_SCALE("Fire Dragon Scale", Group.OTHER, PartType.SCALE, R.drawable.scale_red_icon),
    IMMORTAL_DRAGONSCALE("Immortal Dragonscale", Group.OTHER, PartType.SCALE, R.drawable.scale_red_icon),

    //ELDER_DRAGON
    ELDER_DRAGON_BLOOD("Elder Dragon Blood", Group.OTHER, PartType.MONSTER_PART, R.drawable.dragon_blood_icon),
    ELDER_DRAGON_BONE("Elder Dragon Bone", Group.OTHER, PartType.MONSTER_PART, R.drawable.bone_purple_icon),

    //SAC
    AQUA_SAC("Aqua Sac", Group.OTHER, PartType.SAC, R.drawable.sac_blue_icon),
    ELECTRO_SAC("Electro Sac", Group.OTHER, PartType.SAC, R.drawable.sac_yellow_icon),
    THUNDER_SAC("Thunder Sac", Group.OTHER, PartType.SAC, R.drawable.sac_yellow_icon),
    POISON_SAC("Poison Sac", Group.OTHER, PartType.SAC, R.drawable.sac_purple_icon),
    TOXIN_SAC("Toxin Sac", Group.OTHER, PartType.SAC, R.drawable.sac_purple_icon),
    FLAME_SAC("Flame Sac", Group.OTHER, PartType.SAC, R.drawable.sac_red_icon),
    INFERNO_SAC("Inferno Sac", Group.OTHER, PartType.SAC, R.drawable.sac_red_icon),

    //ANCIENT FOREST
    //GREAT JAGRAS
    GREAT_JAGRAS_CLAW("Great Jagras Claw", Group.MONSTER_PART, PartType.GREAT_JAGRAS, R.drawable.claw_yellow_icon),
    GREAT_JAGRAS_HIDE("Great Jagras Hide", Group.MONSTER_PART, PartType.GREAT_JAGRAS, R.drawable.hide_yellow_icon),
    GREAT_JAGRAS_MANE("Great Jagras Mane", Group.MONSTER_PART, PartType.GREAT_JAGRAS, R.drawable.part_monster_yellow_icon),
    GREAT_JAGRAS_SCALE("Great Jagras Scale", Group.MONSTER_PART, PartType.GREAT_JAGRAS, R.drawable.scale_yellow_icon),

    //TOBI KADACHI
    TOBI_KADACHI_CLAW("Kadachi Claw", Group.MONSTER_PART, PartType.TOBI_KADACHI, R.drawable.claw_blue_icon),
    TOBI_KADACHI_ELECTRODE("Kadachi Electrode", Group.MONSTER_PART, PartType.TOBI_KADACHI, R.drawable.hide_blue_icon),
    TOBI_KADACHI_MEMBRANE("Kadachi Membrane", Group.MONSTER_PART, PartType.TOBI_KADACHI, R.drawable.membrane_icon),
    TOBI_KADACHI_PELT("Kadachi Pelt", Group.MONSTER_PART, PartType.TOBI_KADACHI, R.drawable.hide_blue_icon),
    TOBI_KADACHI_SCALE("Kadachi Scale", Group.MONSTER_PART, PartType.TOBI_KADACHI, R.drawable.scale_blue_icon),

    //ANJANATH
    ANJANATH_FANG("Anjanath Fang", Group.MONSTER_PART, PartType.ANJANATH, R.drawable.claw_pink_icon),
    ANJANATH_NOSEBONE("Anjanath", Group.MONSTER_PART, PartType.ANJANATH, R.drawable.bone_pink_icon),
    ANJANATH_PELT("Anjanath Pelt", Group.MONSTER_PART, PartType.ANJANATH, R.drawable.part_monster_pink_icon),
    ANJANATH_SCALE("Anjanath Scale", Group.MONSTER_PART, PartType.ANJANATH, R.drawable.scale_pink_icon),
    ANJANATH_TAIL("Anjanath Tail", Group.MONSTER_PART, PartType.ANJANATH, R.drawable.part_monster_pink_icon),

    //RATHALOS
    RATHALOS_CARAPACE("Rathalos Carapace", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.part_monster_red_icon),
    RATHALOS_MARROW("Rathalos Marrow", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.part_monster_red_icon),
    RATHALOS_MEDULLA("Rathalos Medulla", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.part_monster_red_icon),
    RATHALOS_PLATE("Rathalos Plate", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.plate_red_icon),
    RATHALOS_SCALE("Rathalos Scale", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.scale_red_icon),
    RATHALOS_SHELL("Rathalos Shell", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.scale_red_icon),
    RATHALOS_TAIL("Rathalos Tail", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.part_monster_red_icon),
    RATHALOS_WEBBING("Rathalos Webbing", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.membrane_red_icon),
    RATHALOS_WING("Rathalos Wing", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.part_monster_red_icon),
    RATHALOS_WINGTALON("Rathalos Wingtalon", Group.MONSTER_PART, PartType.RATHALOS, R.drawable.claw_red_icon),

    //AZURE RATHALOS
    AZURE_RATH_CARAPACE("Azure Rath Carapace", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.part_monster_blue_icon),
    AZURE_RATH_MARROW("Azure Rath Marrow", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.part_monster_blue_icon),
    AZURE_RATH_PLATE("Azure Rath Plate", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.plate_blue_icon),
    AZURE_RATH_SCALE("Azure Rath Scale", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.scale_blue_icon),
    AZURE_RATH_TAIL("Azure Rath Tail", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.part_monster_blue_icon),
    AZURE_RATH_WING("Azure Rath Wing", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.part_monster_blue_icon),
    AZURE_RATH_WINGTALON("Azure Rath Wingtalon", Group.MONSTER_PART, PartType.AZURE_RATHALOS, R.drawable.claw_blue_icon),

    //WIDLSPIRE WASTE
    //BARROTH
    BARROTH_CARAPACE("Barroth Carapace", Group.MONSTER_PART, PartType.BARROTH, R.drawable.part_monster_orange_icon),
    BARROTH_CLAW("Barroth Claw", Group.MONSTER_PART, PartType.BARROTH, R.drawable.claw_orange_icon),
    BARROTH_RIDGE("Barroth Ridge", Group.MONSTER_PART, PartType.BARROTH, R.drawable.part_monster_orange_icon),
    BARROTH_SHELL("Barroth Shell", Group.MONSTER_PART, PartType.BARROTH, R.drawable.part_monster_orange_icon),

    //PUKEI-PUKEI
    PUKEI_CARAPACE("Pukei Carapace", Group.MONSTER_PART, PartType.PUKEI_PUKEI, R.drawable.part_monster_purple_icon),
    PUKEI_QUILL("Pukei Quill", Group.MONSTER_PART, PartType.PUKEI_PUKEI, R.drawable.membrane_purple_icon),
    PUKEI_SCALE("Pukei Scale", Group.MONSTER_PART, PartType.PUKEI_PUKEI, R.drawable.scale_purple_icon),
    PUKEI_SAC("Pukei Sac", Group.MONSTER_PART, PartType.PUKEI_PUKEI, R.drawable.sac_purple_icon),
    PUKEI_TAIL("Pukei Tail", Group.MONSTER_PART, PartType.PUKEI_PUKEI, R.drawable.part_monster_purple_icon),
    PUKEI_WING("Pukei Wing", Group.MONSTER_PART, PartType.PUKEI_PUKEI, R.drawable.part_monster_purple_icon),

    //JYURATODUS
    JYURATODUS_CARAPACE("Jyuratodus Carapace", Group.MONSTER_PART, PartType.JYURATODUS, R.drawable.part_monster_yellow_icon),
    JYURATODUS_FANG("Jyuratodus Fang", Group.MONSTER_PART, PartType.JYURATODUS, R.drawable.claw_yellow_icon),
    JYURATODUS_FIN("Jyuratodus Fin", Group.MONSTER_PART, PartType.JYURATODUS, R.drawable.part_monster_yellow_icon),
    JYURATODUS_SCALE("Jyuratodus Scale", Group.MONSTER_PART, PartType.JYURATODUS, R.drawable.scale_yellow_icon),
    JYURATODUS_SHELL("Jyuratodus Shell", Group.MONSTER_PART, PartType.JYURATODUS, R.drawable.part_monster_yellow_icon),

    //DIABLOS
    DIABLOS_CARAPACE("Diablos Carapace", Group.MONSTER_PART, PartType.DIABLOS, R.drawable.part_monster_orange_icon),
    DIABLOS_FANG("Diablos Fang", Group.MONSTER_PART, PartType.DIABLOS, R.drawable.claw_orange_icon),
    DIABLOS_RIDGE("Diablos Ridge", Group.MONSTER_PART, PartType.DIABLOS, R.drawable.part_monster_orange_icon),
    DIABLOS_SHELL("Diablos Shell", Group.MONSTER_PART, PartType.DIABLOS, R.drawable.part_monster_orange_icon),

    //BLACK DIABLOS
    BLACK_DIABLOS_CARAPACE("Black Diablos Carapace", Group.MONSTER_PART, PartType.BLACK_DIABLOS, R.drawable.part_monster_grey_icon),
    BLACK_DIABLOS_RIDGE("Black Diablos Ridge", Group.MONSTER_PART, PartType.BLACK_DIABLOS, R.drawable.part_monster_grey_icon),

    //EXPANSIONS
    //KULU-YA-KU
    KULU_YA_KU_BEAK("Kulu-Ya-Ku Beak", Group.MONSTER_PART, PartType.KULU_YA_KU, R.drawable.part_monster_orange_icon),
    KULU_YA_KU_HIDE("Kulu-Ya-Ku Hide", Group.MONSTER_PART, PartType.KULU_YA_KU, R.drawable.hide_orange_icon),
    KULU_YA_KU_PLUME("Kulu-Ya-Ku Plume", Group.MONSTER_PART, PartType.KULU_YA_KU, R.drawable.part_monster_orange_icon),
    KULU_YA_KU_SCALE("Kulu-Ya-Ku Scale", Group.MONSTER_PART, PartType.KULU_YA_KU, R.drawable.scale_yellow_icon),

    //ELDER_DRAGONS
    //KUSHALA DAORA
    DAROA_CARAPACE("Daora Carapace", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.part_monster_grey_icon),
    DAROA_CLAW("Daora Claw", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.claw_grey_icon),
    DAROA_DRAGON_SCALE("Daora Dragon Scale", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.scale_grey_icon),
    DAROA_GEM("Daora Gem", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.gem_grey_icon),
    DAROA_HORN("Daora Horn", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.claw_grey_icon),
    DAROA_TAIL("Daora Tail", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.part_monster_grey_icon),
    DAROA_WEBBING("Daora Webbing", Group.MONSTER_PART, PartType.KUSHALA_DAORA, R.drawable.part_monster_grey_icon),

    //TEOSTRA
    TEOSTRA_CARAPACE("Teostra Carapace", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.part_monster_red_icon),
    TEOSTRA_CLAW("Teostra Claw", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.claw_red_icon),
    TEOSTRA_GEM("Teostra Gem", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.gem_red_icon),
    TEOSTRA_HORN("Teostra Horn", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.claw_red_icon),
    TEOSTRA_MANE("Teostra Mane", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.part_monster_red_icon),
    TEOSTRA_POWDER("Teostra Powder", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.sac_red_icon),
    TEOSTRA_TAIL("Teostra Tail", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.part_monster_red_icon),
    TEOSTRA_WEBBING("Teostra Webbing", Group.MONSTER_PART, PartType.TEOSTRA, R.drawable.part_monster_red_icon),

    //NERGIGANTE
    NERGIGANTE_CARAPACE("Nerg Carapace", Group.MONSTER_PART, PartType.NERGIGANTE, R.drawable.part_monster_grey_icon),
    NERGIGANTE_GEM("Nerg Gem", Group.MONSTER_PART, PartType.NERGIGANTE, R.drawable.gem_grey_icon),
    NERGIGANTE_HORN("Nerg Horn", Group.MONSTER_PART, PartType.NERGIGANTE, R.drawable.claw_grey_icon),
    NERGIGANTE_REGROWTH_PLATE("Nerg Regrowth Plate", Group.MONSTER_PART, PartType.NERGIGANTE, R.drawable.plate_grey_icon),
    NERGIGANTE_TAIL("Nerg Tail", Group.MONSTER_PART, PartType.NERGIGANTE, R.drawable.part_monster_grey_icon),
    NERGIGANTE_TALON("Nerg Talon", Group.MONSTER_PART, PartType.NERGIGANTE, R.drawable.part_monster_grey_icon),

}