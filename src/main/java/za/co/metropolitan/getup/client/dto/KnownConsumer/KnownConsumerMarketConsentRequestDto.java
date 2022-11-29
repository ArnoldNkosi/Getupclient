package za.co.metropolitan.getup.client.dto.KnownConsumer;

import java.io.Serializable;
import java.math.BigInteger;

public class KnownConsumerMarketConsentRequestDto implements Serializable {
        private BigInteger clientId;
        private String userSignOn;
        private boolean addToResearchCommunity = false;
        private boolean newProducts = false;
        private boolean shareInfoInGroup = false;

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

        public boolean isAddToResearchCommunity() {
            return addToResearchCommunity;
        }

        public void setAddToResearchCommunity(boolean addToResearchCommunity) {
            this.addToResearchCommunity = addToResearchCommunity;
        }

        public boolean isNewProducts() {
            return newProducts;
        }

        public void setNewProducts(boolean newProducts) {
            this.newProducts = newProducts;
        }

        public boolean isShareInfoInGroup() {
            return shareInfoInGroup;
        }

        public void setShareInfoInGroup(boolean shareInfoInGroup) {
            this.shareInfoInGroup = shareInfoInGroup;
        }

        @Override
        public String toString() {
            return "ClientMarketConsentRequestDto{" +
                    "clientId=" + clientId +
                    ", userSignOn='" + userSignOn + '\'' +
                    ", addToResearchCommunity=" + addToResearchCommunity +
                    ", newProducts=" + newProducts +
                    ", shareInfoInGroup=" + shareInfoInGroup +
                    '}';
        }
    }
