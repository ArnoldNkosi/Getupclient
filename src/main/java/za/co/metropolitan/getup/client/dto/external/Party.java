package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "guid",
        "attributes",
        "partyId",
        "copied"
})
public class Party  implements Serializable {

 @JsonProperty("guid")
 private String guid;
 @JsonProperty("attributes")
 private List<Attribute> attributes = null;
 @JsonProperty("partyId")
 private String partyId;
 @JsonProperty("copied")
 private Boolean copied;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("guid")
 public String getGuid() {
  return guid;
 }

 @JsonProperty("guid")
 public void setGuid(String guid) {
  this.guid = guid;
 }

 @JsonProperty("attributes")
 public List<Attribute> getAttributes() {
  return attributes;
 }

 @JsonProperty("attributes")
 public void setAttributes(List<Attribute> attributes) {
  this.attributes = attributes;
 }

 @JsonProperty("partyId")
 public String getPartyId() {
  return partyId;
 }

 @JsonProperty("partyId")
 public void setPartyId(String partyId) {
  this.partyId = partyId;
 }

 @JsonProperty("copied")
 public Boolean getCopied() {
  return copied;
 }

 @JsonProperty("copied")
 public void setCopied(Boolean copied) {
  this.copied = copied;
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
  return "Party{" +
          "guid='" + guid + '\'' +
          ", attributes=" + attributes +
          ", partyId='" + partyId + '\'' +
          ", copied=" + copied +
          ", additionalProperties=" + additionalProperties +
          '}';
 }
}