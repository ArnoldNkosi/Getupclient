package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_client_contact")
public class ClientContact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "contact_value")
    private String contactValue;

    @Column(name = "user_signon")
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

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    @Override
    public String toString() {
        return "ClientContact{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", contactType='" + contactType + '\'' +
                ", contactValue='" + contactValue + '\'' +
                ", userSignOn='" + userSignOn + '\'' +
                '}';
    }
}
