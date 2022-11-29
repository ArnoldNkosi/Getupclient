package za.co.metropolitan.getup.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.metropolitan.getup.client.GetupClientApplication;
import za.co.metropolitan.getup.client.dto.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GetupClientApplication.class)
public class ClientDetailServiceTest {

 @Autowired
 private ClientDetailService service;

//	@MockBean
//	private PersonClientRepository clientRepository;
//	@MockBean
//	private ClientContactRepository clientContactRepository;
//	@MockBean
//	private ContactTypeLookupRepository contactTypeRepository;
//	@MockBean
//	private ClientEmploymentDetailsRepository employmentRepository;
//	@MockBean
//	private EmploymentTypeLookupRepository employmentTypeRepository;
//	@MockBean
//	private IndustryLookupRepository industryRepository;
//	@MockBean
//	private OccupationLookupRepository occupationRepository;
//	@MockBean
//	private ClientMarketConsentRepository marketConsentRepository;
//	@MockBean
//	private MarketingConsentLookupRepository consentLookupRepository;
//	@MockBean
//	private ClientPrefContactTimeRepository contactPrefRepository;
//	@MockBean
//	private ContactTimeLookupRepository contactTimeLookupRepository;
//	@MockBean
//	private ClientLifeHackRepository lifeHackRepository;
//	@MockBean
//	private ProductLifeHackLookupRepository lifeHackProductLookupRepository;
 //
//	@MockBean
//	private ClientMarketConsentHistoryRepository clientMarketConsetHistoryRepository;
//	@MockBean
//	private ClientPrefContactTimeHistoryRepository clientPrefContactHistoryRepository;
//	@MockBean
//	private ClientLifeHackHistoryRepository lifeHackHistoryRepository;
//	@MockBean
//	private ClientEmploymentDetailsHistoryRepository clientEmploymentDetailsHistoryRepository;
//	@MockBean
//	private PersonClientHistoryRepository personClientHistoryRepository;
//	@MockBean
//	private ClientContactHistoryRepository clientContactHistoryRepository;

//	@Test
//	public void contextLoads() {
//
////		String encodedPassword = bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());
//		String encodedPassword1111 = new BCryptPasswordEncoder().encode("1111");
//		String encodedPassword2222 = new BCryptPasswordEncoder().encode("2222");
//
//		System.out.println("encodedPassword1111: " + encodedPassword1111);
//		System.out.println("encodedPassword2222: " + encodedPassword2222);
//
//	}

/* @Before
 public void setup() {
  // load client

 }*/

/* @After
 public void cleanup() {

 }*/

/* @Test
 public void testUpdateMarketingConsentInsert() {

//		ClientMarketConsent currentConsent = marketConsentRepository.findByIdAndClientId(dto.getId(), dto.getClientId());

//		doReturn()

  ClientMarketConsentDetailsDto consent_1 = new ClientMarketConsentDetailsDto();
//		consent_1.setId(new BigInteger("1"));
  consent_1.setClientId(new BigInteger("2"));
  consent_1.setConsentType("Share Info Internally");
  consent_1.setUserSignOn("TEST_USER");

  ClientMarketConsentDetailsDto consent_2 = new ClientMarketConsentDetailsDto();
//		consent_2.setId(new BigInteger("2"));
  consent_2.setClientId(new BigInteger("2"));
  consent_2.setConsentType("New Solutions");
  consent_2.setUserSignOn("TEST_USER");

  List<ClientMarketConsentDetailsDto> request = new ArrayList<>();
  request.add(consent_1);
  request.add(consent_2);

//
  service.updateMarketingConsent(request);

//		Stream<List<String>> consentList1 = Stream.of(consentList);

  // consentList.forEach(System.out::println);

 }*/

/* @Test
 public void test_update_marketing_consent_update() {

  List<ClientMarketConsentDetailsDto> request = new ArrayList<>();
//		request.add("Share Info Internally");
//		request.add("New Solutions");

  BigInteger clientId = new BigInteger("2");
  String userSignOn = "TEST_USER";

  ClientMarketConsentDetailsDto dto = new ClientMarketConsentDetailsDto();
  dto.setId(new BigInteger("15"));
  dto.setClientId(clientId);
  dto.setConsentType("New Solutions");
  dto.setUserSignOn(userSignOn);

  request.add(dto);

  service.updateMarketingConsent(request);

//		Stream<List<String>> consentList1 = Stream.of(consentList);

  // consentList.forEach(System.out::println);

 }*/

/* @Test
 public void test_update_marketing_consent_delete() {

  List<ClientMarketConsentDetailsDto> request = new ArrayList<>();
//		request.add("Share Info Internally");
//		request.add("New Solutions");

  BigInteger clientId = new BigInteger("2");
  String userSignOn = "TEST_USER";

  ClientMarketConsentDetailsDto dto = new ClientMarketConsentDetailsDto();
  dto.setId(new BigInteger("15"));
  dto.setClientId(clientId);
  dto.setConsentType("Share Info Internally");
  dto.setUserSignOn(userSignOn);
  dto.setDelete(true);

  request.add(dto);

  service.updateMarketingConsent(request);

//		Stream<List<String>> consentList1 = Stream.of(consentList);

  // consentList.forEach(System.out::println);

 }*/


/* @Test
 public void test_update_client_pref_insert() {


  List<ClientPrefContactTimeDto> prefList = new ArrayList<>();

  ClientPrefContactTimeDto detail_1 = new ClientPrefContactTimeDto();

  detail_1.setId(new BigInteger("3"));
  detail_1.setClientId(new BigInteger("2"));
  detail_1.setClientContactId(new BigInteger("1"));
//	  detail_1.setContactValue("0214567891");
  detail_1.setContactTime("19h00 - 20h00");
  detail_1.setDelete(Boolean.FALSE);
  detail_1.setUserSignOn("TEST_USER");

  prefList.add(detail_1);

  service.updateContactPreferences(prefList);

 }*/


/* @Test
 public void test_update_client_pref_update() {

  List<ClientPrefContactTimeDto> prefList = new ArrayList<>();

  ClientPrefContactTimeDto detail_1 = new ClientPrefContactTimeDto();

  detail_1.setId(new BigInteger("21"));
  detail_1.setClientId(new BigInteger("2"));
  detail_1.setClientContactId(new BigInteger("1"));
  detail_1.setContactTime("14h00 - 15h00");
  detail_1.setDelete(Boolean.FALSE);
  detail_1.setUserSignOn("TEST_USER");

  prefList.add(detail_1);

  service.updateContactPreferences(prefList);

 }*/


/* @Test
 public void test_update_client_delete() {

  List<ClientPrefContactTimeDto> prefList = new ArrayList<>();

  ClientPrefContactTimeDto detail_1 = new ClientPrefContactTimeDto();


  detail_1.setId(new BigInteger("21"));
  detail_1.setClientId(new BigInteger("2"));
  detail_1.setClientContactId(new BigInteger("1"));
  detail_1.setContactTime("14h00 - 15h00");
  detail_1.setDelete(Boolean.TRUE);
  detail_1.setUserSignOn("TEST_USER");

  prefList.add(detail_1);

  service.updateContactPreferences(prefList);

 }*/

/* @Test
 public void test_update_life_hacks_insert() {

  List<ClientLifeHackDetailsDto> lifeList = new ArrayList<>();

  ClientLifeHackDetailsDto lifeHack_1 = new ClientLifeHackDetailsDto();
//		lifeHack_1.setId(new BigInteger("6"));
  lifeHack_1.setClientId(new BigInteger("2"));
  lifeHack_1.setProduct("Metropolitan GetUp Income Protection");
  lifeHack_1.setLifeHack("Be a loyal employee and earn savings rebate");
  lifeHack_1.setDelete(Boolean.FALSE);
  lifeHack_1.setUserSignOn("TEST_USER");

  lifeList.add(lifeHack_1);

  service.updateLifeHacks(lifeList);

 }*/

/* @Test
 public void test_update_life_hacks_update() {

  List<ClientLifeHackDetailsDto> lifeList = new ArrayList<>();

  ClientLifeHackDetailsDto lifeHack_1 = new ClientLifeHackDetailsDto();
  lifeHack_1.setId(new BigInteger("9"));
  lifeHack_1.setClientId(new BigInteger("2"));
  lifeHack_1.setProduct("Metropolitan GetUp Income Protection");
  lifeHack_1.setLifeHack("Be boss with your credit");
  lifeHack_1.setDelete(Boolean.FALSE);
  lifeHack_1.setUserSignOn("TEST_USER");

  lifeList.add(lifeHack_1);

  service.updateLifeHacks(lifeList);

 }*/

/* @Test
 public void test_update_life_hacks_delete() {

  List<ClientLifeHackDetailsDto> lifeList = new ArrayList<>();

  ClientLifeHackDetailsDto lifeHack_1 = new ClientLifeHackDetailsDto();
  lifeHack_1.setId(new BigInteger("9"));
  lifeHack_1.setClientId(new BigInteger("2"));
  lifeHack_1.setProduct("Metropolitan GetUp Income Protection");
  lifeHack_1.setLifeHack("Be boss with your credit");
  lifeHack_1.setDelete(Boolean.TRUE);
  lifeHack_1.setUserSignOn("TEST_USER");

  lifeList.add(lifeHack_1);

  service.updateLifeHacks(lifeList);

 }*/

/* @Test
 public void test_update_employment_details_insert() {

  EmploymentDetailsDto employmentDetails = new EmploymentDetailsDto();
//		employmentDetails.setid();
  employmentDetails.setClientId(new BigInteger("2"));
  employmentDetails.setEmployerName("Test Employer");
  employmentDetails.setEmploymentType("Permanently Employed");
  employmentDetails.setIndustry("Real estate");
  employmentDetails.setOccupation("ACCOUNTANT");
  employmentDetails.setGrossSalaryAmount(new Double(20000.00));
  employmentDetails.setDelete(Boolean.FALSE);
  employmentDetails.setUserSignOn("TEST_USER");

  service.updateEmploymentDetails(employmentDetails);

 }*/

/* @Test
 public void test_update_employment_details_update() {

  EmploymentDetailsDto employmentDetails = new EmploymentDetailsDto();
  employmentDetails.setId(new BigInteger("16"));
  employmentDetails.setClientId(new BigInteger("2"));
  employmentDetails.setEmployerName("Test Employer");
  employmentDetails.setEmploymentType("Permanently Employed");
  employmentDetails.setIndustry("Higher education and training");
  employmentDetails.setOccupation("ACTUARY");
  employmentDetails.setGrossSalaryAmount(new Double(20000.00));
  employmentDetails.setDelete(Boolean.FALSE);
  employmentDetails.setUserSignOn("TEST_USER");

  service.updateEmploymentDetails(employmentDetails);

 }*/

/* @Test
 public void test_update_employment_details_delete() {

  EmploymentDetailsDto employmentDetails = new EmploymentDetailsDto();
  employmentDetails.setId(new BigInteger("16"));
  employmentDetails.setClientId(new BigInteger("2"));
  employmentDetails.setEmployerName("Test Employer");
  employmentDetails.setEmploymentType("Permanently Employed");
  employmentDetails.setIndustry("Higher education and training");
  employmentDetails.setOccupation("ACTUARY");
  employmentDetails.setGrossSalaryAmount(new Double(20000.00));
  employmentDetails.setDelete(Boolean.TRUE);
  employmentDetails.setUserSignOn("TEST_USER");

  service.updateEmploymentDetails(employmentDetails);

 }*/

/* @Test
 public void test_update_client_contacts_insert() {

  List<ClientContactDetailsDto> contactList = new ArrayList<>();
  ClientContactDetailsDto cDetails = new ClientContactDetailsDto();
  cDetails.setId(new BigInteger("4"));
  cDetails.setClientId(new BigInteger("2"));
  cDetails.setContactType("Email Address");
  cDetails.setContactValue("test@example.com");
  cDetails.setUserSignOn("TEST_USER");

  contactList.add(cDetails);

  service.updateClientContacts(contactList);

 }*/

/* @Test
 public void test_update_client_contacts_update() {

  List<ClientContactDetailsDto> contactList = new ArrayList<>();
  ClientContactDetailsDto cDetails = new ClientContactDetailsDto();
  cDetails.setId(new BigInteger("14"));
  cDetails.setClientId(new BigInteger("2"));
  cDetails.setContactType("Email Address");
  cDetails.setContactValue("newtest@example.com");
  cDetails.setUserSignOn("TEST_USER");

  contactList.add(cDetails);

  service.updateClientContacts(contactList);

 }*/

/* @Test
 public void test_update_client_contacts_delete() {


  List<ClientContactDetailsDto> contactList = new ArrayList<>();
  ClientContactDetailsDto cDetails = new ClientContactDetailsDto();
  cDetails.setId(new BigInteger("14"));
  cDetails.setClientId(new BigInteger("2"));
  cDetails.setContactType("Email Address");
  cDetails.setContactValue("newtest@example.com");
  cDetails.setDelete(Boolean.TRUE);
  cDetails.setUserSignOn("TEST_USER");

  contactList.add(cDetails);

  service.updateClientContacts(contactList);

 }*/

/* @Test
 public void test_update_client_details_update() {

  try {
   ClientDetailsDto client = new ClientDetailsDto();
   client.setClientID(new BigInteger("2"));
   client.setIdNumber("10");
   client.setGetupClientNumber("GU-1001002");
   client.setIdType("National ID");
   client.setDateOfBirth("1990-01-01");
   client.setFirstName("John");
   client.setSurname("Smith");
   client.setInitials("J");
   client.setTitle("Mr");
   client.setGender("Male");
   client.setMaritalStatus("Married");
   client.setEthnicity("Black");
   client.setEducationLevel("Honours Degree / Post Graduate Diploma / Professional Qualification");
   client.setUserSignOn("TEST_USER");

   service.updatePersonClient(client);

  } catch (Exception e) {
   // TODO: handle exception
   e.printStackTrace();
  }

 }*/

/* @Test
 public void test_update_client_details_delete() {

  try {
   ClientDetailsDto client = new ClientDetailsDto();
   client.setClientID(new BigInteger("2"));
   client.setIdNumber("10");
   client.setGetupClientNumber("GU-1001002");
   client.setIdType("National ID");
   client.setDateOfBirth("1990-01-01");
   client.setFirstName("John");
   client.setSurname("Smith");
   client.setInitials("J");
   client.setTitle("Mr");
   client.setGender("Male");
   client.setMaritalStatus("Married");
   client.setEthnicity("Black");
   client.setEducationLevel("Honours Degree / Post Graduate Diploma / Professional Qualification");
   client.setUserSignOn("TEST_USER");
   client.setDelete(true);

   service.updatePersonClient(client);

  } catch (Exception e) {
   // TODO: handle exception
   e.printStackTrace();
  }

 }*/


}