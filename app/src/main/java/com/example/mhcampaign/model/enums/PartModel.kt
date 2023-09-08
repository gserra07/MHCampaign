package com.example.mhcampaign.model.enums


data class PartModel(
        var name: PartItem,
        var quantity: Int = 1
) {
    fun count(quantity: Int): PartModel {
        this.quantity = quantity
        return this
    }
}
