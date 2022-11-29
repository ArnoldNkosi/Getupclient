package za.co.metropolitan.getup.client.model.knownconsumer;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;


@Entity
@Table(schema = "client", name = "tbl_known_consumer_consent")
public class KnownConsumerConsent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;


    @Column(name="Known_Consumer_Id")
    private BigInteger knownconsumerid;

    @Column(name="Consent_Type")
    private String ConsentType;

    @Column(name="has_opted_in")
    private Boolean Has_opted_in;

    @Column(name="Created_at")
    private Timestamp Created_at;

    @Column(name="Updated_at")
    private Timestamp Updated_at;


    public KnownConsumerConsent() {
    }

    public KnownConsumerConsent(BigInteger knownconsumerid, String consentType, Boolean has_opted_in, Timestamp created_at, Timestamp updated_at) {
        this.knownconsumerid = knownconsumerid;
        ConsentType = consentType;
        Has_opted_in = has_opted_in;
        Created_at = created_at;
        Updated_at = updated_at;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        id = id;
    }

    public BigInteger getKnown_consumer_id() {
        return knownconsumerid;
    }

    public void setKnown_consumer_id(BigInteger known_consumer_id) {
        this.knownconsumerid = known_consumer_id;
    }

    public String getConsentType() {
        return ConsentType;
    }

    public void setConsentType(String consentType) {
        ConsentType = consentType;
    }

    public Boolean getHas_opted_in() {
        return Has_opted_in;
    }

    public void setHas_opted_in(Boolean has_opted_in) {
        Has_opted_in = has_opted_in;
    }

    public Timestamp getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        Created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return Updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        Updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "KnownConsumerConsent{" +
                "id=" + id +
                ", known_consumer_id=" + knownconsumerid +
                ", ConsentType='" + ConsentType + '\'' +
                ", Has_opted_in=" + Has_opted_in +
                ", Created_at=" + Created_at +
                ", Updated_at=" + Updated_at +
                '}';
    }
}
