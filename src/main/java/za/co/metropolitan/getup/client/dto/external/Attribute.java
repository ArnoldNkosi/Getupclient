package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "party_type",
    "natural_party",
    "link",
    "agreements",
    "business_description"
})
public class Attribute  implements Serializable {

 @JsonProperty("party_type")
 private String partyType;
 @JsonProperty("natural_party")
 private NaturalParty naturalParty;
 @JsonProperty("link")
 private Link link;
 @JsonProperty("agreements")
 private List<Object> agreements = null;
 @JsonProperty("business_description")
 private BusinessDescription businessDescription;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("party_type")
 public String getPartyType() {
  return partyType;
 }

 @JsonProperty("party_type")
 public void setPartyType(String partyType) {
  this.partyType = partyType;
 }

 @JsonProperty("natural_party")
 public NaturalParty getNaturalParty() {
  return naturalParty;
 }

 @JsonProperty("natural_party")
 public void setNaturalParty(NaturalParty naturalParty) {
  this.naturalParty = naturalParty;
 }

 @JsonProperty("link")
 public Link getLink() {
  return link;
 }

 @JsonProperty("link")
 public void setLink(Link link) {
  this.link = link;
 }

 @JsonProperty("agreements")
 public List<Object> getAgreements() {
  return agreements;
 }

 @JsonProperty("agreements")
 public void setAgreements(List<Object> agreements) {
  this.agreements = agreements;
 }

 @JsonProperty("business_description")
 public BusinessDescription getBusinessDescription() {
  return businessDescription;
 }

 @JsonProperty("business_description")
 public void setBusinessDescription(BusinessDescription businessDescription) {
  this.businessDescription = businessDescription;
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
  return "Attribute{" +
      "partyType='" + partyType + '\'' +
      ", naturalParty=" + naturalParty +
      ", link=" + link +
      ", agreements=" + agreements +
      ", businessDescription=" + businessDescription +
      ", additionalProperties=" + additionalProperties +
      '}';
 }
}