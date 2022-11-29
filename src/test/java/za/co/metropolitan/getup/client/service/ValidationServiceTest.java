package za.co.metropolitan.getup.client.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import za.co.metropolitan.getup.client.GetupClientApplication;
import za.co.metropolitan.getup.client.dto.ClientDetailsDto;
import za.co.metropolitan.getup.client.dto.ClientListRequestDto;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GetupClientApplication.class)
public class ValidationServiceTest {

 @Autowired
 private ValidationService service;

 @Test(expected = InvalidRequestException.class)
 public void testIsValidClientIdNumberInvalidNull() {
  String ID_NUMBER = null;
  service.isValidClientIdNumber(ID_NUMBER);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidClientIdNumberInvalidEmpty() {
  String ID_NUMBER = "";
  service.isValidClientIdNumber(ID_NUMBER);
 }

 @Test
 public void testIsValidClientIdNumber() {
  String ID_NUMBER = "1234567890";
  assertTrue(service.isValidClientIdNumber(ID_NUMBER));
 }

 @Test(expected = InvalidRequestException.class)
 public void testHasValidSearchRequirementsAllEmpty() {
  ClientListRequestDto request = new ClientListRequestDto();
  request.setIdNumber("");
  request.setName("");
  request.setSurname("");
  request.setInitials("");
  service.hasValidSearchRequirements(request);
 }

 @Test
 public void testHasValidSearchRequirementsIdNumberEmpty() {
  ClientListRequestDto request = new ClientListRequestDto();
  request.setIdNumber("");
  request.setName("Name");
  request.setSurname("Surname");
  request.setInitials("NS");
  assertTrue(service.hasValidSearchRequirements(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidClientIdTypeInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setIdType(null);
  service.isValidClientIdType(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidClientIdTypeInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setIdType("");
  service.isValidClientIdType(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidClientIdTypeInvalidType() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setIdType("Id Type");
  service.isValidClientIdType(request);
 }

 @Test
 public void testIsValidClientIdTypeValidType() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setIdType("National ID");
  assertTrue(service.isValidClientIdType(request));
  request.setIdType("Passport");
  assertTrue(service.isValidClientIdType(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidDateOfBirthInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setDateOfBirth(null);
  service.isValidDateOfBirth(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidDateOfBirthInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setDateOfBirth("");
  service.isValidDateOfBirth(request);
 }

 @Test
 public void testIsValidDateOfBirth() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setDateOfBirth("1990-01-01T00:00:00.000Z");
  assertTrue(service.isValidDateOfBirth(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidFirstNameInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setFirstName(null);
  service.isValidFirstName(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidFirstNameInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setFirstName("");
  service.isValidFirstName(request);
 }

 @Test
 public void testIsValidFirstName() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setFirstName("John");
  assertTrue(service.isValidFirstName(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidSurnameInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setSurname(null);
  service.isValidSurname(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidSurnameInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setSurname("");
  service.isValidSurname(request);
 }

 @Test
 public void testIsValidSurname() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setSurname("Smith");
  assertTrue(service.isValidSurname(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidInitialsInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setInitials(null);
  service.isValidInitials(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidInitialsInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setInitials("");
  service.isValidInitials(request);
 }

 @Test
 public void testIsValidInitials() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setInitials("J");
  assertTrue(service.isValidInitials(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidTitleInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setTitle(null);
  service.isValidInitials(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidTitleInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setInitials("");
  service.isValidInitials(request);
 }

 @Test
 public void testIsValidTitle() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setTitle("Miss");
  assertTrue(service.isValidTitle(request));
  request.setTitle("Mr");
  assertTrue(service.isValidTitle(request));
  request.setTitle("Mrs");
  assertTrue(service.isValidTitle(request));
  request.setTitle("Ms");
  assertTrue(service.isValidTitle(request));
  request.setTitle("Sir");
  assertTrue(service.isValidTitle(request));
  request.setTitle("Dr");
  assertTrue(service.isValidTitle(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidGenderInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setGender(null);
  service.isValidGender(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidGenderInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setGender("");
  service.isValidGender(request);
 }

 @Test
 public void testIsValidGender() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setGender("Male");
  assertTrue(service.isValidGender(request));
  request.setGender("Female");
  assertTrue(service.isValidGender(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMaritalStatusInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setMaritalStatus(null);
  assertTrue(service.isValidGender(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMaritalStatusInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setGender("");
  assertTrue(service.isValidGender(request));
 }

 @Test
 public void testIsValidMaritalStatus() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setMaritalStatus("Divorced");
  assertTrue(service.isValidMaritalStatus(request));
  request.setMaritalStatus("Separated");
  assertTrue(service.isValidMaritalStatus(request));
  request.setMaritalStatus("Single");
  assertTrue(service.isValidMaritalStatus(request));
  request.setMaritalStatus("Widowed");
  assertTrue(service.isValidMaritalStatus(request));
  request.setMaritalStatus("Married");
  assertTrue(service.isValidMaritalStatus(request));
  request.setMaritalStatus("Unknown");
  assertTrue(service.isValidMaritalStatus(request));
 }

//	@Test(expected = InvalidRequestException.class)
//	public void testIsValidEthnicityInvalidNull() {
//		ClientDetailsDto request = new ClientDetailsDto();
//		request.setEthnicity(null);
//		service.isValidEthnicity(request);
//	}
//
//	@Test(expected = InvalidRequestException.class)
//	public void testIsValidEthnicityInvalidEmpty() {
//		ClientDetailsDto request = new ClientDetailsDto();
//		request.setEthnicity("");
//		service.isValidEthnicity(request);
//	}

 @Test
 public void testIsValidEthnicity() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setEthnicity("Black");
  assertTrue(service.isValidEthnicity(request));
  request.setEthnicity("Coloured");
  assertTrue(service.isValidEthnicity(request));
  request.setEthnicity("Indian");
  assertTrue(service.isValidEthnicity(request));
  request.setEthnicity("Asian");
  assertTrue(service.isValidEthnicity(request));
  request.setEthnicity("White");
  assertTrue(service.isValidEthnicity(request));
 }

//	@Test(expected = InvalidRequestException.class)
//	public void testIsValidEducationLevelInvalidNull() {
//		ClientDetailsDto request = new ClientDetailsDto();
//		request.setEducationLevel(null);
//		service.isValidEducationLevel(request);
//	}
//
//	@Test(expected = InvalidRequestException.class)
//	public void testIsValidEducationLevelnvalidEmpty() {
//		ClientDetailsDto request = new ClientDetailsDto();
//		request.setEducationLevel("");
//		service.isValidEducationLevel(request);
//	}

 @Test
 public void testIsValidEducationLevel() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setEducationLevel("Not Matriculated / Up to Grade 9");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Not Matriculated / Up to Grade 10");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Not Matriculated / Up to Grade 11 / N3");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Matriculated / Up to Grade 12 / N4 ");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Higher Certificates / N5 / N6");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("National Diploma / Advanced Certificates");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Bachelor's Degree / Advanced Diploma / Post Graduate Certificate / B-Tech");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Honours Degree / Post Graduate Diploma / Professional Qualification");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Master's Degree");
  assertTrue(service.isValidEducationLevel(request));
  request.setEducationLevel("Doctor's Degree");
  assertTrue(service.isValidEducationLevel(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidUserSignOnInvalidNull() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setUserSignOn(null);
  service.isValidUserSignOn(request);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidUserSignOnInvalidEmpty() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setUserSignOn("");
  service.isValidUserSignOn(request);
 }

 @Test
 public void testIsValidUserSignOn() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setUserSignOn("user");
  assertTrue(service.isValidUserSignOn(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMarketingConsentInvalidNull() {
  String MARKET_CONSENT_TYPE = null;
  service.isValidMarketingConsent(MARKET_CONSENT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMarketingConsentInvalidEmpty() {
  String MARKET_CONSENT_TYPE = "";
  service.isValidMarketingConsent(MARKET_CONSENT_TYPE);
 }

 @Test
 public void testIsValidMarketingConsent() {
  String MARKET_CONSENT_TYPE = "New Solutions";
  assertTrue(service.isValidMarketingConsent(MARKET_CONSENT_TYPE));
  MARKET_CONSENT_TYPE = "Share Info Internally";
  assertTrue(service.isValidMarketingConsent(MARKET_CONSENT_TYPE));
 }

 @Test
 public void testIsValidPersonClient() {
  ClientDetailsDto request = new ClientDetailsDto();
  request.setIdNumber("1234567890");
  request.setIdType("National ID");
  request.setDateOfBirth("1990-01-01T00:00:00.000Z");
  request.setFirstName("John");
  request.setSurname("Smith");
  request.setInitials("J");
  request.setTitle("Mr");
  request.setGender("Male");
  request.setMaritalStatus("Single");
  request.setEthnicity("Black");
  request.setEducationLevel("Not Matriculated / Up to Grade 9");
  request.setUserSignOn("user");
  assertTrue(service.isValidPersonClient(request));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMarketingConsentLookupInvalidNull() {
  String MARKET_CONSENT_TYPE = null;
  service.isValidMarketingConsentLookup(MARKET_CONSENT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMarketingConsentLookupInvalidEmpty() {
  String MARKET_CONSENT_TYPE = "";
  service.isValidMarketingConsentLookup(MARKET_CONSENT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidMarketingConsentLookupInvalidLookup() {
  String MARKET_CONSENT_TYPE = "";
  service.isValidMarketingConsentLookup(MARKET_CONSENT_TYPE);
 }

 @Test
 public void testIsValidMarketingConsentLookup() {
  String MARKET_CONSENT_TYPE = "New Solutions";
  assertTrue(service.isValidMarketingConsentLookup(MARKET_CONSENT_TYPE));
  MARKET_CONSENT_TYPE = "Share Info Internally";
  assertTrue(service.isValidMarketingConsentLookup(MARKET_CONSENT_TYPE));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidContactTimeLookupInvalidNull() {
  String CONTACT_TIME = null;
  service.isValidContactTimeLookup(CONTACT_TIME);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidContactTimeLookupInvalidEmpty() {
  String CONTACT_TIME = "";
  service.isValidContactTimeLookup(CONTACT_TIME);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidContactTimeLookupInvalidLookup() {
  String CONTACT_TIME = "01h00 - 02h00";
  service.isValidContactTimeLookup(CONTACT_TIME);
 }

 @Test
 public void testIsValidContactTimeLookup() {
  String CONTACT_TIME = "09h00 - 10h00";
  assertTrue(service.isValidContactTimeLookup(CONTACT_TIME));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidEmploymentTypeLookupIvalidNull() {
  String EMPLOYMENT_TYPE = null;
  service.isValidEmploymentTypeLookup(EMPLOYMENT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidEmploymentTypeLookupInvalidEmpty() {
  String EMPLOYMENT_TYPE = "";
  service.isValidEmploymentTypeLookup(EMPLOYMENT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidEmploymentTypeLookupInvalidLookup() {
  String EMPLOYMENT_TYPE = "Unknown";
  service.isValidEmploymentTypeLookup(EMPLOYMENT_TYPE);
 }

 @Test
 public void testIsValidEmploymentTypeLookup() {
  String EMPLOYMENT_TYPE = "Contractor";
  assertTrue(service.isValidEmploymentTypeLookup(EMPLOYMENT_TYPE));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidIndustryLookupInvalidNull() {
  String INDUSTRY = null;
  service.isValidIndustryLookup(INDUSTRY);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidIndustryLookupInvalidEmpty() {
  String INDUSTRY = "";
  service.isValidIndustryLookup(INDUSTRY);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidIndustryLookupInvalidLookup() {
  String INDUSTRY = "Industry";
  service.isValidIndustryLookup(INDUSTRY);
 }

 @Test
 public void testIsValidIndustryLookup() {
  String INDUSTRY = "Manufacturing";
  assertTrue(service.isValidIndustryLookup(INDUSTRY));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidOccupationLookupInvalidNull() {
  String OCCUPATION = null;
  service.isValidOccupationLookup(OCCUPATION);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidOccupationLookupInvalidEmpty() {
  String OCCUPATION = "";
  service.isValidOccupationLookup(OCCUPATION);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsValidOccupationLookupInvalidLookup() {
  String OCCUPATION = "Occupation";
  service.isValidOccupationLookup(OCCUPATION);
 }

 @Test
 public void testIsValidOccupationLookup() {
  String OCCUPATION = "ACCOUNTANT";
  assertTrue(service.isValidOccupationLookup(OCCUPATION));
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsContactTypeLookupInvalidNull() {
  String CONTACT_TYPE = null;
  service.isValidContactTimeLookup(CONTACT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsContactTypeLookupInvalidEmpty() {
  String CONTACT_TYPE = "";
  service.isValidContactTypeLookup(CONTACT_TYPE);
 }

 @Test(expected = InvalidRequestException.class)
 public void testIsContactTypeLookupInvalidLookup() {
  String CONTACT_TYPE = "";
  service.isValidContactTypeLookup(CONTACT_TYPE);
 }

 @Test
 public void testIsContactTypeLookup() {
  String CONTACT_TYPE = "Cellphone";
  assertTrue(service.isValidContactTypeLookup(CONTACT_TYPE));
  CONTACT_TYPE = "Email Address";
  assertTrue(service.isValidContactTypeLookup(CONTACT_TYPE));
 }

 @Test
 public void createJsonFromObj(){
  String obj = "{quotePackageId='3c394700-7744-44f2-b486-2edcc8d0c1af', mainMember=Person{firstName='Nicholus', lastName='Luyelyele ', initials='N', email='Thabile17@gmail.com', cellphone='0731339177', idNumber='8704046044086', age=0, dateOfBirth='1987-04-04', gender='male', income=null, medicalAid=null, maritalStatus='single', industryOfEmployment='null', province='null', educationLevel='null', premium=null, coverAmount=null, previousCoverAmount=null, relation='null', uniqueIdentifier='null'}, additionalInformation=AdditionalInfo{channel='Digital Direct', subChannel='Met GetUp Website', externalReference='Metgetup', adId='null', adPublishPlatform='null', pip='true', leadProvider='', leadId=''}, paymentMethod='Debit Order', bankDetails=BankDetails{accountHolder='Nicholus Luyelyele ', bank='capitec', branchCode='470010', accountNumber='1410215936', bankStatus='null'}, beneficiaries=[Beneficiary{firstName='Phathiswa', lastName='Gxula', relationship='other', cellphone='0635441647', percentage=100, dateOfBirth='1998-08-08'}], billingDay=25, contactPreference='Thabile17@gmail.com', preferredContactTime='12h00 - 13h00', contactMe=true, shareMyData=true, marketResearch=null, pip=null, createPaymentMethod=true}";
 }

}
