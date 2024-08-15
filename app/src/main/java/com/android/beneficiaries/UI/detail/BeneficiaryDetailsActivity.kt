package com.android.beneficiaries.UI.detail

import Beneficiary
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.beneficiaries.R

class BeneficiaryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the root LinearLayout
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(ContextCompat.getColor(this@BeneficiaryDetailsActivity, R.color.backgroundColor))
        }
        setContentView(rootLayout)

        // Get the Beneficiary object passed from the MainActivity
        val beneficiary = intent.getParcelableExtra<Beneficiary>("beneficiary")

        // Dynamically create and add UI elements based on Beneficiary data
        beneficiary?.let {
            addBeneficiaryDetailView(rootLayout, "Full Name", "${it.firstName} ${it.middleName?.let { "$it " } ?: ""}${it.lastName}")
            addBeneficiaryDetailView(rootLayout, "SSN", it.socialSecurityNumber)
            addBeneficiaryDetailView(rootLayout, "Date of Birth", it.getFormattedDateOfBirth())
            addBeneficiaryDetailView(rootLayout, "Phone Number", it.phoneNumber)
            addBeneficiaryDetailView(rootLayout, "Address", it.getFullAddress())
        }
    }

    // Helper method to dynamically create and add TextViews to the layout
    private fun addBeneficiaryDetailView(parent: LinearLayout, label: String, value: String) {
        // Create a LinearLayout to hold the label and value
        val detailLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }
            setBackgroundColor(ContextCompat.getColor(this@BeneficiaryDetailsActivity, R.color.cardBackgroundColor))
        }

        // Create and add the label TextView
        val labelTextView = TextView(this).apply {
            text = label
            textSize = 14f
            setTextColor(ContextCompat.getColor(this@BeneficiaryDetailsActivity, R.color.primaryDarkColor))
        }
        detailLayout.addView(labelTextView)

        // Create and add the value TextView
        val valueTextView = TextView(this).apply {
            text = value
            textSize = 16f
            setTextColor(ContextCompat.getColor(this@BeneficiaryDetailsActivity, R.color.textColorSecondary))
        }
        detailLayout.addView(valueTextView)

        // Add the detail layout to the parent layout
        parent.addView(detailLayout)
    }
}
