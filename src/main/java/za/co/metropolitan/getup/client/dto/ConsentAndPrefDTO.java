package za.co.metropolitan.getup.client.dto;

import za.co.metropolitan.getup.client.dto.client.ContactMediumRequestDto;

import java.io.Serializable;
import java.math.BigInteger;

public class ConsentAndPrefDTO implements Serializable {

        private String userSignOn;
        private BigInteger clientId;
        private String bestTimeToContact;
        private ContactMediumRequestDto inboundMedium;
        private ContactMediumRequestDto outboundMedium;
        private Boolean marketingResearch = null;
        private Boolean newSolutions = null;
        private Boolean shareInfoInternally = null;

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
        return "ConsentAndPrefDTO{" +
                "userSignOn='" + userSignOn + '\'' +
                ", clientId=" + clientId +
                ", bestTimeToContact='" + bestTimeToContact + '\'' +
                ", inboundMedium=" + inboundMedium +
                ", outboundMedium=" + outboundMedium +
                ", marketingResearch=" + marketingResearch +
                ", newSolutions=" + newSolutions +
                ", shareInfoInternally=" + shareInfoInternally +
                '}';
    }
}

