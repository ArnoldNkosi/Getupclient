package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_client_mkt_consent_his")
public class ClientMarketConsentHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "consent_type")
    private String consentType;

    @Column(name = "user_signon")
    private String userSignOn;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "has_opted_in")
    private Boolean optedIn = null;

    public Boolean getOptedIn() {
        return optedIn;
    }

    public void setOptedIn(Boolean optedIn) {
        this.optedIn = optedIn;
    }

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

    public String getConsentType() {
        return consentType;
    }

    public void setConsentType(String consentType) {
        this.consentType = consentType;
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
        return "ClientMarketConsentHistory{" +
            "id=" + id +
            ", clientId=" + clientId +
            ", consentType='" + consentType + '\'' +
            ", userSignOn='" + userSignOn + '\'' +
            ", createdAt=" + createdAt +
                ", optedIn=" + optedIn +
            '}';
    }
}