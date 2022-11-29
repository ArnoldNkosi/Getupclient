package za.co.metropolitan.getup.client.dto.external;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreBeneficiary {
    @JsonProperty("rolePlayerId")
    private String rolePlayerId;
    @JsonProperty("percentageShare")
    private int percentageShare;
    @JsonProperty("policyHolderRelationShip")
    private PolicyHolderRelationShip policyHolderRelationShip;

    public int getPercentageShare() {
        return percentageShare;
    }

    public void setPercentageShare(int percentageShare) {
        this.percentageShare = percentageShare;
    }

    public PolicyHolderRelationShip getPolicyHolderRelationShip() {
        return policyHolderRelationShip;
    }

    public void setPolicyHolderRelationShip(PolicyHolderRelationShip policyHolderRelationShip) {
        this.policyHolderRelationShip = policyHolderRelationShip;
    }

    public String getRolePlayerId() {
        return rolePlayerId;
    }

    public void setRolePlayerId(String rolePlayerId) {
        this.rolePlayerId = rolePlayerId;
    }
}
