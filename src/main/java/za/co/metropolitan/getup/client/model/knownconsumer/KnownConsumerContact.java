package za.co.metropolitan.getup.client.model.knownconsumer;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_known_consumer_contact")
public class KnownConsumerContact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "known_consumer_id")
    private BigInteger knownConsumerId;

    @Column(name = "contact_type")
    private String contact_type;

    @Column(name = "contact_type_seq_number")
    private Integer contact_type_seq_number;

    @Column(name = "contact_value")
    private String contactValue;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public KnownConsumerContact() {
    }

    public KnownConsumerContact(BigInteger knownConsumerId, String contact_type, Integer contact_type_seq_number, String contact_value) {
        this.knownConsumerId = knownConsumerId;
        this.contact_type = contact_type;
        this.contact_type_seq_number = contact_type_seq_number;
        this.contactValue = contact_value;
    }

    public KnownConsumerContact(BigInteger knownConsumerId, String contact_type, Integer contact_type_seq_number, String contact_value, Timestamp updated_at) {
        this.knownConsumerId = knownConsumerId;
        this.contact_type = contact_type;
        this.contact_type_seq_number = contact_type_seq_number;
        this.contactValue = contact_value;
        this.updated_at = updated_at;
    }

    public KnownConsumerContact(String contact_type, Integer contact_type_seq_number, String contact_value) {
        this.contact_type = contact_type;
        this.contact_type_seq_number = contact_type_seq_number;
        this.contactValue = contact_value;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKnownConsumerId() {
        return knownConsumerId;
    }

    public void setKnownConsumerId(BigInteger knownConsumerId) {
        this.knownConsumerId = knownConsumerId;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public Integer getContact_type_seq_number() {
        return contact_type_seq_number;
    }

    public void setContact_type_seq_number(Integer contact_type_seq_number) {
        this.contact_type_seq_number = contact_type_seq_number;
    }

    public String getContact_value() {
        return contactValue;
    }

    public void setContact_value(String contact_value) {
        this.contactValue = contact_value;
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
        return "KnownConsumerContact{" +
                "id=" + id +
                ", knownConsumerId=" + knownConsumerId +
                ", contact_type='" + contact_type + '\'' +
                ", contact_type_seq_number=" + contact_type_seq_number +
                ", contact_value='" + contactValue + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
