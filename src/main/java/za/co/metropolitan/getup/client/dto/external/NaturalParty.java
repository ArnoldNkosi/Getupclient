package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id_number",
    "id_type",
    "title",
    "first_name",
    "last_name",
    "initials",
    "date_of_birth",
    "gender",
    "audit_user",
    "audit_time"
})
public class NaturalParty {

 @JsonProperty("id_number")
 private String idNumber;
 @JsonProperty("id_type")
 private String idType;
 @JsonProperty("title")
 private String title;
 @JsonProperty("first_name")
 private String firstName;
 @JsonProperty("last_name")
 private String lastName;
 @JsonProperty("initials")
 private String initials;
 @JsonProperty("date_of_birth")
 private String dateOfBirth;
 @JsonProperty("gender")
 private String gender;
 @JsonProperty("audit_user")
 private String auditUser;
 @JsonProperty("audit_time")
 private String auditTime;
 @JsonIgnore
 private Map<String, Object> additionalProperties = new HashMap<>();

 @JsonProperty("id_number")
 public String getIdNumber() {
  return idNumber;
 }

 @JsonProperty("id_number")
 public void setIdNumber(String idNumber) {
  this.idNumber = idNumber;
 }

 @JsonProperty("id_type")
 public String getIdType() {
  return idType;
 }

 @JsonProperty("id_type")
 public void setIdType(String idType) {
  this.idType = idType;
 }

 @JsonProperty("title")
 public String getTitle() {
  return title;
 }

 @JsonProperty("title")
 public void setTitle(String title) {
  this.title = title;
 }

 @JsonProperty("first_name")
 public String getFirstName() {
  return firstName;
 }

 @JsonProperty("first_name")
 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 @JsonProperty("last_name")
 public String getLastName() {
  return lastName;
 }

 @JsonProperty("last_name")
 public void setLastName(String lastName) {
  this.lastName = lastName;
 }

 @JsonProperty("initials")
 public String getInitials() {
  return initials;
 }

 @JsonProperty("initials")
 public void setInitials(String initials) {
  this.initials = initials;
 }

 @JsonProperty("date_of_birth")
 public String getDateOfBirth() {
  return dateOfBirth;
 }

 @JsonProperty("date_of_birth")
 public void setDateOfBirth(String dateOfBirth) {
  this.dateOfBirth = dateOfBirth;
 }

 @JsonProperty("gender")
 public String getGender() {
  return gender;
 }

 @JsonProperty("gender")
 public void setGender(String gender) {
  this.gender = gender;
 }

 @JsonProperty("audit_user")
 public String getAuditUser() {
  return auditUser;
 }

 @JsonProperty("audit_user")
 public void setAuditUser(String auditUser) {
  this.auditUser = auditUser;
 }

 @JsonProperty("audit_time")
 public String getAuditTime() {
  return auditTime;
 }

 @JsonProperty("audit_time")
 public void setAuditTime(String auditTime) {
  this.auditTime = auditTime;
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
  return "NaturalParty{" +
      "idNumber='" + idNumber + '\'' +
      ", idType='" + idType + '\'' +
      ", title='" + title + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", initials='" + initials + '\'' +
      ", dateOfBirth='" + dateOfBirth + '\'' +
      ", gender='" + gender + '\'' +
      ", auditUser='" + auditUser + '\'' +
      ", auditTime='" + auditTime + '\'' +
      ", additionalProperties=" + additionalProperties +
      '}';
 }
}