package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class PreferenceOutboundMediumDTO implements Serializable {

    private BigInteger clientId;
    private String medium = "";
    private String userSignOn;
    private Boolean optedIn = null;

    public Boolean getOptedIn() {
        return optedIn;
    }

    public void setOptedIn(Boolean optedIn) {
        this.optedIn = optedIn;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    @Override
    public String toString() {
        return "PreferenceInboundMediumDTO{" +
                "clientId=" + clientId +
                ", medium='" + medium + '\'' +
                ", userSignOn='" + userSignOn + '\'' +
                ", optedIn='" + optedIn + '\'' +
                '}';
    }
}
