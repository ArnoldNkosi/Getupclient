package za.co.metropolitan.getup.client.dto.external;

import java.io.Serializable;

public class CoreMarketingConsent implements Serializable {

 private boolean addBenefitsToPlan;
 private boolean addToResearchCommunity = false;
 private boolean newProducts = false;
 private boolean shareInfoInGroup = false;

 public boolean isAddBenefitsToPlan() {
  return addBenefitsToPlan;
 }

 public void setAddBenefitsToPlan(boolean addBenefitsToPlan) {
  this.addBenefitsToPlan = addBenefitsToPlan;
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
  return "MarketingConsent{" +
      "addBenefitsToPlan=" + addBenefitsToPlan +
      ", addToResearchCommunity=" + addToResearchCommunity +
      ", newProducts=" + newProducts +
      ", shareInfoInGroup=" + shareInfoInGroup +
      '}';
 }
}