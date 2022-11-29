package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_contact_type_lkp")
public class ContactTypeLookup {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "contact_type")
    private String contactType;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @Override
    public String toString() {
        return "ContactTypeLookup{" +
                "id=" + id +
                ", contactType=" + contactType +
                '}';
    }
}
