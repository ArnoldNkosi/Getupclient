package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_mkt_consent_type_lkp")
public class MarketingConsentLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "consent_type")
    private String consentType;

    @Column(name = "consent_type_desc")
    private String consentTypeDescription;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getConsentType() {
        return consentType;
    }

    public void setConsentType(String consentType) {
        this.consentType = consentType;
    }

    public String getConsentTypeDescription() {
        return consentTypeDescription;
    }

    public void setConsentTypeDescription(String consentTypeDescription) {
        this.consentTypeDescription = consentTypeDescription;
    }

    @Override
    public String toString() {
        return "MarketingConsentLookup{" +
                "id=" + id +
                ", consentType='" + consentType + '\'' +
                ", consentTypeDescription='" + consentTypeDescription + '\'' +
                '}';
    }
}
