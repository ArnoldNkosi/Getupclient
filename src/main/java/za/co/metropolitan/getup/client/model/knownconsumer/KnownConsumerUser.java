package za.co.metropolitan.getup.client.model.knownconsumer;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "audit", name = "tbl_known_consumer_users")
public class KnownConsumerUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "known_consumer_number")
    private String knownConsumerNumber;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_created_at")
    private Timestamp otp_created_at;

    @Column(name = "created_at")
    private Timestamp created_at;


    public KnownConsumerUser() {
    }

    public KnownConsumerUser(String knownConsumerNumber, String otp, Timestamp otp_created_at) {
        this.knownConsumerNumber = knownConsumerNumber;
        this.otp = otp;
        this.otp_created_at = otp_created_at;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getKnownConsumerNumber() {
        return knownConsumerNumber;
    }

    public void setKnownConsumerNumber(String knownConsumerNumber) {
        this.knownConsumerNumber = knownConsumerNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getOtp_created_at() {
        return otp_created_at;
    }

    public void setOtp_created_at(Timestamp otp_created_at) {
        this.otp_created_at = otp_created_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "KnownConsumerUser{" +
                "id=" + id +
                ", knownConsumerNumber='" + knownConsumerNumber + '\'' +
                ", otp='" + otp + '\'' +
                ", otp_created_at=" + otp_created_at +
                ", created_at=" + created_at +
                '}';
    }
}
