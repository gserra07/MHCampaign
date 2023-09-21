package com.example.mhcampaign.ui.huntersView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.model.HunterDataModel

class HuntersViewModel(
    hunterListIn: MutableList<HunterDataModel> = mutableListOf()
) {
    private val _hunterList = MutableLiveData(hunterListIn)
    val hunterList: LiveData<MutableList<HunterDataModel>> = _hunterList

    private val _selectedHunter = MutableLiveData<HunterDataModel?>()
    val selectedHunter: LiveData<HunterDataModel?> = _selectedHunter

    private val _hunterDialogVisibility = MutableLiveData(false)
    val hunterDialogVisibility: LiveData<Boolean> = _hunterDialogVisibility

    private val _inventoryDialogVisibility = MutableLiveData(false)
    val inventoryDialogVisibility: LiveData<Boolean> = _inventoryDialogVisibility


    fun onSelectedHunterChange(hunterData: HunterDataModel?) {
        _selectedHunter.value = hunterData
    }

    fun onHunterDialogVisibilityChange(visibility: Boolean) {
        _hunterDialogVisibility.value = visibility
    }

    fun onInventoryDialogVisibilityChange(visibility: Boolean) {
        _inventoryDialogVisibility.value = visibility
    }

    fun addHunter(item: HunterDataModel) {
        _hunterList.value?.add(item)
    }

}