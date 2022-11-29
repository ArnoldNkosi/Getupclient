package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "policyNumber",
            "productDescription",
            "productName",
            "policyStatus",
            "policyStatusDescription",
            "inceptionDate",
            "schemeCode",
            "totalPremium",
            "totalWaiverDeathDisabilityPremium",
            "totalWaiverRetirementPremium",
            "premiumDebt",
            "outstandingDebt",
            "premiumSkipCount",
            "policyHolders",
            "benefits",
            "beneficiaryOfOwnership",
            "roleplayers",
            "premiumPayer",
//            "policyMovements",
//            "productMovements",
//            "premiumPosition"
})

public class FuneralPolicyInfo implements Serializable {

    @JsonProperty("policyNumber")
    private PolicyNumber policyNumber = null;
    @JsonProperty("productDescription")
    private String productDescription;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("policyStatus")
    private String policyStatus;
    @JsonProperty("policyStatusDescription")
    private String policyStatusDescription;
    @JsonProperty("inceptionDate")
    private String inceptionDate;
    @JsonProperty("schemeCode")
    private String schemeCode;
    @JsonProperty("totalPremium")
    private Double totalPremium;
    @JsonProperty("totalWaiverDeathDisabilityPremium")
    private String totalWaiverDeathDisabilityPremium;
    @JsonProperty("premiumDebt")
    private String premiumDebt;
    @JsonProperty("outstandingDebt")
    private String outstandingDebt;
    @JsonProperty("premiumSkipCount")
    private String premiumSkipCount;
    @JsonProperty("policyHolders")
    private List<PolicyHolder> policyHolders = null;
    @JsonProperty("benefits")
    private List<Benefit> benefits = null;
    @JsonProperty("beneficiaryOfOwnership")
    private BeneficiaryOfOwnership beneficiaryOfOwnership = null;
    @JsonProperty("roleplayers")
    private List<Roleplayer> roleplayers = null;
    @JsonProperty("premiumPayer")
    private PremiumPayer premiumPayer = null;
//    @JsonProperty("policyMovements")
//    private PolicyMovements policyMovements = null;
//    @JsonProperty("productMovements")
//    private ProductMovements productMovements = null;
//    @JsonProperty("premiumPosition")
//    private PremiumPosition premiumPosition = null;


    public PolicyNumber getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(PolicyNumber policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getPolicyStatusDescription() {
        return policyStatusDescription;
    }

    public void setPolicyStatusDescription(String policyStatusDescription) {
        this.policyStatusDescription = policyStatusDescription;
    }

    public String getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(String inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public Double getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(Double totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getTotalWaiverDeathDisabilityPremium() {
        return totalWaiverDeathDisabilityPremium;
    }

    public void setTotalWaiverDeathDisabilityPremium(String totalWaiverDeathDisabilityPremium) {
        this.totalWaiverDeathDisabilityPremium = totalWaiverDeathDisabilityPremium;
    }

    public String getPremiumDebt() {
        return premiumDebt;
    }

    public void setPremiumDebt(String premiumDebt) {
        this.premiumDebt = premiumDebt;
    }

    public String getOutstandingDebt() {
        return outstandingDebt;
    }

    public void setOutstandingDebt(String outstandingDebt) {
        this.outstandingDebt = outstandingDebt;
    }

    public String getPremiumSkipCount() {
        return premiumSkipCount;
    }

    public void setPremiumSkipCount(String premiumSkipCount) {
        this.premiumSkipCount = premiumSkipCount;
    }

    public List<PolicyHolder> getPolicyHolders() {
        return policyHolders;
    }

    public void setPolicyHolders(List<PolicyHolder> policyHolders) {
        this.policyHolders = policyHolders;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public BeneficiaryOfOwnership getBeneficiaryOfOwnership() {
        return beneficiaryOfOwnership;
    }

    public void setBeneficiaryOfOwnership(BeneficiaryOfOwnership beneficiaryOfOwnership) {
        this.beneficiaryOfOwnership = beneficiaryOfOwnership;
    }

    public List<Roleplayer> getRoleplayers() {
        return roleplayers;
    }

    public void setRoleplayers(List<Roleplayer> roleplayers) {
        this.roleplayers = roleplayers;
    }

    public PremiumPayer getPremiumPayer() {
        return premiumPayer;
    }

    public void setPremiumPayer(PremiumPayer premiumPayer) {
        this.premiumPayer = premiumPayer;
    }

    @Override
    public String toString() {
        return "FuneralPolicyInfo{" +
                "policyNumber=" + policyNumber +
                ", productDescription='" + productDescription + '\'' +
                ", productName='" + productName + '\'' +
                ", policyStatus='" + policyStatus + '\'' +
                ", policyStatusDescription='" + policyStatusDescription + '\'' +
                ", inceptionDate='" + inceptionDate + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                ", totalPremium='" + totalPremium + '\'' +
                ", totalWaiverDeathDisabilityPremium='" + totalWaiverDeathDisabilityPremium + '\'' +
                ", premiumDebt='" + premiumDebt + '\'' +
                ", outstandingDebt='" + outstandingDebt + '\'' +
                ", premiumSkipCount='" + premiumSkipCount + '\'' +
                ", policyHolders=" + policyHolders +
                ", benefits=" + benefits +
                ", beneficiaryOfOwnership=" + beneficiaryOfOwnership +
                ", roleplayers=" + roleplayers +
                ", premiumPayer=" + premiumPayer +
                '}';
    }
}
