package com.example.mhcampaign.ui.partView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.model.enums.PartModel

class PartViewModel(
    private var partModel: PartModel,
) {

    private val _dataModel: MutableLiveData<PartModel> = MutableLiveData(partModel)
    val dataModel: LiveData<PartModel> = _dataModel

    private val _quantityInit = partModel.quantity
    private val _quantity: MutableLiveData<Int> = MutableLiveData(_quantityInit)
    val quantity: LiveData<Int> = _quantity

    fun add() {
        _quantity.value = _quantity.value?.plus(1) ?: 0
        _dataModel.value?.quantity = _quantity.value!!
    }

    fun subtract() {
        _quantity.value = _quantity.value?.minus(1) ?: 0
        _dataModel.value?.quantity = _quantity.value!!
    }

}