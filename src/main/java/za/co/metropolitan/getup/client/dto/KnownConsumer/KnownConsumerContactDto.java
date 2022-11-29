package za.co.metropolitan.getup.client.dto.KnownConsumer;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

public class KnownConsumerContactDto implements Serializable {

    private BigInteger id;
    private BigInteger known_consumer_id;
    private String contact_type;
    private Integer contact_type_seq_number;
    private String contact_value;
    private Timestamp created_at;
    private Timestamp updated_at;
    public KnownConsumerContactDto() {
    }

    public KnownConsumerContactDto(BigInteger known_consumer_id, String contact_type, Integer contact_type_seq_number, String contact_value, Timestamp created_at,Timestamp updated_at) {
        this.known_consumer_id = known_consumer_id;
        this.contact_type = contact_type;
        this.contact_type_seq_number = contact_type_seq_number;
        this.contact_value = contact_value;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public BigInteger getKnown_consumer_id() {
        return known_consumer_id;
    }
    public void setKnown_consumer_id(BigInteger known_consumer_id) {
        this.known_consumer_id = known_consumer_id;
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
        return contact_value;
    }
    public void setContact_value(String contact_value) {
        this.contact_value = contact_value;
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
        return "KnownConsumerContactDto{" +
                "id=" + id +
                ", known_consumer_id=" + known_consumer_id +
                ", contact_type='" + contact_type + '\'' +
                ", contact_type_seq_number=" + contact_type_seq_number +
                ", contact_value='" + contact_value + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
