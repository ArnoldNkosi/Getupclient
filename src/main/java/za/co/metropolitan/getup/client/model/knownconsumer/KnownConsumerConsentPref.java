package za.co.metropolitan.getup.client.model.knownconsumer;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_known_consumer_contact_pref")
public class KnownConsumerConsentPref {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "known_consumer_contact_id")
    private BigInteger known_consumer_contact_id;

    @Column(name = "contact_time")
    private String contact_time;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public KnownConsumerConsentPref() {
    }

    public KnownConsumerConsentPref(BigInteger id, BigInteger known_consumer_contact_id, String contact_time, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.known_consumer_contact_id = known_consumer_contact_id;
        this.contact_time = contact_time;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKnown_consumer_contact_id() {
        return known_consumer_contact_id;
    }

    public void setKnown_consumer_contact_id(BigInteger known_consumer_contact_id) {
        this.known_consumer_contact_id = known_consumer_contact_id;
    }

    public String getContact_time() {
        return contact_time;
    }

    public void setContact_time(String contact_time) {
        this.contact_time = contact_time;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "KnownConsumerConsentPref{" +
                "id=" + id +
                ", known_consumer_contact_id=" + known_consumer_contact_id +
                ", contact_time='" + contact_time + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}

