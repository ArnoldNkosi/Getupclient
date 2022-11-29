package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "parties",
    "search_type"
})
public class SearchPartyResponse {

 @JsonProperty("parties")
 private List<Party> parties = null;
 @JsonProperty("search_type")
 private String searchType;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("parties")
 public List<Party> getParties() {
  return parties;
 }

 @JsonProperty("parties")
 public void setParties(List<Party> parties) {
  this.parties = parties;
 }

 @JsonProperty("search_type")
 public String getSearchType() {
  return searchType;
 }

 @JsonProperty("search_type")
 public void setSearchType(String searchType) {
  this.searchType = searchType;
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
  return "SearchPartyResponse{" +
      "parties=" + parties +
      ", searchType='" + searchType + '\'' +
      ", additionalProperties=" + additionalProperties +
      '}';
 }
}