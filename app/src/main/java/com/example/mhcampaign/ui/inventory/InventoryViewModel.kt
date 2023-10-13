package com.example.mhcampaign.ui.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.enums.PartModel

class InventoryViewModel(
    private var hunterDataModel: HunterDataModel?,
    private var visibility: Boolean,
) {
    private val _dataModel: MutableLiveData<HunterDataModel> = MutableLiveData(hunterDataModel)
    val dataModel: LiveData<HunterDataModel> = _dataModel

    private val _parentVisibility: MutableLiveData<Boolean> = MutableLiveData(visibility)
    val parentVisibility: LiveData<Boolean> = _parentVisibility

    private val _childVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val childVisibility: LiveData<Boolean> = _childVisibility

    fun setHunter(hunterDataModel: HunterDataModel){
        _dataModel.value= hunterDataModel
    }

    fun add(partModel: PartModel) {
        _dataModel.value?.inventory?.add(partModel)
    }

    fun onParentVisibilityChange(visibility: Boolean) {
        _parentVisibility.value = visibility
    }
    fun onChildVisibilityChange(visibility: Boolean) {
        _childVisibility.value = visibility
    }
}