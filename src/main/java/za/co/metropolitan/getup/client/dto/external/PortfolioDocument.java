package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "agreement_number",
    "originating_system",
    "product_name",
    "product_description",
    "brand",
    "segment",
    "status",
    "premium_amount",
    "premium_frequency",
    "role_players",
    "business_type",
    "business_key",
    "status_description"
})
public class PortfolioDocument {

 @JsonProperty("agreement_number")
 private String agreementNumber;
 @JsonProperty("originating_system")
 private String originatingSystem;
 @JsonProperty("product_name")
 private String productName;
 @JsonProperty("product_description")
 private String productDescription;
 @JsonProperty("brand")
 private String brand;
 @JsonProperty("segment")
 private String segment;
 @JsonProperty("status")
 private String status;
 @JsonProperty("premium_amount")
 private String premiumAmount;
 @JsonProperty("premium_frequency")
 private String premiumFrequency;
 @JsonProperty("role_players")
 private List<String> rolePlayers = null;
 @JsonProperty("business_type")
 private String businessType;
 @JsonProperty("business_key")
 private String businessKey;
 @JsonProperty("status_description")
 private String statusDescription;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("agreement_number")
 public String getAgreementNumber() {
  return agreementNumber;
 }

 @JsonProperty("agreement_number")
 public void setAgreementNumber(String agreementNumber) {
  this.agreementNumber = agreementNumber;
 }

 @JsonProperty("originating_system")
 public String getOriginatingSystem() {
  return originatingSystem;
 }

 @JsonProperty("originating_system")
 public void setOriginatingSystem(String originatingSystem) {
  this.originatingSystem = originatingSystem;
 }

 @JsonProperty("product_name")
 public String getProductName() {
  return productName;
 }

 @JsonProperty("product_name")
 public void setProductName(String productName) {
  this.productName = productName;
 }

 @JsonProperty("product_description")
 public String getProductDescription() {
  return productDescription;
 }

 @JsonProperty("product_description")
 public void setProductDescription(String productDescription) {
  this.productDescription = productDescription;
 }

 @JsonProperty("brand")
 public String getBrand() {
  return brand;
 }

 @JsonProperty("brand")
 public void setBrand(String brand) {
  this.brand = brand;
 }

 @JsonProperty("segment")
 public String getSegment() {
  return segment;
 }

 @JsonProperty("segment")
 public void setSegment(String segment) {
  this.segment = segment;
 }

 @JsonProperty("status")
 public String getStatus() {
  return status;
 }

 @JsonProperty("status")
 public void setStatus(String status) {
  this.status = status;
 }

 @JsonProperty("premium_amount")
 public String getPremiumAmount() {
  return premiumAmount;
 }

 @JsonProperty("premium_amount")
 public void setPremiumAmount(String premiumAmount) {
  this.premiumAmount = premiumAmount;
 }

 @JsonProperty("premium_frequency")
 public String getPremiumFrequency() {
  return premiumFrequency;
 }

 @JsonProperty("premium_frequency")
 public void setPremiumFrequency(String premiumFrequency) {
  this.premiumFrequency = premiumFrequency;
 }

 @JsonProperty("role_players")
 public List<String> getRolePlayers() {
  return rolePlayers;
 }

 @JsonProperty("role_players")
 public void setRolePlayers(List<String> rolePlayers) {
  this.rolePlayers = rolePlayers;
 }

 @JsonProperty("business_type")
 public String getBusinessType() {
  return businessType;
 }

 @JsonProperty("business_type")
 public void setBusinessType(String businessType) {
  this.businessType = businessType;
 }

 @JsonProperty("business_key")
 public String getBusinessKey() {
  return businessKey;
 }

 @JsonProperty("business_key")
 public void setBusinessKey(String businessKey) {
  this.businessKey = businessKey;
 }

 @JsonProperty("status_description")
 public String getStatusDescription() {
  return statusDescription;
 }

 @JsonProperty("status_description")
 public void setStatusDescription(String statusDescription) {
  this.statusDescription = statusDescription;
 }

 @JsonAnyGetter
 public Map<String, Object> getAdditionalProperties() {
  return this.additionalProperties;
 }

 @JsonAnySetter
 public void setAdditionalProperty(String name, Object value) {
  this.additionalProperties.put(name, value);
 }

 @Override
 public String toString() {
  return "PortfolioDocument{" +
      "agreementNumber='" + agreementNumber + '\'' +
      ", originatingSystem='" + originatingSystem + '\'' +
      ", productName='" + productName + '\'' +
      ", productDescription='" + productDescription + '\'' +
      ", brand='" + brand + '\'' +
      ", segment='" + segment + '\'' +
      ", status='" + status + '\'' +
      ", premiumAmount='" + premiumAmount + '\'' +
      ", premiumFrequency='" + premiumFrequency + '\'' +
      ", rolePlayers=" + rolePlayers +
      ", businessType='" + businessType + '\'' +
      ", businessKey='" + businessKey + '\'' +
      ", statusDescription='" + statusDescription + '\'' +
      ", additionalProperties=" + additionalProperties +
      '}';
 }

}