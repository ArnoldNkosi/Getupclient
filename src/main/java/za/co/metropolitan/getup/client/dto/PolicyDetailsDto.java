package za.co.metropolitan.getup.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import za.co.metropolitan.getup.client.dto.external.PolicyContractLinkList;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyDetailsDto implements Serializable {


    private String policyNumber;
    private Double coverAmount;
    private Double bundledPremium;
    private String policyStartDate;
    private Person mainMember;
    private List<Spouse> spouses = null;
    private List<Child> children = null;
    private List<Person> childrenForLife = null;
    private List<Person>  parents = null;
    private List<Person> extendedFamily = null;
    private List<Person> otherLives = null;
    private PaymentDto paymentDetails;
    private List<Beneficiary> beneficiaries = null;
    private String packageName;
    private String status;
    private int billingDay;
    private String policyContractUrl;
    private List<PolicyContractLinkList> contractUrls;

    public List<Person> getOtherLives() {
        return otherLives;
    }

    public void setOtherLives(List<Person> otherLives) {
        this.otherLives = otherLives;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Double getCoverAmount() {
        return coverAmount;
    }

    public void setCoverAmount(Double coverAmount) {
        this.coverAmount = coverAmount;
    }

    public Double getBundledPremium() {
        return bundledPremium;
    }

    public void setBundledPremium(Double bundledPremium) {
        this.bundledPremium = bundledPremium;
    }

    public String getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(String policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public Person getMainMember() {
        return mainMember;
    }

    public void setMainMember(Person mainMember) {
        this.mainMember = mainMember;
    }

    public List<Spouse> getSpouses() {
        return spouses;
    }

    public void setSpouses(List<Spouse> spouses) {
        this.spouses = spouses;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Person> getChildrenForLife() {
        return childrenForLife;
    }

    public void setChildrenForLife(List<Person> childrenForLife) {
        this.childrenForLife = childrenForLife;
    }

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }

    public List<Person> getExtendedFamily() {
        return extendedFamily;
    }

    public void setExtendedFamily(List<Person> extendedFamily) {
        this.extendedFamily = extendedFamily;
    }

    public PaymentDto getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDto paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBillingDay() {
        return billingDay;
    }

    public void setBillingDay(int billingDay) {
        this.billingDay = billingDay;
    }

    public String getPolicyContractUrl() {
        return policyContractUrl;
    }

    public void setPolicyContractUrl(String policyContractUrl) {
        this.policyContractUrl = policyContractUrl;
    }

    public List<PolicyContractLinkList> getContractUrls() {
        return contractUrls;
    }

    public void setContractUrls(List<PolicyContractLinkList> contractUrls) {
        this.contractUrls = contractUrls;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", coverAmount=" + coverAmount +
                ", bundledPremium=" + bundledPremium +
                ", policyStartDate='" + policyStartDate + '\'' +
                ", mainMember=" + mainMember +
                ", spouses=" + spouses +
                ", children=" + children +
                ", childrenForLife=" + childrenForLife +
                ", parents=" + parents +
                ", extendedFamily=" + extendedFamily +
                ", paymentDetails=" + paymentDetails +
                ", beneficiaries=" + beneficiaries +
                ", packageName='" + packageName + '\'' +
                ", status='" + status + '\'' +
                ", billingDay=" + billingDay +
                ", policyContractUrl='" + policyContractUrl + '\'' +
                ", contractUrls='" + contractUrls + '\'' +
                '}';
    }
}
