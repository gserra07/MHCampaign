package com.example.mhcampaign.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CounterViewModel(
    amount: Int = 0,
    private var maxLimit: Int? = null,
    private var minLimit: Int? = null,
) {
    private val _count: MutableLiveData<Int> = MutableLiveData<Int>(amount)
    val count: LiveData<Int> = _count

    fun add() {
        if ((maxLimit != null && _count.value!! < maxLimit!!) || maxLimit == null)
            _count.value = _count.value!! + 1
    }

    fun subtract() {
        if ((minLimit != null && _count.value!! > minLimit!!) || minLimit == null)
            _count.value = _count.value!! - 1
    }
}