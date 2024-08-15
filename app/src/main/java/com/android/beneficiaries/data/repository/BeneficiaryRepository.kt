package com.example.app.data

import Beneficiary
import com.android.beneficiaries.utils.BeneficiaryParser


class BeneficiaryRepository {
  //returns all Beneficiaries list
    fun getBeneficiaries(jsonString: String): List<Beneficiary> {
        return BeneficiaryParser.parseBeneficiaryData(jsonString)
    }
}
