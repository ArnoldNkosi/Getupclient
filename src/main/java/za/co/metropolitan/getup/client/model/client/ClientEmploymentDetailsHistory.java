package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_client_employment_details_his")
public class ClientEmploymentDetailsHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "employer_name")
    private String employerName;

    @Column(name = "industry")
    private String industry;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "gross_salary_amount")
    private Double grossSalaryAmount;

    @Column(name = "user_signon")
    private String userSignOn;

    @Column(name = "created_at")
    private Timestamp createdAt;

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ClientEmploymentDetailsHistory{" +
            "id=" + id +
            ", clientId=" + clientId +
            ", employmentType='" + employmentType + '\'' +
            ", employerName='" + employerName + '\'' +
            ", industry='" + industry + '\'' +
            ", occupation='" + occupation + '\'' +
            ", grossSalaryAmount=" + grossSalaryAmount +
            ", userSignOn='" + userSignOn + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}