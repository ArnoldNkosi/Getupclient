package za.co.metropolitan.getup.client.dto.external;

import java.io.Serializable;

public class CoreMarketingConsentResponse implements Serializable {

 private String message;

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 @Override
 public String toString() {
  return "MarketConsentResponse{" +
      "message='" + message + '\'' +
      '}';
 }
}