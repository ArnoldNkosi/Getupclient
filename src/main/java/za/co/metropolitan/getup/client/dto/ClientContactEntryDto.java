package za.co.metropolitan.getup.client.dto;

import java.math.BigInteger;

public class ClientContactEntryDto {
    private BigInteger clientId;
    private String contactType;
    private String contactValue;
    private String userSignOn;

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
        return "ClientContactEntryDto{" +
                "clientId=" + clientId +
                ", contactType='" + contactType + '\'' +
                ", contactValue='" + contactValue + '\'' +
                ", userSignOn='" + userSignOn + '\'' +
                '}';
    }
}
