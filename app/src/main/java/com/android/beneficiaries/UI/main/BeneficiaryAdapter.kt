package com.android.beneficiaries.UI.main

import Beneficiary
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.beneficiaries.R

class BeneficiaryAdapter(
    private val beneficiaries: List<Beneficiary>,
    private val onClick: (Beneficiary) -> Unit
) : RecyclerView.Adapter<BeneficiaryAdapter.BeneficiaryViewHolder>() {

    class BeneficiaryViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        val textName: TextView = TextView(layout.context).apply {
            textSize = 18f
            setPadding(0, 0, 0, 8)
        }
        val textBeneType: TextView = TextView(layout.context).apply {
            textSize = 16f
            setPadding(0, 0, 0, 8)
        }
        val textDesignation: TextView = TextView(layout.context).apply {
            textSize = 16f
        }


        init {
            var params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0,10,0,0)
            layout.apply {
                layoutParams = params
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.WHITE)
                setPadding(16, 16, 16, 16)
                addView(textName)
                addView(textBeneType)
                addView(textDesignation)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {
        val layout = LinearLayout(parent.context)
        return BeneficiaryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        val beneficiary = beneficiaries[position]
        holder.textName.text = "${beneficiary.firstName} ${beneficiary.lastName}"
        holder.textName.setTextColor(ContextCompat.getColor(holder.layout.context, R.color.primaryDarkColor))

        holder.textBeneType.text = beneficiary.beneType
        holder.textDesignation.text = beneficiary.getDesignation()

        holder.layout.setOnClickListener {
            onClick(beneficiary)
        }
    }

    override fun getItemCount() = beneficiaries.size
}
