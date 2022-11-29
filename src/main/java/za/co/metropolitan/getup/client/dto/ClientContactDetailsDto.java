package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class ClientContactDetailsDto implements Serializable {

 private BigInteger id;
 private BigInteger clientId;
 private String contactType;
 private String contactValue;
 private String userSignOn;

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
  return "ClientContactDetailsDto{" +
      "id=" + id +
      ", clientId=" + clientId +
      ", contactType='" + contactType + '\'' +
      ", contactValue='" + contactValue + '\'' +
      ", userSignOn='" + userSignOn + '\'' +
      '}';
 }
}