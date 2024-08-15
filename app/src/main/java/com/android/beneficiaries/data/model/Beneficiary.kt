import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Data class representing the Beneficiary's address
@Parcelize
data class BeneficiaryAddress(
    val firstLineMailing: String?, // First line of the mailing address
    val scndLineMailing: String?,  // Second line of the mailing address (optional)
    val city: String,              // City of the mailing address
    val zipCode: String,           // Zip code of the mailing address
    val stateCode: String,         // State code of the mailing address
    val country: String            // Country of the mailing address
) : Parcelable

// Data class representing a Beneficiary
@Parcelize
data class Beneficiary(
    val firstName: String,          // Beneficiary's first name
    val lastName: String,           // Beneficiary's last name
    val middleName: String?,        // Beneficiary's middle name (optional)
    val designationCode: String,    // Designation code ("P" for Primary, "C" for Contingent)
    val beneType: String,           // Type of beneficiary (e.g., "Child", "Spouse")
    val socialSecurityNumber: String, // Social Security Number (masked)
    val dateOfBirth: String,        // Date of birth in the format "MMddyyyy"
    val phoneNumber: String,        // Phone number of the beneficiary
    val beneficiaryAddress: BeneficiaryAddress // Beneficiary's address
) : Parcelable {

    // Method to translate designation code to a full designation name
    fun getDesignation(): String {
        return when (designationCode) {
            "P" -> "Primary"
            "C" -> "Contingent"
            else -> "Unknown"
        }
    }

    // Method to format the date of birth to "MM/dd/yyyy"
    fun getFormattedDateOfBirth(): String {
        return "${dateOfBirth.substring(0, 2)}/${dateOfBirth.substring(2, 4)}/${dateOfBirth.substring(4)}"
    }

    // Method to get full address as a single string
    fun getFullAddress(): String {
        return "${beneficiaryAddress.firstLineMailing}${beneficiaryAddress.scndLineMailing?.let { "\n$it" } ?: ""}\n" +
                "${beneficiaryAddress.city}, ${beneficiaryAddress.stateCode} ${beneficiaryAddress.zipCode}\n" +
                "${beneficiaryAddress.country}"
    }
}
