package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class ClientPrefContactTimeDto implements Serializable {

 private BigInteger clientId;
 private String contactTime = null;
 private String userSignOn;

 public BigInteger getClientId() {
  return clientId;
 }

 public void setClientId(BigInteger clientId) {
  this.clientId = clientId;
 }

 public String getContactTime() {
  return contactTime;
 }

 public void setContactTime(String contactTime) {
  this.contactTime = contactTime;
 }

 public String getUserSignOn() {
  return userSignOn;
 }

 public void setUserSignOn(String userSignOn) {
  this.userSignOn = userSignOn;
 }

 @Override
 public String toString() {
  return "ClientPrefContactTimeDto{" +
          ", clientId=" + clientId +
          ", contactTime='" + contactTime + '\'' +
          ", userSignOn='" + userSignOn + '\'' +
          '}';
 }
}