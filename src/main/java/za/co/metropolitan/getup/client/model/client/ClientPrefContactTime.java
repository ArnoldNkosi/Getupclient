package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_client_pref_contact")
public class ClientPrefContactTime {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "contact_time")
    private String contactTime;

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

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    @Override
    public String toString() {
        return "ClientPrefContactTimeDto{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", contactTime='" + contactTime + '\'' +
                ", userSignOn='" + userSignOn + '\'' +
                '}';
    }
}
