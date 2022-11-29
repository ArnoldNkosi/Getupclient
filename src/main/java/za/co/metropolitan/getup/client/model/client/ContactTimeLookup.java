package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_contact_time_lkp")
public class ContactTimeLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "contact_time")
    private String contactTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    @Override
    public String toString() {
        return "ContactTimeLookup{" +
                "id=" + id +
                ", contactTime=" + contactTime +
                '}';
    }
}
