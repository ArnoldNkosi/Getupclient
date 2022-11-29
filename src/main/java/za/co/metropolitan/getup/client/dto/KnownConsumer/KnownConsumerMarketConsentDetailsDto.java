package za.co.metropolitan.getup.client.dto.KnownConsumer;
import java.io.Serializable;
import java.math.BigInteger;

public class KnownConsumerMarketConsentDetailsDto  implements Serializable {

        private BigInteger id;
        private BigInteger known_consumer_id;

        private String Known_consumer_number;
        private String consentType = "";
        private String userSignOn;
        private Boolean optedIn = null;
    public KnownConsumerMarketConsentDetailsDto() {
    }


    public KnownConsumerMarketConsentDetailsDto(String consentType, Boolean optedIn) {
        this.consentType = consentType;
        this.optedIn = optedIn;
    }

    public String getKnown_consumer_number() {
        return Known_consumer_number;
    }
    public void setKnown_consumer_number(String known_consumer_number) {
        Known_consumer_number = known_consumer_number;
    }
    public Boolean getOptedIn() {
            return optedIn;
        }

        public void setOptedIn(Boolean optedIn) {
            this.optedIn = optedIn;
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

        public String getConsentType() {
            return consentType;
        }

        public void setConsentType(String consentType) {
            this.consentType = consentType;
        }
    public void setContentType(String consentType) {
    }

        public String getUserSignOn() {
            return userSignOn;
        }

        public void setUserSignOn(String userSignOn) {
            this.userSignOn = userSignOn;
        }

    @Override
    public String toString() {
        return "KnownConsumerMarketConsentDetailsDto{" +
                "id=" + id +
                ", known_consumer_id=" + known_consumer_id +
                ", Known_consumer_number='" + Known_consumer_number + '\'' +
                ", consentType='" + consentType + '\'' +
                ", userSignOn='" + userSignOn + '\'' +
                ", optedIn=" + optedIn +
                '}';
    }
}
