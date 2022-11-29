package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class ClientMarketConsentDetailsDto implements Serializable {

   private BigInteger id;
   private BigInteger clientId;
   private String consentType = "";
   private String userSignOn;
    private Boolean optedIn = null;

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

   public BigInteger getClientId() {
    return clientId;
   }

   public void setClientId(BigInteger clientId) {
    this.clientId = clientId;
   }

   public String getConsentType() {
    return consentType;
   }

   public void setConsentType(String consentType) {
    this.consentType = consentType;
   }

   public String getUserSignOn() {
    return userSignOn;
   }

   public void setUserSignOn(String userSignOn) {
    this.userSignOn = userSignOn;
   }

   @Override
   public String toString() {
    return "ClientMarketConsentDetailsDto{" +
            "id=" + id +
            ", clientId=" + clientId +
            ", consentType='" + consentType + '\'' +
            ", userSignOn='" + userSignOn + '\'' +
            ", optedIn='" + optedIn + '\'' +
            '}';
   }

}
