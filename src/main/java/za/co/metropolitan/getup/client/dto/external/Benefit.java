package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import za.co.metropolitan.getup.client.dto.Beneficiary;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "benefitAmount",
})

public class Benefit implements Serializable {

    @JsonProperty("benefitAmount")
    private Double benefitAmount;

    @JsonProperty("premium")
    private Double premium;

    @JsonProperty("beneficiaries")
    private List<CoreBeneficiary> beneficiaries;

    @JsonProperty("lives")
    private List<CoreLive> lives;

    @JsonProperty("children")
    private List<Children> Children;

    @JsonProperty("benefits")
    private List<Benefit> benefits;

    public List<Children> getChildren() {
        return Children;
    }

    public void setChildren(List<Children> children) {
        Children = children;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public List<CoreLive> getLives() {
        return lives;
    }

    public void setLives(List<CoreLive> lives) {
        this.lives = lives;
    }

    public List<CoreBeneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<CoreBeneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public Double getBenefitAmount() {
        return benefitAmount;
    }
    public void setBenefitAmount(Double benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    @Override
    public String toString() {
        return "Benefit{" +
                "benefitAmount=" + benefitAmount +
                ", beneficiaries=" + beneficiaries +
                ", lives=" + lives +
                ", benefits=" + benefits +
                '}';
    }
}
