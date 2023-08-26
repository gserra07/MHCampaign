package com.example.mhcampaign.model

import com.example.mhcampaign.R

data class PartModel(var name: PartItem, var count: Int = 0) {
    fun count(count: Int): PartModel {
        this.count = count
        return this
    }
}

enum class PartType(val icon: Int) {
    ORE(R.drawable.ore_icon),
    BONE(R.drawable.bone_icon),
    MONSTERPART(R.drawable.monster_skin_icon),
    SAC(R.drawable.sac_icon),
    SCALE(R.drawable.scale_icon),
}

enum class PartItem(var partName: String, var type: PartType) {

    //ORES
    CARBALITE("Carbalite Ore", PartType.ORE),
    MACHALITE("Machalite Ore", PartType.ORE),
    DRAGONITE("Dragonite Ore", PartType.ORE),
    FUCIUM("Fucium Ore", PartType.ORE),
    DRAGONVEIN_CRYSTAL("Dragonvein Crystal", PartType.ORE),
    CORAL_CRYSTAL("Coral Crystal", PartType.ORE),
    EARTH_CRYSTAL("Earth Crystal", PartType.ORE),
    NOVACRYSTAL("Novacrystal", PartType.ORE),

    //BONE
    QUALITY_BONE("Quality Bone", PartType.BONE),
    SMALL_BONE("Monster Bone Small", PartType.BONE),
    MEDIUM_BONE("Monster Bone Medium", PartType.BONE),
    LARGE_BONE("Monster Bone Large", PartType.BONE),
    KEENBONE("Monster Keenbone", PartType.BONE),
    HARDBONE("Monstter Hardbone", PartType.BONE),
    ANCIENT_BONE("Ancient Bone", PartType.BONE),
    BOULDER_BONE("Boulder Bone", PartType.BONE),

    //OTHER
    WINGDRAKE_HIDE("Wingdrake hide", PartType.MONSTERPART),
    WARM_PELT("Warm Pelt", PartType.MONSTERPART),
    SHARP_CLAW("Sharp Claw", PartType.MONSTERPART),
    GAJAU_SCALE("Gajau Scale", PartType.SCALE),
    ELECTRODE("Electrode", PartType.MONSTERPART),
    FERTILE_MUD("Fertile Mud", PartType.MONSTERPART),

    //GEMS
    WYVERN_GEM("Wyvern Gem", PartType.MONSTERPART),
    BIRD_WYVERN_GEM("Bird Wyvern Gem", PartType.MONSTERPART),

    //DIABLOS
    BLOS_MEDULLA("Blos Medulla", PartType.MONSTERPART),
    MAJESTIC_HORN("Majestic Horn", PartType.MONSTERPART),
    TWISTED_HORN("Twisted Horn", PartType.MONSTERPART),
    BLACK_SPIRAL_HORN("Black Spiral Horn", PartType.MONSTERPART),

    //FIRE DRAGON
    FIRECELL_STONE("Firecell Stone", PartType.ORE),
    FIRE_DRAGON_SCALE("Fire Dragon Scale", PartType.SCALE),
    IMMORTAL_DRAGONSCALE("Immortal Dragonscale", PartType.SCALE),

    //ELDER_DRAGON
    ELDER_DRAGON_BLOOD("Elder Dragon Blood", PartType.MONSTERPART),
    ELDER_DRAGON_BONE("Elder Dragon Bone", PartType.BONE),


    //SAC
    AQUA_SAC("Aqua Sac", PartType.SAC),
    ELECTRO_SAC("Electro Sac", PartType.SAC),
    FLAME_SAC("Flame Sac", PartType.SAC),
    POISON_SAC("Poison Sac", PartType.SAC),
    THUNDER_SAC("Thunder Sac", PartType.SAC),
    TOXIN_SAC("Toxin Sac", PartType.SAC),

    //ANCIENT FOREST
    //GREAT JAGRAS
    GREAT_JAGRAS_CLAW("Great Jagras Claw", PartType.MONSTERPART),
    GREAT_JAGRAS_HIDE("Great Jagras Hide", PartType.MONSTERPART),
    GREAT_JAGRAS_MANE("Great Jagras Mane", PartType.MONSTERPART),
    GREAT_JAGRAS_SCALE("Great Jagras Scale", PartType.MONSTERPART),

    //TOBI KADACHI
    TOBI_KADACHI_CLAW("Kadachi Claw", PartType.MONSTERPART),
    TOBI_KADACHI_ELECTRODE("Kadachi Electrode", PartType.MONSTERPART),
    TOBI_KADACHI_MEMBRANE("Kadachi Membrane", PartType.MONSTERPART),
    TOBI_KADACHI_PELT("Kadachi Pelt", PartType.MONSTERPART),
    TOBI_KADACHI_SCALE("Kadachi Scale", PartType.MONSTERPART),

    //ANJANATH
    ANJANATH_FANG("Anjanath Fang", PartType.MONSTERPART),
    ANJANATH_NOSEBONE("Anjanath", PartType.MONSTERPART),
    ANJANATH_PELT("Anjanath Pelt", PartType.MONSTERPART),
    ANJANATH_SCALE("Anjanath Scale", PartType.MONSTERPART),
    ANJANATH_TAIL("Anjanath Tail", PartType.MONSTERPART),

    //RATHALOS
    RATHALOS_CARAPACE("Rathalos Carapace", PartType.MONSTERPART),
    RATHALOS_MARROW("Rathalos Marrow", PartType.MONSTERPART),
    RATHALOS_MEDULLA("Rathalos Medulla", PartType.MONSTERPART),
    RATHALOS_PLATE("Rathalos Plate", PartType.MONSTERPART),
    RATHALOS_SCALE("Rathalos Scale", PartType.MONSTERPART),
    RATHALOS_SHELL("Rathalos Shell", PartType.MONSTERPART),
    RATHALOS_TAIL("Rathalos Tail", PartType.MONSTERPART),
    RATHALOS_WEBBING("Rathalos Webbing", PartType.MONSTERPART),
    RATHALOS_WING("Rathalos Wing", PartType.MONSTERPART),
    RATHALOS_WINGTALON("Rathalos Wingtalon", PartType.MONSTERPART),

    //AZURE RATHALOS
    AZURE_RATH_CARAPACE("Azure Rath Carapace", PartType.MONSTERPART),
    AZURE_RATH_MARROW("Azure Rath Marrow", PartType.MONSTERPART),
    AZURE_RATH_PLATE("Azure Rath Plate", PartType.MONSTERPART),
    AZURE_RATH_SCALE("Azure Rath Scale", PartType.MONSTERPART),
    AZURE_RATH_TAIL("Azure Rath Tail", PartType.MONSTERPART),
    AZURE_RATH_WING("Azure Rath Wing", PartType.MONSTERPART),
    AZURE_RATH_WINGTALON("Azure Rath Wingtalon", PartType.MONSTERPART),

    //WIDLSPIRE WASTE
    //BARROTH
    BARROTH_CARAPACE("Barroth Carapace", PartType.MONSTERPART),
    BARROTH_CLAW("Barroth Claw", PartType.MONSTERPART),
    BARROTH_RIDGE("Barroth Ridge", PartType.MONSTERPART),
    BARROTH_SHELL("Barroth Shell", PartType.MONSTERPART),

    //PUKEI-PUKEI
    PUKEI_CARAPACE("Pukei Carapace", PartType.MONSTERPART),
    PUKEI_QUILL("Pukei Quill", PartType.MONSTERPART),
    PUKEI_SCALE("Pukei Scale", PartType.MONSTERPART),
    PUKEI_SAC("Pukei Sac", PartType.MONSTERPART),
    PUKEI_TAIL("Pukei Tail", PartType.MONSTERPART),
    PUKEI_WING("Pukei Wing", PartType.MONSTERPART),

    //JYURATODUS
    JYURATODUS_CARAPACE("Jyuratodus Carapace", PartType.MONSTERPART),
    JYURATODUS_FANG("Jyuratodus Fang", PartType.MONSTERPART),
    JYURATODUS_FIN("Jyuratodus Fin", PartType.MONSTERPART),
    JYURATODUS_SCALE("Jyuratodus Scale", PartType.MONSTERPART),
    JYURATODUS_SHELL("Jyuratodus Shell", PartType.MONSTERPART),

    //DIABLOS
    DIABLOS_CARAPACE("Diablos Carapace", PartType.MONSTERPART),
    DIABLOS_FANG("Diablos Fang", PartType.MONSTERPART),
    DIABLOS_RIDGE("Diablos Ridge", PartType.MONSTERPART),
    DIABLOS_SHELL("Diablos Shell", PartType.MONSTERPART),

    //BLACK DIABLOS
    BLACK_DIABLOS_CARAPACE("Black Diablos Carapace", PartType.MONSTERPART),
    BLACK_DIABLOS_RIDGE("Black Diablos Ridge", PartType.MONSTERPART),

    //EXPANSIONS
    //KULU-YA-KU
    KULU_YA_KU_BEAK("Kulu-Ya-Ku Beak", PartType.MONSTERPART),
    KULU_YA_KU_HIDE("Kulu-Ya-Ku Hide", PartType.MONSTERPART),
    KULU_YA_KU_PLUME("Kulu-Ya-Ku Plume", PartType.MONSTERPART),
    KULU_YA_KU_SCALE("Kulu-Ya-Ku Scale", PartType.MONSTERPART),

    //ELDER_DRAGONS
    //KUSHALA DAORA
    DAROA_CARAPACE("Daora Carapace", PartType.MONSTERPART),
    DAROA_CLAW("Daora Claw", PartType.MONSTERPART),
    DAROA_DRAGON_SCALE("Daora Dragon Scale", PartType.MONSTERPART),
    DAROA_GEM("Daora Gem", PartType.MONSTERPART),
    DAROA_HORN("Daora Horn", PartType.MONSTERPART),
    DAROA_TAIL("Daora Tail", PartType.MONSTERPART),
    DAROA_WEBBING("Daora Webbing", PartType.MONSTERPART),

    //TEOSTRA
    TEOSTRA_CARAPACE("Teostra Carapace", PartType.MONSTERPART),
    TEOSTRA_CLAW("Teostra Claw", PartType.MONSTERPART),
    TEOSTRA_GEM("Teostra Gem", PartType.MONSTERPART),
    TEOSTRA_HORN("Teostra Horn", PartType.MONSTERPART),
    TEOSTRA_MANE("Teostra Mane", PartType.MONSTERPART),
    TEOSTRA_POWDER("Teostra Powder", PartType.MONSTERPART),
    TEOSTRA_TAIL("Teostra Tail", PartType.MONSTERPART),
    TEOSTRA_WEBBING("Teostra Webbing", PartType.MONSTERPART),

    //NERGIGANTE
    NERGIGANTE_CARAPACE("Nerg Carapace", PartType.MONSTERPART),
    NERGIGANTE_GEM("Nerg Gem", PartType.MONSTERPART),
    NERGIGANTE_HORN("Nerg Horn", PartType.MONSTERPART),
    NERGIGANTE_REGROWTH_PLATE("Nerg Regrowth Plate", PartType.MONSTERPART),
    NERGIGANTE_TAIL("Nerg Tail", PartType.MONSTERPART),
    NERGIGANTE_TALON("Nerg Talon", PartType.MONSTERPART),

}
