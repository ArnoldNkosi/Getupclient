package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "businessType",
    "businessKey",
    "portfolioDocuments",
    "createdTime"
})
public class PortfolioRecord {

 @JsonProperty("businessType")
 private Integer businessType;
 @JsonProperty("businessKey")
 private String businessKey;
 @JsonProperty("portfolioDocuments")
 private List<PortfolioDocument> portfolioDocuments = null;
 @JsonProperty("createdTime")
 private String createdTime;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("businessType")
 public Integer getBusinessType() {
  return businessType;
 }

 @JsonProperty("businessType")
 public void setBusinessType(Integer businessType) {
  this.businessType = businessType;
 }

 @JsonProperty("businessKey")
 public String getBusinessKey() {
  return businessKey;
 }

 @JsonProperty("businessKey")
 public void setBusinessKey(String businessKey) {
  this.businessKey = businessKey;
 }

 @JsonProperty("portfolioDocuments")
 public List<PortfolioDocument> getPortfolioDocuments() {
  return portfolioDocuments;
 }

 @JsonProperty("portfolioDocuments")
 public void setPortfolioDocuments(List<PortfolioDocument> portfolioDocuments) {
  this.portfolioDocuments = portfolioDocuments;
 }

 @JsonProperty("createdTime")
 public String getCreatedTime() {
  return createdTime;
 }

 @JsonProperty("createdTime")
 public void setCreatedTime(String createdTime) {
  this.createdTime = createdTime;
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
  return "PortfolioRecord{" +
      "businessType=" + businessType +
      ", businessKey='" + businessKey + '\'' +
      ", portfolioDocuments=" + portfolioDocuments +
      ", createdTime=" + createdTime +
      ", additionalProperties=" + additionalProperties +
      '}';
 }
}
