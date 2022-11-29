package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "business_type",
    "business_key"
})
public class Link {

 @JsonProperty("business_type")
 private Integer businessType;
 @JsonProperty("business_key")
 private String businessKey;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("business_type")
 public Integer getBusinessType() {
  return businessType;
 }

 @JsonProperty("business_type")
 public void setBusinessType(Integer businessType) {
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
  return "Link{" +
      "businessType=" + businessType +
      ", businessKey='" + businessKey + '\'' +
      ", additionalProperties=" + additionalProperties +
      '}';
 }
}