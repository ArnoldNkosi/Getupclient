package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class EmploymentDetailsDto implements Serializable {

    private BigInteger id;
    private BigInteger clientId;
    private String employmentType;
    private String employerName;
    private String industry;
    private String occupation;
    private Double grossSalaryAmount;
    private String userSignOn;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Double getGrossSalaryAmount() {
        return grossSalaryAmount;
    }

    public void setGrossSalaryAmount(Double grossSalaryAmount) {
        this.grossSalaryAmount = grossSalaryAmount;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    @Override
    public String toString() {
        return "EmploymentDetailsDto{" +
            "id=" + id +
            ", clientId=" + clientId +
            ", employmentType='" + employmentType + '\'' +
            ", employerName='" + employerName + '\'' +
            ", industry='" + industry + '\'' +
            ", occupation='" + occupation + '\'' +
            ", grossSalaryAmount=" + grossSalaryAmount +
            ", userSignOn='" + userSignOn + '\'' +
            '}';
    }
}