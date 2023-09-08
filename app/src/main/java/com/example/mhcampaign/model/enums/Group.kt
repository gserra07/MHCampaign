package com.example.mhcampaign.model.enums

enum class Group(
        var groupName: String,
        var indexOrder: Int
) {
    COMMONS("Common",0),
    OTHER("Other Resources",1),
    MONSTER_PART("Monster Parts",2)
}
