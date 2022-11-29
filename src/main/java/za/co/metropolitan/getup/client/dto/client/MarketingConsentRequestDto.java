package za.co.metropolitan.getup.client.dto.client;

import java.io.Serializable;
import java.math.BigInteger;

public class MarketingConsentRequestDto implements Serializable {

    private BigInteger clientId;
    private String userSignOn;
    private Boolean marketingResearch = null;
    private Boolean newSolutions = null;
    private Boolean shareInfoInternally = null;


    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    public Boolean getMarketingResearch() {
        return marketingResearch;
    }

    public void setMarketingResearch(Boolean marketingResearch) {
        this.marketingResearch = marketingResearch;
    }

    public Boolean getNewSolutions() {
        return newSolutions;
    }

    public void setNewSolutions(Boolean newSolutions) {
        this.newSolutions = newSolutions;
    }

    public Boolean getShareInfoInternally() {
        return shareInfoInternally;
    }

    public void setShareInfoInternally(Boolean shareInfoInternally) {
        this.shareInfoInternally = shareInfoInternally;
    }

    @Override
    public String toString() {
        return "MarketingConsentRequestDto{" +
                "clientId=" + clientId +
                ", userSignOn='" + userSignOn + '\'' +
                ", marketingResearch=" + marketingResearch +
                ", newSolutions=" + newSolutions +
                ", shareInfoInternally=" + shareInfoInternally +
                '}';
    }
}
