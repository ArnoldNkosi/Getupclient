package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class ProductsDto  implements Serializable {

    private String productName;
    private Double totalCoverAmount;
    private String membersCovered;
    private Double totalMonthlyPremium;
    private PolicyDetailsDto policyDetails;
    private String policyUGC;

    public String getPolicyUGC() {
        return policyUGC;
    }

    public void setPolicyUGC(String policyUGC) {
        this.policyUGC = policyUGC;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getTotalCoverAmount() {
        return totalCoverAmount;
    }

    public void setTotalCoverAmount(Double totalCoverAmount) {
        this.totalCoverAmount = totalCoverAmount;
    }

    public String getMembersCovered() {
        return membersCovered;
    }

    public void setMembersCovered(String membersCovered) {
        this.membersCovered = membersCovered;
    }

    public Double getTotalMonthlyPremium() {
        return totalMonthlyPremium;
    }

    public void setTotalMonthlyPremium(Double totalMonthlyPremium) {
        this.totalMonthlyPremium = totalMonthlyPremium;
    }

    public PolicyDetailsDto getPolicyDetails() {
        return policyDetails;
    }

    public void setPolicyDetails(PolicyDetailsDto policyDetails) {
        this.policyDetails = policyDetails;
    }

    @Override
    public String toString() {
        return "ProductsDto{" +
                "productName='" + productName + '\'' +
                ", totalCoverAmount='" + totalCoverAmount + '\'' +
                ", membersCovered='" + membersCovered + '\'' +
                ", totalMonthlyPremium='" + totalMonthlyPremium + '\'' +
                ", policyDetails=" + policyDetails +
                ", policyUGC='" + policyUGC + '\'' +
                '}';
    }
}
