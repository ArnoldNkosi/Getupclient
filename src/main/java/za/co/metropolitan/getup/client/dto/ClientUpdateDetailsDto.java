package za.co.metropolitan.getup.client.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class ClientUpdateDetailsDto {

 private BigInteger clientID;
 private String getupClientNumber;
 private String idNumber;
 private String idType;
 private String dateOfBirth;
 private String firstName;
 private String surname;
 private String initials;
 private String title;
 private String gender;
 private String maritalStatus;
 private String ethnicity;
 private String educationLevel;
 private String userSignOn;
 private Timestamp createdAt;
 private List<ClientContactDetailsDto> contactDetails;
 private List<ClientLifeHackDetailsDto> lifeHackDetails;
 private EmploymentDetailsDto employmentDetails;
 private List<ClientMarketConsentDetailsDto> marketingConsentDetails;
 private ClientPreferenceDetailsDto clientPreferenceDetails;


 public String getGetupClientNumber() {
  return getupClientNumber;
 }

 public void setGetupClientNumber(String getupClientNumber) {
  this.getupClientNumber = getupClientNumber;
 }

 public String getIdNumber() {
  return idNumber;
 }

 public void setIdNumber(String idNumber) {
  this.idNumber = idNumber;
 }

 public String getIdType() {
  return idType;
 }

 public void setIdType(String idType) {
  this.idType = idType;
 }

 public String getDateOfBirth() {
  return dateOfBirth;
 }

 public void setDateOfBirth(String dateOfBirth) {
  this.dateOfBirth = dateOfBirth;
 }

 public String getFirstName() {
  return firstName;
 }

 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 public String getSurname() {
  return surname;
 }

 public void setSurname(String surname) {
  this.surname = surname;
 }

 public String getInitials() {
  return initials;
 }

 public void setInitials(String initials) {
  this.initials = initials;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getGender() {
  return gender;
 }

 public void setGender(String gender) {
  this.gender = gender;
 }

 public String getMaritalStatus() {
  return maritalStatus;
 }

 public void setMaritalStatus(String maritalStatus) {
  this.maritalStatus = maritalStatus;
 }

 public String getEthnicity() {
  return ethnicity;
 }

 public void setEthnicity(String ethnicity) {
  this.ethnicity = ethnicity;
 }

 public String getEducationLevel() {
  return educationLevel;
 }

 public void setEducationLevel(String educationLevel) {
  this.educationLevel = educationLevel;
 }

 public String getUserSignOn() {
  return userSignOn;
 }

 public void setUserSignOn(String userSignOn) {
  this.userSignOn = userSignOn;
 }

 public Timestamp getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(Timestamp createdAt) {
  this.createdAt = createdAt;
 }

 public List<ClientContactDetailsDto> getContactDetails() {
  return contactDetails;
 }

 public void setContactDetails(List<ClientContactDetailsDto> contactDetails) {
  this.contactDetails = contactDetails;
 }

 public List<ClientLifeHackDetailsDto> getLifeHackDetails() {
  return lifeHackDetails;
 }

 public void setLifeHackDetails(List<ClientLifeHackDetailsDto> lifeHackDetails) {
  this.lifeHackDetails = lifeHackDetails;
 }

 public EmploymentDetailsDto getEmploymentDetails() {
  return employmentDetails;
 }

 public void setEmploymentDetails(EmploymentDetailsDto employmentDetails) {
  this.employmentDetails = employmentDetails;
 }

 public List<ClientMarketConsentDetailsDto> getMarketingConsentDetails() {
  return marketingConsentDetails;
 }

 public void setMarketingConsentDetails(List<ClientMarketConsentDetailsDto> marketingConsentDetails) {
  this.marketingConsentDetails = marketingConsentDetails;
 }

 public ClientPreferenceDetailsDto getClientPreferenceDetails() {
  return clientPreferenceDetails;
 }

 public void setClientPreferenceDetails(ClientPreferenceDetailsDto clientPreferenceDetails) {
  this.clientPreferenceDetails = clientPreferenceDetails;
 }

 public BigInteger getClientID() {
  return clientID;
 }

 public void setClientID(BigInteger clientID) {
  this.clientID = clientID;
 }

 @Override
 public String toString() {
  return "ClientUpdateDetailsDto{" +
      "clientID=" + clientID +
      ", getupClientNumber='" + getupClientNumber + '\'' +
      ", idNumber='" + idNumber + '\'' +
      ", idType='" + idType + '\'' +
      ", dateOfBirth='" + dateOfBirth + '\'' +
      ", firstName='" + firstName + '\'' +
      ", surname='" + surname + '\'' +
      ", initials='" + initials + '\'' +
      ", title='" + title + '\'' +
      ", gender='" + gender + '\'' +
      ", maritalStatus='" + maritalStatus + '\'' +
      ", ethnicity='" + ethnicity + '\'' +
      ", educationLevel='" + educationLevel + '\'' +
      ", userSignOn='" + userSignOn + '\'' +
      ", createdAt=" + createdAt +
      ", contactDetails=" + contactDetails +
      ", lifeHackDetails=" + lifeHackDetails +
      ", employmentDetails=" + employmentDetails +
      ", marketingConsentDetails=" + marketingConsentDetails +
      ", clientPreferenceDetails=" + clientPreferenceDetails +
      '}';
 }
}
