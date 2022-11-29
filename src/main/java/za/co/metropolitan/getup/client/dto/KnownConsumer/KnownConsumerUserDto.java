package za.co.metropolitan.getup.client.dto.KnownConsumer;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class KnownConsumerUserDto implements Serializable {

    private BigInteger id;

    private String known_consumer_number;

    private String otp;

    private Timestamp otp_created_at;

    private Timestamp created_at;

    private List<KnownConsumerContactDto> knownConsumerContactDto;

    public KnownConsumerUserDto() {}
    public KnownConsumerUserDto(BigInteger id, String known_consumer_number, String otp, Timestamp otp_created_at, Timestamp created_at, List<KnownConsumerContactDto> knownConsumerContactDto) {
        this.id = id;
        this.known_consumer_number = known_consumer_number;
        this.otp = otp;
        this.otp_created_at = otp_created_at;
        this.created_at = created_at;
        this.knownConsumerContactDto = knownConsumerContactDto;
    }

    public KnownConsumerUserDto(BigInteger id, String known_consumer_number, String otp, Timestamp otp_created_at, Timestamp created_at) {
        this.id = id;
        this.known_consumer_number = known_consumer_number;
        this.otp = otp;
        this.otp_created_at = otp_created_at;
        this.created_at = created_at;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getKnown_consumer_number() {
        return known_consumer_number;
    }

    public List<KnownConsumerContactDto> getKnownConsumerContactDto() {
        return knownConsumerContactDto;
    }

    public void setKnownConsumerContactDto(List<KnownConsumerContactDto> knownConsumerContactDto) {
        this.knownConsumerContactDto = knownConsumerContactDto;
    }

    public void setKnown_consumer_number(String known_consumer_number) {
        this.known_consumer_number = known_consumer_number;
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
        return "KnownConsumerUserDto{" +
                "id=" + id +
                ", known_consumer_number='" + known_consumer_number + '\'' +
                ", otp='" + otp + '\'' +
                ", otp_created_at=" + otp_created_at +
                ", created_at=" + created_at +
                ", knownConsumerContactDto=" + knownConsumerContactDto +
                '}';
    }
}
