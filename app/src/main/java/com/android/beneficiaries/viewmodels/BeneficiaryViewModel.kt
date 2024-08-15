package com.example.app.viewmodel

import Beneficiary
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.BeneficiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeneficiaryViewModel : ViewModel() {

    private val repository = BeneficiaryRepository()

    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()
    val beneficiaries: LiveData<List<Beneficiary>> get() = _beneficiaries

    fun loadBeneficiaries(jsonString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getBeneficiaries(jsonString)
            _beneficiaries.postValue(data)
        }
    }
}
