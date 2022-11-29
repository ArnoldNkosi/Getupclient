package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

public class ClientLifeHackDetailsDto implements Serializable {

 private BigInteger id;
 private BigInteger clientId;
 private String product;
 private String lifeHack;
 private String userSignOn;
 private Timestamp createdAt;
 private Timestamp endedAt;
 private Integer lifeHackScore;
 private Timestamp lifeHackScoreAt;

 private boolean delete;

 private Timestamp optedInDate;

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

 public String getProduct() {
  return product;
 }

 public void setProduct(String product) {
  this.product = product;
 }

 public String getLifeHack() {
  return lifeHack;
 }

 public void setLifeHack(String lifeHack) {
  this.lifeHack = lifeHack;
 }

 public boolean isDelete() {
  return delete;
 }

 public void setDelete(boolean delete) {
  this.delete = delete;
 }

 public String getUserSignOn() {
  return userSignOn;
 }

 public void setUserSignOn(String userSignOn) {
  this.userSignOn = userSignOn;
 }

 public Timestamp getOptedInDate() {
  return optedInDate;
 }

 public void setOptedInDate(Timestamp optedInDate) {
  this.optedInDate = optedInDate;
 }

 public Timestamp getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(Timestamp createdAt) {
  this.createdAt = createdAt;
 }

 public Timestamp getEndedAt() {
  return endedAt;
 }

 public void setEndedAt(Timestamp endedAt) {
  this.endedAt = endedAt;
 }

 public Integer getLifeHackScore() {
  return lifeHackScore;
 }

 public void setLifeHackScore(Integer lifeHackScore) {
  this.lifeHackScore = lifeHackScore;
 }

 public Timestamp getLifeHackScoreAt() {
  return lifeHackScoreAt;
 }

 public void setLifeHackScoreAt(Timestamp lifeHackScoreAt) {
  this.lifeHackScoreAt = lifeHackScoreAt;
 }

 @Override
 public String toString() {
  return "ClientLifeHackDetailsDto{" +
          "id=" + id +
          ", clientId=" + clientId +
          ", product='" + product + '\'' +
          ", lifeHack='" + lifeHack + '\'' +
          ", userSignOn='" + userSignOn + '\'' +
          ", createdAt=" + createdAt +
          ", endedAt=" + endedAt +
          ", lifeHackScore=" + lifeHackScore +
          ", lifeHackScoreAt=" + lifeHackScoreAt +
          ", delete=" + delete +
          ", optedInDate=" + optedInDate +
          '}';
 }
}
