package za.co.metropolitan.getup.client.dto.external;

import za.co.metropolitan.getup.client.dto.UpdateResponseDto;

import java.io.Serializable;

public class MarketingConsentDetailsResponse implements Serializable {

 private UpdateResponseDto updateResponseDto;
 private CoreMarketingConsentResponse coreMarketingConsentResponse;

 public UpdateResponseDto getUpdateResponse() {
  return updateResponseDto;
 }

 public void setUpdateResponse(UpdateResponseDto updateResponseDto) {
  this.updateResponseDto = updateResponseDto;
 }

 public CoreMarketingConsentResponse getCoreMarketingConsentResponse() {
  return coreMarketingConsentResponse;
 }

 public void setCoreMarketingConsentResponse(CoreMarketingConsentResponse coreMarketingConsentResponse) {
  this.coreMarketingConsentResponse = coreMarketingConsentResponse;
 }

 @Override
 public String toString() {
  return "MarketingConsentDetailsResponse{" +
      "updateResponseDto=" + updateResponseDto +
      ", marketConsentResponse=" + coreMarketingConsentResponse +
      '}';
 }
}