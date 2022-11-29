package za.co.metropolitan.getup.client.dto.client;

import org.springframework.security.core.parameters.P;

import java.io.Serializable;
import java.math.BigInteger;

public class ContactPrefRequestDto implements Serializable {

    private String userSignOn;
    private BigInteger clientId;
    private String bestTimeToContact;
    private ContactMediumRequestDto inboundMedium;
    private ContactMediumRequestDto outboundMedium;

    public String getBestTimeToContact() {
        return bestTimeToContact;
    }

    public void setBestTimeToContact(String bestTimeToContact) {
        this.bestTimeToContact = bestTimeToContact;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public ContactMediumRequestDto getInboundMedium() {
        return inboundMedium;
    }

    public void setInboundMedium(ContactMediumRequestDto inboundMedium) {
        this.inboundMedium = inboundMedium;
    }

    public ContactMediumRequestDto getOutboundMedium() {
        return outboundMedium;
    }

    public void setOutboundMedium(ContactMediumRequestDto outboundMedium) {
        this.outboundMedium = outboundMedium;
    }

    @Override
    public String toString() {
        return "ContactPrefRequestDto{" +
                "userSignOn='" + userSignOn + '\'' +
                ", clientId=" + clientId +
                ", bestTimeToContact='" + bestTimeToContact + '\'' +
                ", inboundMedium=" + inboundMedium +
                ", outboundMedium=" + outboundMedium +
                '}';
    }
}
