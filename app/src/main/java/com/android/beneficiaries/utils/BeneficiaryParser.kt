// BeneficiaryParser.kt
package com.android.beneficiaries.utils

import Beneficiary
import BeneficiaryAddress
import org.json.JSONArray

/**
 * Utility class to parse JSON data into a list of Beneficiary objects
 */
object BeneficiaryParser {

    /**
     * Parses the JSON string into a list of Beneficiary objects
     * @param jsonString The JSON string to be parsed
     * @return List of Beneficiary objects
     */
    fun parseBeneficiaryData(jsonString: String): List<Beneficiary> {
        val beneficiaries = mutableListOf<Beneficiary>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            // Parse the address object
            val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
            val address = BeneficiaryAddress(
                firstLineMailing = addressObject.getString("firstLineMailing"),
                scndLineMailing = addressObject.optString("scndLineMailing", null),
                city = addressObject.getString("city"),
                zipCode = addressObject.getString("zipCode"),
                stateCode = addressObject.getString("stateCode"),
                country = addressObject.getString("country")
            )

            // Parse the Beneficiary object
            val beneficiary = Beneficiary(
                firstName = jsonObject.getString("firstName"),
                lastName = jsonObject.getString("lastName"),
                middleName = jsonObject.optString("middleName", null),
                designationCode = jsonObject.getString("designationCode"),
                beneType = jsonObject.getString("beneType"),
                socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                dateOfBirth = jsonObject.getString("dateOfBirth"),
                phoneNumber = jsonObject.getString("phoneNumber"),
                beneficiaryAddress = address
            )

            beneficiaries.add(beneficiary)
        }

        return beneficiaries
    }
}
