package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "business_type",
    "brand",
    "segment",
    "white_label",
    "division",
    "country_code",
    "data_sensitivity",
    "description"
})
public class BusinessDescription {

 @JsonProperty("business_type")
 private Integer businessType;
 @JsonProperty("brand")
 private String brand;
 @JsonProperty("segment")
 private String segment;
 @JsonProperty("white_label")
 private String whiteLabel;
 @JsonProperty("division")
 private String division;
 @JsonProperty("country_code")
 private String countryCode;
 @JsonProperty("data_sensitivity")
 private String dataSensitivity;
 @JsonProperty("description")
 private String description;
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

 @JsonProperty("white_label")
 public String getWhiteLabel() {
  return whiteLabel;
 }

 @JsonProperty("white_label")
 public void setWhiteLabel(String whiteLabel) {
  this.whiteLabel = whiteLabel;
 }

 @JsonProperty("division")
 public String getDivision() {
  return division;
 }

 @JsonProperty("division")
 public void setDivision(String division) {
  this.division = division;
 }

 @JsonProperty("country_code")
 public String getCountryCode() {
  return countryCode;
 }

 @JsonProperty("country_code")
 public void setCountryCode(String countryCode) {
  this.countryCode = countryCode;
 }

 @JsonProperty("data_sensitivity")
 public String getDataSensitivity() {
  return dataSensitivity;
 }

 @JsonProperty("data_sensitivity")
 public void setDataSensitivity(String dataSensitivity) {
  this.dataSensitivity = dataSensitivity;
 }

 @JsonProperty("description")
 public String getDescription() {
  return description;
 }

 @JsonProperty("description")
 public void setDescription(String description) {
  this.description = description;
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
  return "BusinessDescription{" +
      "businessType=" + businessType +
      ", brand='" + brand + '\'' +
      ", segment='" + segment + '\'' +
      ", whiteLabel='" + whiteLabel + '\'' +
      ", division='" + division + '\'' +
      ", countryCode='" + countryCode + '\'' +
      ", dataSensitivity='" + dataSensitivity + '\'' +
      ", description='" + description + '\'' +
      ", additionalProperties=" + additionalProperties +
      '}';
 }
}
