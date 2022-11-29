package za.co.metropolitan.getup.client.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.metropolitan.getup.client.dto.client.ContactPrefRequestDto;
import za.co.metropolitan.getup.client.dto.client.CreateClientRequestDto;
import za.co.metropolitan.getup.client.dto.client.MarketingConsentRequestDto;
import za.co.metropolitan.getup.client.modelRepository.client.MarketingConsentLookupRepository;
import za.co.metropolitan.getup.client.modelRepository.client.PersonClientRepository;
import za.co.metropolitan.getup.client.dto.*;
import za.co.metropolitan.getup.client.dto.external.*;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;
import za.co.metropolitan.getup.client.errors.RecordNotFoundException;
import za.co.metropolitan.getup.client.helper.ServiceHelper;
import za.co.metropolitan.getup.client.model.client.*;
import za.co.metropolitan.getup.client.modelRepository.client.PrefContactMediumLookupRepository;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SharedService {

 @Autowired
 private ClientDetailService clientDetailService;

    @Autowired
    private PersonClientRepository personClientRepository;

    @Autowired
    private PersonClientRepository clientRepository;

    @Autowired
    private MarketingConsentLookupRepository clientMarketConsentLookup;

    @Autowired
    private PrefContactMediumLookupRepository prefContactMediumLookupRepository;

    @Autowired
    private MetRetailCoreService metRetailCoreService;

    @Autowired
    private ProductService productService;

    @Value("${metBusinessType}")
    private Integer businessType;

    @Value("${mfpUGC.url}")
    private String mfpUGC;

    @Value("${atomUGC.url}")
    private String atomUGC;

    @Autowired
    private ValidationService validationService;

 private static final Logger log = LoggerFactory.getLogger(SharedService.class);

    private ClientDetailsDto setpersonPojo(PersonClient client){
        ClientDetailsDto clientDetails = null;
        if(client != null) {
            clientDetails = new ClientDetailsDto();
            clientDetails.setClientID(client.getId());
            clientDetails.setGetupClientNumber(client.getGetupClientNumber());
            clientDetails.setIdNumber(client.getIdNumber());
            clientDetails.setIdType(client.getIdType());
            clientDetails.setDateOfBirth(client.getDateOfBirth().toString());
            clientDetails.setFirstName(client.getFirstName());
            clientDetails.setSurname(client.getSurname());
            clientDetails.setInitials(client.getInitials());
            clientDetails.setTitle(client.getTitle());
            clientDetails.setGender(client.getGender());
            clientDetails.setMaritalStatus(client.getMaritalStatus());
            clientDetails.setEthnicity(client.getEthnicity());
            clientDetails.setEducationLevel(client.getEducationLevel());
            clientDetails.setUserSignOn(client.getUserSignon());

        }


        return clientDetails;
    }

    public Boolean doesClientExist(String clientIdNumber,
                                   String clientId,
                                   String getupClientNumber ) throws Exception {
        Boolean exists = false;

    //// ClientIdentifier can be clientId or idNumber or getupClientNumber /////////////

        if (clientIdNumber != null &&
                        (validationService.isValidClientIdNumber(clientIdNumber) &&
                        clientRepository.findByIdNumber(clientIdNumber) != null)    ) {
            return true;
        }

//        /// check core///
//        if(clientIdNumber != null &&
//                        (validationService.isValidClientIdNumber(clientIdNumber) &&
//                        metRetailCoreService.findMetKeyForClientIdNumber(clientIdNumber) != null) ){
//            /// create on getup
//            this.findClientPerson(clientIdNumber);
//
//            return true;
//        }

        if (clientId != null &&
                        (validationService.isValidClientId(clientId) &&
                        clientRepository.findById(BigInteger.valueOf(Long.parseLong(clientId))) != null)    ) {

            return true;
        }

        if (getupClientNumber != null &&
                        clientRepository.findByGetupClientNumber(getupClientNumber) != null   ) {

            return true;
        }

        return exists;
    }

   public ClientDetailsDto fetchGetupClient(String request) throws Exception {
       ClientDetailsDto clientDetails = setpersonPojo(clientRepository.findByGetupClientNumber(request));
       return clientDetails;
    }

    public ClientDetailsDto findClientDetails(String request) throws Exception {

        ClientDetailsDto clientDetails = null;
        //  PersonClient client = null;

        //// PERSON ////
        if(validationService.isNumeric(request)){

            //// id number validation
            validationService.isValidClientIdNumber(request);

            //// person from getup else core client/party info from core//
            clientDetails = this.findClientPerson(request);

        }else {
            //// person from getup with GetupClientNumber //
            clientDetails = setpersonPojo(clientRepository.findByGetupClientNumber(request));
        }

        // does client exist
        if (clientDetails == null) {
            throw new RecordNotFoundException("Client does not exist ");
        } else {

           log.info("### FETCH PERSON INFO ###");

            if(clientDetails.getContactDetails() == null) {
                clientDetails.setContactDetails(this.findContactDetails(clientDetails.getIdNumber()));
            }
           log.info("### FETCH CONTACT INFO ###");


            if(clientDetails.getLifeHackDetails() == null) {
                clientDetails.setLifeHackDetails(clientDetailService.findLifeHacks(clientDetails.getClientID()));
            }
           log.info("### FETCH LIFE HACKS ###");

            if(clientDetails.getEmploymentDetails() == null) {
                clientDetails.setEmploymentDetails(clientDetailService.findEmployment(clientDetails.getClientID()));
            }
            log.info("### FETCH EMPLOYMENT ###");

            List<ClientMarketConsentDetailsDto> consentList = this.findMarketingConsent(clientDetails.getIdNumber());
            if(!consentList.isEmpty())
                clientDetails.setMarketingConsentDetails(consentList);
            log.info("### FETCH MARKET CONSENT ###");


            ClientPreferenceDetailsDto prefDetails = this.findContactPref(clientDetails.getIdNumber());
            if(prefDetails != null)
              clientDetails.setClientPreferenceDetails(prefDetails);
            log.info("### FETCH CONTACT PREFERENCE ###");
        }

        return clientDetails;
    }


    public MarketingConsentDetailsResponse saveMarketingConsent(List<ClientMarketConsentDetailsDto> marketingConsentDetails) throws Exception {

        UpdateResponseDto updateResponseDto = clientDetailService.saveMarketingConsent(marketingConsentDetails);

        String id = personClientRepository.findById(marketingConsentDetails.get(0).getClientId()).getIdNumber();
        CoreMarketingConsentResponse coreMarketingConsentResponse = metRetailCoreService.updateCoreMarketingConsent(id,
                this.setCoreMarketingConsent(marketingConsentDetails));

        MarketingConsentDetailsResponse response = new MarketingConsentDetailsResponse();
        response.setUpdateResponse(updateResponseDto);
        response.setCoreMarketingConsentResponse(coreMarketingConsentResponse);
        return response;
     }


     public ContactPreferenceResponse saveContactPreferences(ClientPreferenceDetailsDto clientPreferenceDetails) {

        UpdateResponseDto updateResponseDto = clientDetailService.saveContactPreference(clientPreferenceDetails);

        CorePreferencesResponse corePreferencesResponse = metRetailCoreService.updateCorePreferences(personClientRepository.findById(clientPreferenceDetails.getContactTimeOfDay().getClientId()).getIdNumber(),
                this.setCorePreferences(clientPreferenceDetails));

        ContactPreferenceResponse response = new ContactPreferenceResponse();
        response.setUpdateResponse(updateResponseDto);
        response.setCorePreferencesResponse(corePreferencesResponse);
        return response;
    }


 public ClientDetailsDto findClientPerson(String idnumber) throws Exception {

        log.info("######### findClientPerson #########");

     ClientDetailsDto coreClient = new ClientDetailsDto();
     ClientDetailsDto respone = null;

   PersonClient getupclient = clientDetailService.findPersonbyIdnumber(idnumber);
   if(getupclient != null){

       respone = setpersonPojo(getupclient);

     }else{

         List<Party> searchResponse = metRetailCoreService.searchPartyByIdnumber(idnumber).getParties();

         if (searchResponse != null) {

             for (Attribute attribute : searchResponse.get(0).getAttributes()) {

                 if (attribute.getLink().getBusinessType() == businessType) {
                     coreClient.setMetKey(attribute.getLink().getBusinessKey());
                     coreClient.setFirstName(attribute.getNaturalParty().getFirstName());
                     coreClient.setSurname(attribute.getNaturalParty().getLastName());

                     if( attribute.getNaturalParty().getTitle().toUpperCase().contains("MRS")){
                         coreClient.setTitle("Mrs");
                     }
                     if( attribute.getNaturalParty().getTitle().toUpperCase().contains("MIS")){
                         coreClient.setTitle("Miss");
                     }
                     if( attribute.getNaturalParty().getTitle().toUpperCase().contains("MR")){
                         coreClient.setTitle("Mr");
                     }
                     if( attribute.getNaturalParty().getTitle().toUpperCase().contains("MS")){
                         coreClient.setTitle("Ms");
                     }
                     if( attribute.getNaturalParty().getTitle().toUpperCase().contains("SI")){
                         coreClient.setTitle("Sir");
                     }
                     if( attribute.getNaturalParty().getTitle().toUpperCase().contains("PROF") || attribute.getNaturalParty().getTitle().toUpperCase().contains("DR")){
                         coreClient.setTitle("Dr");
                     }

                     if (attribute.getNaturalParty().getGender().toUpperCase().equals("F")) {
                         coreClient.setGender("Female");
                     }

                     if (attribute.getNaturalParty().getGender().toUpperCase().equals("M")) {
                         coreClient.setGender("Male");
                     }

                     if(attribute.getNaturalParty().getInitials() != null) {
                         coreClient.setInitials(attribute.getNaturalParty().getInitials());
                     }else {
                         coreClient.setInitials(String.valueOf(attribute.getNaturalParty().getFirstName().charAt(0)));
                     }

                     coreClient.setDateOfBirth(attribute.getNaturalParty().getDateOfBirth().substring(0, 4) + "-" +
                             attribute.getNaturalParty().getDateOfBirth().substring(4, 6) + "-" +
                             attribute.getNaturalParty().getDateOfBirth().substring(6, 8));

                     ClientDetail coreperson = metRetailCoreService.getClientDetail(coreClient.getMetKey());
                     coreClient.setIdNumber(attribute.getNaturalParty().getIdNumber());

                     if (coreperson.getPassportNumber() != null) {
                         coreClient.setIdType("Passport");
                     } else {
                         coreClient.setIdType("National ID");
                     }

                     coreClient.setUserSignOn("CORE");

                     List<ClientContactDetailsDto> contactDetails = new ArrayList<>();

                     if (coreperson.getEmail() != null) {
                         ClientContactDetailsDto clientContact = new ClientContactDetailsDto();
                         clientContact.setContactValue(coreperson.getEmail());
                         clientContact.setContactType("Email Address");
                         contactDetails.add(clientContact);
                     }

                     if (coreperson.getCellphoneNo() != null) {
                         ClientContactDetailsDto clientContact = new ClientContactDetailsDto();
                         clientContact.setContactValue(coreperson.getCellphoneNo());
                         clientContact.setContactType("Cellphone");
                         contactDetails.add(clientContact);
                     }

                     coreClient.setContactDetails(contactDetails);
                     respone = clientDetailService.saveGetupClient(coreClient);
                 }
             }

         }
     }

   return respone;
 }


    private String setCoreContactTime(String contactTime){
            String contactPrefTime = null;

            switch(contactTime) {

                case "Morning":
                    return "Morning 9am - 1pm";
                case "Mid-day":
                    return "All day 9am - 5pm";
                case "Afternoon":
                    return "Afternoon 1pm - 5pm";
                case "Evening":
                    return "After hours";
            }

            return contactPrefTime;
     }

    private CoreContactPref setCorePreferences(ClientPreferenceDetailsDto clientPreferenceDetails) {

        CoreContactPref coreContactPref = new CoreContactPref();

        if(clientPreferenceDetails.getContactTimeOfDay().getContactTime() != null)
        coreContactPref.setBestContactTime(this.setCoreContactTime(clientPreferenceDetails.getContactTimeOfDay().getContactTime()));


        /// client select how metropolitan can make contct with them ///
        for (PreferenceOutboundMediumDTO t : clientPreferenceDetails.getOutboundMedium()) {

                if(coreContactPref.getContactMethod() != null){
                    switch(t.getMedium()) {
                        case "WhatsApp":
                            coreContactPref.setContactMethod("WhatsApp");
                        case "Email":
                            coreContactPref.setContactMethod("Email");
                        case "SMS":
                            coreContactPref.setContactMethod("Cellphone");
                    }
                }
        }

        return coreContactPref;
    }

 private CoreMarketingConsent setCoreMarketingConsent(List<ClientMarketConsentDetailsDto> marketingConsentDetails) {
  // find consent types and set updates on core market consent
     CoreMarketingConsent coreMarketingConsent = new CoreMarketingConsent();

     for (ClientMarketConsentDetailsDto t : marketingConsentDetails) {

                 ///"New Solutions"///
                 if (Integer.valueOf(ServiceHelper.convertToconsentTypeId(t.getConsentType())).equals(-1)) {
                     coreMarketingConsent.setNewProducts(true);
                 }else{
                     coreMarketingConsent.setNewProducts(false);
                 }

                 ///"Share Info Internally"///
                 if (Integer.valueOf(ServiceHelper.convertToconsentTypeId(t.getConsentType())).equals(-2)) {
                     coreMarketingConsent.setShareInfoInGroup(true);
                 }else {
                     coreMarketingConsent.setShareInfoInGroup(false);
                 }

                 ///"AddToResearchCommunity"///
                 if (Integer.valueOf(ServiceHelper.convertToconsentTypeId(t.getConsentType())).equals(-3)) {
                     coreMarketingConsent.setAddToResearchCommunity(true);
                 }else {
                     coreMarketingConsent.setAddToResearchCommunity(false);
                 }

                 ///AddBenefitsToPlan///
//                 if (t.getId().equals(-4)) {
//                     coreMarketingConsent.setAddBenefitsToPlan(true);
//                 }

             }

        return coreMarketingConsent;
 }

    /// find and map contact info ///
    public List<ClientContactDetailsDto> findContactDetails(String idnumber){
        BigInteger clientId = clientDetailService.findPersonbyIdnumber(idnumber).getId();

        if (clientId == null)
            throw new InvalidRequestException("Please create client first");

        List<ClientContactDetailsDto> contactDetails = new ArrayList<ClientContactDetailsDto>();

        List<ClientContactDetailsDto> contacts = new ArrayList<ClientContactDetailsDto>();

        contacts = clientDetailService.findContactInfo(clientId);

        if(contacts != null){
                contactDetails.addAll(contacts);
        }else {
            ///// check core ///
            List<Party> searchResponse = metRetailCoreService.searchPartyByIdnumber(idnumber).getParties();

            if (searchResponse != null) {

                for (Attribute attribute : searchResponse.get(0).getAttributes()) {

                    if (attribute.getLink().getBusinessType() == businessType) {

                        ClientDetail coreperson = metRetailCoreService.getClientDetail(attribute.getLink().getBusinessKey());

                        if (coreperson.getEmail() != null) {
                            ClientContactDetailsDto clientContact = new ClientContactDetailsDto();
                            clientContact.setClientId(clientId);
                            clientContact.setUserSignOn("CORE");
                            clientContact.setContactValue(coreperson.getEmail());
                            clientContact.setContactType("Email Address");
                            contacts.add(clientContact);
                        }

                        if (coreperson.getCellphoneNo() != null) {
                            ClientContactDetailsDto clientContact = new ClientContactDetailsDto();
                            clientContact.setClientId(clientId);
                            clientContact.setUserSignOn("CORE");
                            clientContact.setContactValue(coreperson.getCellphoneNo());
                            clientContact.setContactType("Cellphone");
                            contacts.add(clientContact);
                        }

                        contactDetails = clientDetailService.upsertClientContacts(contacts);
                    }
                }
            }
        }

        return contactDetails;

    }

   /// find and map to our preferences structure ///
  public ClientPreferenceDetailsDto findContactPref(String idnumber){

      BigInteger clientId = clientDetailService.findPersonbyIdnumber(idnumber).getId();

      if (clientId == null)
          throw new InvalidRequestException("Please create client first");

      ClientPreferenceDetailsDto prefList = clientDetailService.findPreferences(clientId);

      ClientPreferenceDetailsDto newPref = null;
//      log.info("###### " + prefList.toString());
      if(prefList != null) {

          newPref = new ClientPreferenceDetailsDto();

          newPref.setContactTimeOfDay(prefList.getContactTimeOfDay());
          newPref.setOutboundMedium(prefList.getOutboundMedium());
          newPref.setInboundMedium(prefList.getInboundMedium());

      }else{
          ///check in core
          CoreContactPref corePref = metRetailCoreService.findCoreContactPref(idnumber);

          /// get from core
          if (corePref != null) {
              newPref = new ClientPreferenceDetailsDto();
              if (corePref.getContactMethod() != null) {
                  switch (corePref.getContactMethod()) {
                      case "WhatsApp":
                          newPref.getOutboundMedium().get(0).setMedium("WhatsApp");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(true);

                          newPref.getInboundMedium().get(0).setOptedIn(true);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("WhatsApp");
                      case "Home Telephone":
                          newPref.getOutboundMedium().get(0).setMedium("SMS");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(true);

                          newPref.getInboundMedium().get(0).setOptedIn(true);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("SMS");
                      case "SMS":
                          newPref.getOutboundMedium().get(0).setMedium("SMS");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(true);

                          newPref.getInboundMedium().get(0).setOptedIn(true);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("SMS");
                      case "Cellphone":
                          newPref.getOutboundMedium().get(0).setMedium("SMS");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(true);

                          newPref.getInboundMedium().get(0).setOptedIn(true);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("SMS");
                      case "Email":
                          newPref.getOutboundMedium().get(0).setMedium("Email");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(true);

                          newPref.getInboundMedium().get(0).setOptedIn(true);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("Email");


                      default:
                          newPref.getOutboundMedium().get(0).setMedium("WhatsApp");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(false);

                          newPref.getInboundMedium().get(0).setOptedIn(false);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("WhatsApp");

                          newPref.getOutboundMedium().get(0).setMedium("Facebook Messenger");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(false);

                          newPref.getInboundMedium().get(0).setOptedIn(false);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("Facebook Messenger");

                          newPref.getOutboundMedium().get(0).setMedium("SMS");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(false);

                          newPref.getInboundMedium().get(0).setOptedIn(false);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("SMS");

                          newPref.getOutboundMedium().get(0).setMedium("Email");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(false);

                          newPref.getInboundMedium().get(0).setOptedIn(false);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("Email");

                          newPref.getOutboundMedium().get(0).setMedium("Online profile notifications");
                          newPref.getOutboundMedium().get(0).setClientId(clientId);
                          newPref.getOutboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getOutboundMedium().get(0).setOptedIn(false);

                          newPref.getInboundMedium().get(0).setOptedIn(false);
                          newPref.getInboundMedium().get(0).setClientId(clientId);
                          newPref.getInboundMedium().get(0).setUserSignOn("CORE");
                          newPref.getInboundMedium().get(0).setMedium("Online profile notifications");
                  }

              }

              if (corePref.getBestContactTime() != null) {
                  newPref.getContactTimeOfDay().setContactTime(corePref.getBestContactTime());
                  newPref.getContactTimeOfDay().setClientId(clientId);
                  newPref.getContactTimeOfDay().setUserSignOn("CORE");
              }


                clientDetailService.saveContactPreference(newPref);
            }

        }

      return newPref;
  }


  /// find and map to our consent structure ///
  public List<ClientMarketConsentDetailsDto> findMarketingConsent(String idnumber) {

     BigInteger clientId = clientDetailService.findPersonbyIdnumber(idnumber).getId();

   if (clientId == null)
    throw new InvalidRequestException("Please create client first");

      List<ClientMarketConsentDetailsDto> consentList = new ArrayList<ClientMarketConsentDetailsDto>();
      /// fetch from getup
      consentList.addAll(clientDetailService.findMarketConsent(clientId));

      if(consentList.isEmpty()) {
          /// get from core
          CoreMarketingConsent coreMarketingConsent = metRetailCoreService.findCoreMarketingConsent(idnumber);

          consentList = new ArrayList<>();

          if (coreMarketingConsent.isShareInfoInGroup() || coreMarketingConsent.isAddToResearchCommunity()
                  || coreMarketingConsent.isNewProducts()) {
              List<MarketingConsentLookup> typelist = clientMarketConsentLookup.findAll();

              for (MarketingConsentLookup t : typelist) {

                  ClientMarketConsentDetailsDto consent = new ClientMarketConsentDetailsDto();
                  consent.setClientId(clientId);
                  consent.setUserSignOn("CORE");

                  ///"New Solutions"///
                  if ( t.getId().equals(BigInteger.valueOf(-1))) {
                      consent.setConsentType(t.getConsentType());
                      consent.setOptedIn(coreMarketingConsent.isNewProducts());
                  }

                  ///"Share Info Internally"///
                  if (t.getId().equals(BigInteger.valueOf(-2))) {
                      consent.setConsentType(t.getConsentType());
                      consent.setOptedIn(coreMarketingConsent.isShareInfoInGroup());
                  }

                  ///"AddToResearchCommunity"///
                  if (t.getId().equals(BigInteger.valueOf(-3))) {
                      consent.setConsentType(t.getConsentType());
                      consent.setOptedIn(coreMarketingConsent.isAddToResearchCommunity());
                  }

                  /// core has another consent type called AddBenefitsToPlan
                  /// coreMarketingConsent.isAddBenefitsToPlan()
                  consentList.add(consent);
              }
          }

      }

     return consentList;
  }


    public ClientDetailsDto mappingClientDetailsRequest(CreateClientRequestDto request) {

        ClientDetailsDto response = new ClientDetailsDto();
        response.setInitials(request.getInitials());
        response.setClientID(request.getClientID());
        response.setGetupClientNumber(request.getGetupClientNumber());
        response.setGender(request.getGender());
        response.setTitle(request.getTitle());
        response.setUserSignOn(request.getUserSignOn());
        response.setEducationLevel(request.getEducationLevel());
        response.setEmploymentDetails(request.getEmploymentDetails());
        response.setUserSignOn(request.getUserSignOn());
        response.setMaritalStatus(request.getMaritalStatus());
        response.setIdType(request.getIdType());
        response.setEthnicity(request.getEthnicity());
        response.setDateOfBirth(request.getDateOfBirth());
        response.setFirstName(request.getFirstName());
        response.setSurname(request.getSurname());
        response.setIdNumber(request.getIdNumber());
        response.setContactDetails(request.getContactDetails());
        response.setLifeHackDetails(request.getLifeHackDetails());

        if(request.getContactPreferenceDetails()!=null)
        response.setClientPreferenceDetails(this.mappingContactPrefRequest(request.getContactPreferenceDetails()));

        if(request.getMarketingConsentDetails() != null)
        response.setMarketingConsentDetails(this.mappingMarketingConsentRequest(request.getMarketingConsentDetails()));

        return response;
    }

    public ClientPreferenceDetailsDto mappingContactPrefRequest(ContactPrefRequestDto request){

        ClientPreferenceDetailsDto mappedMediums = new ClientPreferenceDetailsDto();
        ClientPrefContactTimeDto besttime = new ClientPrefContactTimeDto();
        besttime.setUserSignOn(request.getUserSignOn());
        besttime.setClientId(request.getClientId());
        besttime.setContactTime(request.getBestTimeToContact());

        mappedMediums.setContactTimeOfDay(besttime);

        List<PreferenceInboundMediumDTO> inboundMapping = new ArrayList<>();
        List<PreferenceOutboundMediumDTO> outboundMapping = new ArrayList<>();

        List<PrefContactMediumLookup> typelist = prefContactMediumLookupRepository.findAll();

        for (PrefContactMediumLookup t : typelist) {

            PreferenceInboundMediumDTO inbound = new PreferenceInboundMediumDTO();
            PreferenceInboundMediumDTO outbound = new PreferenceInboundMediumDTO();

            inbound.setClientId(request.getClientId());
            inbound.setUserSignOn(request.getUserSignOn());

            outbound.setClientId(request.getClientId());
            outbound.setUserSignOn(request.getUserSignOn());

            ///"whatsApp"///
            if (t.getMedium().equals("WhatsApp")) {
                inbound.setOptedIn(request.getInboundMedium().getWhatsApp());
                inbound.setMedium(t.getMedium());

                outbound.setOptedIn(request.getOutboundMedium().getWhatsApp());
                outbound.setMedium(t.getMedium());
            }

            ///"Facebook Messenger"///
            if (t.getMedium().equals("Facebook Messenger")) {
                inbound.setOptedIn(request.getInboundMedium().getFacebookMsg());
                inbound.setMedium(t.getMedium());

                outbound.setOptedIn(request.getOutboundMedium().getFacebookMsg());
                outbound.setMedium(t.getMedium());
            }

            ///"SMS"///
            if (t.getMedium().equals("SMS")) {
                inbound.setOptedIn(request.getInboundMedium().getSms());
                inbound.setMedium(t.getMedium());

                outbound.setOptedIn(request.getOutboundMedium().getSms());
                outbound.setMedium(t.getMedium());
            }

            ///"Email"///
            if (t.getMedium().equals("Email")) {
                inbound.setOptedIn(request.getInboundMedium().getEmail());
                inbound.setMedium(t.getMedium());

                outbound.setOptedIn(request.getOutboundMedium().getEmail());
                outbound.setMedium(t.getMedium());
            }

            ///"Online profile notifications"///
            if (t.getMedium().equals("Online profile notifications")) {
                inbound.setOptedIn(request.getInboundMedium().getPushNotifications());
                inbound.setMedium(t.getMedium());

                outbound.setOptedIn(request.getOutboundMedium().getPushNotifications());
                outbound.setMedium(t.getMedium());
            }

            inboundMapping.add(inbound);
            outboundMapping.add(outbound);
        }

        if(inboundMapping.isEmpty())
            throw new InvalidRequestException("Invalid Inbound Medium/s");

        if(outboundMapping.isEmpty())
            throw new InvalidRequestException("Invalid Outbound Medium/s");

        mappedMediums.setInboundMedium(inboundMapping);
        mappedMediums.setOutboundMedium(outboundMapping);


        return mappedMediums;

    }



  public List<ClientMarketConsentDetailsDto> mappingMarketingConsentRequest(MarketingConsentRequestDto request){

      List<ClientMarketConsentDetailsDto> mappedList = new ArrayList<>();

      List<MarketingConsentLookup> typelist = clientMarketConsentLookup.findAll();

      for (MarketingConsentLookup t : typelist) {

          ClientMarketConsentDetailsDto consent = new ClientMarketConsentDetailsDto();

          ///"New Solutions"///
          if (t.getId().equals(BigInteger.valueOf(-1))) {
              consent.setConsentType(t.getConsentType());
              consent.setOptedIn(request.getNewSolutions());
              consent.setClientId(request.getClientId());
              consent.setUserSignOn(request.getUserSignOn());
              mappedList.add(consent);
          }

          ///"Share Info Internally"///
          if (t.getId().equals(BigInteger.valueOf(-2))) {
              consent.setConsentType(t.getConsentType());
              consent.setOptedIn(request.getShareInfoInternally());
              consent.setClientId(request.getClientId());
              consent.setUserSignOn(request.getUserSignOn());
              mappedList.add(consent);
          }

          ///"AddToResearchCommunity"///
          if (t.getId().equals(BigInteger.valueOf(-3))) {
              consent.setConsentType(t.getConsentType());
              consent.setOptedIn(request.getMarketingResearch());
              consent.setClientId(request.getClientId());
              consent.setUserSignOn(request.getUserSignOn());
              mappedList.add(consent);
          }

          /// core has another consent type called AddBenefitsToPlan
          /// coreMarketingConsent.isAddBenefitsToPlan()


      }

      if(mappedList.isEmpty())
          throw new InvalidRequestException("Invalid Consent type/s");


      return mappedList;

  }

  public List<ProductsDto> findPolicySummaryByIdNumber(String idnumber){

      List<ProductsDto> myproducts = new ArrayList<>();

       //// atom //////////
      PolicyDetailsDto atomProduct = productService.findAtomPolicyByIdNumber(idnumber);


        if(atomProduct != null && atomProduct.getPolicyNumber() != null){
            ProductsDto prod = new ProductsDto();

            int count = 0;

                if(atomProduct.getMainMember() != null) {
                    count ++;

                }

             if(atomProduct.getSpouses() != null) {
                 for (int i = 0; i < atomProduct.getSpouses().size(); i++) {
                     count++;
                 }
             }

             if(atomProduct.getChildren() != null) {
                 for (int i = 0; i < atomProduct.getChildren().size(); i++) {
                     count++;
                 }
             }

            if(atomProduct.getParents() != null) {
                for (int i = 0; i < atomProduct.getParents().size(); i++) {
                    count++;
                }
             }

            if(atomProduct.getExtendedFamily() != null) {
                for (int i = 0; i < atomProduct.getExtendedFamily().size(); i++) {
                    count++;
                }
            }

            List<PolicyContractLinkList> list = new ArrayList<>();
            PolicyContractLinkList item = new PolicyContractLinkList();
            item.setLink(atomProduct.getPolicyContractUrl());
            item.setDocumentType("pdf");
            list.add(item);
            atomProduct.setContractUrls(list);


                prod.setMembersCovered(String.valueOf(count));

                prod.setTotalMonthlyPremium(atomProduct.getBundledPremium());
                prod.setProductName(atomProduct.getPackageName());
                prod.setTotalCoverAmount(atomProduct.getCoverAmount());
                prod.setPolicyDetails(atomProduct);

                prod.setPolicyUGC(atomUGC);

            myproducts.add(prod);
        }



      //// core /////////
      PolicyResults coreProduct = metRetailCoreService.findPolicyHolderbyIdNumber(idnumber);
      log.info("############# response hey: " + coreProduct.toString());
        if(coreProduct != null ) {

            if(!coreProduct.getResults().isEmpty()) {
                String policynum = "";

                PolicyDetailsDto policy = new PolicyDetailsDto();

                FuneralPolicyInfo corepolicy = new FuneralPolicyInfo();
                log.info("############# 1: " );
                /// get core policynumber and get policy info
                for(Results results: coreProduct.getResults()){


                    List<Agreements> agreements = results.getAgreements().stream()                // convert list to stream
                            .filter(line -> "MFP".equals(line.getPolicyIndicator()))     // we dont like mkyong
                            .collect(Collectors.toList());

                    for (Agreements agreement : agreements) {
                 //   if (agreement.getPolicyIndicator().equals("MFP")) {
                        corepolicy = metRetailCoreService.findMfpFuneralByPolicy(agreement.getAgreement_number());

                        policy.setPolicyNumber(corepolicy.getPolicyNumber().getPolicyNumber());
                        policy.setPackageName(corepolicy.getProductName());
                        policy.setStatus(corepolicy.getPolicyStatus());
                        policy.setBundledPremium(corepolicy.getTotalPremium());
                        policy.setPolicyStartDate(corepolicy.getInceptionDate());

                        Double benAmount = Double.valueOf(0);


                        String policyHolderId = "0";
                        int count = 0;
                        for (PolicyHolder ph : corepolicy.getPolicyHolders()) {
                            policyHolderId = ph.getRolePlayerId();
                        }

                        ////main member ////
                        for (Roleplayer role : corepolicy.getRoleplayers()) {

                            Person person = new Person();

                            if (role.getRolePlayerId().equals(policyHolderId)) {
                                count++;

                                person.setPremium(corepolicy.getBenefits().get(0).getPremium());

                                benAmount = benAmount + corepolicy.getBenefits().get(0).getBenefitAmount();

                                person.setCoverAmount(corepolicy.getBenefits().get(0).getBenefitAmount());
                                person.setFirstName(role.getFirstName());
                                person.setLastName(role.getSurname());
                                person.setGender(role.getGender());
                                person.setDateOfBirth(role.getDateOfBirth());
                                person.setInitials(role.getInitials());
                                person.setIdNumber(role.getIdNumber());
                                person.setCellphone(role.getContactNumbers().get(0).getNumber());

                                person.setRelation("Main Member");

                                policy.setMainMember(person);

                            }
                        }

                        List<Person> coreLives = new ArrayList<>();
                        List<Beneficiary> listBenificaries = new ArrayList<>();
                        List<Person> listChildren = new ArrayList<>();

                        //// benefits info
                        for (Benefit benefit : corepolicy.getBenefits()) {

                            for (Roleplayer role : corepolicy.getRoleplayers()) {

                                /// beneficiaries ////
                                for (CoreBeneficiary beneficiary : benefit.getBeneficiaries()) {
                                    if (!beneficiary.getRolePlayerId().isEmpty()) {
                                        Beneficiary ben = new Beneficiary();

                                        if (role.getRolePlayerId().equals(beneficiary.getRolePlayerId()) && !role.getRolePlayerId().equals(policyHolderId)) {

                                            ben.setFirstName(role.getFirstName());
                                            ben.setPercentage(beneficiary.getPercentageShare());
                                            ben.setLastName(role.getSurname());
                                            ben.setGender(role.getGender());
                                            ben.setInitials(role.getInitials());
                                            ben.setIdNumber(role.getIdNumber());

                                            if(beneficiary.getPolicyHolderRelationShip() !=null)
                                            ben.setRelationship(beneficiary.getPolicyHolderRelationShip().getRelationshipCode());

                                            if (!role.getContactNumbers().isEmpty())
                                                ben.setCellphone(role.getContactNumbers().get(0).getNumber());

                                            listBenificaries.add(ben);
                                        }

                                    }
                                }

                                ///children///
                                for (Children children : benefit.getChildren()) {
                                    if (!children.getRolePlayerId().isEmpty()) {
                                        Person liveperson = new Person();
                                        if (role.getRolePlayerId().equals(children.getRolePlayerId()) && !role.getRolePlayerId().equals(policyHolderId) && !role.getFirstName().isEmpty()) {
                                            count++;

                                            if (benefit.getBenefitAmount() != null || benefit.getBenefitAmount() != 0)
                                                benAmount = benAmount + benefit.getBenefitAmount();

                                            liveperson.setCoverAmount(benefit.getBenefitAmount());
                                            liveperson.setPremium(benefit.getPremium());
                                            liveperson.setFirstName(role.getFirstName());
                                            liveperson.setLastName(role.getSurname());
                                            liveperson.setGender(role.getGender());
                                            liveperson.setDateOfBirth(role.getDateOfBirth());
                                            liveperson.setInitials(role.getInitials());
                                            liveperson.setIdNumber(role.getIdNumber());
                                            liveperson.setRelation("Child");

                                            if (!role.getContactNumbers().isEmpty())
                                                liveperson.setCellphone(role.getContactNumbers().get(0).getNumber());

                                            listChildren.add(liveperson);
                                        }
                                    }
                                }

                                ///lives///
                                for (CoreLive live : benefit.getLives()) {

                                    Person liveperson = new Person();
                                    if (role.getRolePlayerId().equals(live.getRolePlayerId()) && !role.getRolePlayerId().equals(policyHolderId) && !role.getFirstName().isEmpty()) {
                                        count++;

                                        if (benefit.getBenefitAmount() != null || benefit.getBenefitAmount() != 0)
                                            benAmount = benAmount + benefit.getBenefitAmount();

                                        liveperson.setCoverAmount(benefit.getBenefitAmount());
                                        liveperson.setPremium(benefit.getPremium());
                                        liveperson.setFirstName(role.getFirstName());
                                        liveperson.setLastName(role.getSurname());
                                        liveperson.setDateOfBirth(role.getDateOfBirth());
                                        liveperson.setGender(role.getGender());
                                        liveperson.setInitials(role.getInitials());
                                        liveperson.setIdNumber(role.getIdNumber());
                                        liveperson.setRelation("other");

                                        if (!role.getContactNumbers().isEmpty())
                                            liveperson.setCellphone(role.getContactNumbers().get(0).getNumber());

                                        coreLives.add(liveperson);
                                    }
                                }

                                /////// nested benefits //////////
                                if (!benefit.getBenefits().isEmpty()) {
                                    for (Benefit nestbenefit : benefit.getBenefits()) {
                                        /// beneficiaries ////
                                        for (CoreBeneficiary nestbeneficiary : nestbenefit.getBeneficiaries()) {
                                            if (!nestbeneficiary.getRolePlayerId().isEmpty()) {
                                                Beneficiary nestedben = new Beneficiary();

                                                if (role.getRolePlayerId().equals(nestbeneficiary.getRolePlayerId()) && !role.getRolePlayerId().equals(policyHolderId)) {
                                                    nestedben.setFirstName(role.getFirstName());
                                                    nestedben.setPercentage(nestbeneficiary.getPercentageShare());
                                                    nestedben.setLastName(role.getSurname());
                                                    nestedben.setGender(role.getGender());
                                                    nestedben.setInitials(role.getInitials());
                                                    nestedben.setIdNumber(role.getIdNumber());

                                                    if(nestbeneficiary.getPolicyHolderRelationShip() !=null)
                                                        nestedben.setRelationship(nestbeneficiary.getPolicyHolderRelationShip().getRelationshipCode());

                                                    if (!role.getContactNumbers().isEmpty())
                                                        nestedben.setCellphone(role.getContactNumbers().get(0).getNumber());

                                                    listBenificaries.add(nestedben);
                                                }


                                            }
                                        }

                                        ///lives///
                                        for (CoreLive live : benefit.getLives()) {
                                            Person livenestperson = new Person();

                                            if (role.getRolePlayerId().equals(live.getRolePlayerId()) && !role.getRolePlayerId().equals(policyHolderId) && !role.getFirstName().isEmpty()) {

                                                count++;

                                                if (nestbenefit.getBenefitAmount() != null || nestbenefit.getBenefitAmount() != 0)
                                                    benAmount = benAmount + nestbenefit.getBenefitAmount();


                                                livenestperson.setCoverAmount(nestbenefit.getBenefitAmount());
                                                livenestperson.setPremium(nestbenefit.getPremium());
                                                livenestperson.setFirstName(role.getFirstName());
                                                livenestperson.setLastName(role.getSurname());
                                                livenestperson.setGender(role.getGender());
                                                livenestperson.setDateOfBirth(role.getDateOfBirth());
                                                livenestperson.setInitials(role.getInitials());
                                                livenestperson.setIdNumber(role.getIdNumber());
                                                livenestperson.setRelation("other");
                                                if (!role.getContactNumbers().isEmpty())
                                                    livenestperson.setCellphone(role.getContactNumbers().get(0).getNumber());

                                                coreLives.add(livenestperson);
                                            }
                                        }
                                    }

                                }
                            }

                            if (!listBenificaries.isEmpty())
                                policy.setBeneficiaries(listBenificaries);
                        }

                        if (!coreLives.isEmpty())
                            policy.setOtherLives(coreLives);

                        if (!listChildren.isEmpty())
                            policy.setChildrenForLife(listChildren);

                        policy.setCoverAmount(benAmount);

                        PaymentDto payer = new PaymentDto();
                        BankDetailsDto bank = new BankDetailsDto();
                        bank.setBank(corepolicy.getPremiumPayer().getBank());
                        bank.setAccountNumber(corepolicy.getPremiumPayer().getAccountNumber());
                        bank.setBranchCode(corepolicy.getPremiumPayer().getBranchCode());
                        bank.setAccountHolder(corepolicy.getPremiumPayer().getAccountHolderSurname());

                        switch (corepolicy.getPremiumPayer().getPaymentType()) {
                            case "D":
                                payer.setPaymentMethod("Debit order");
                                break;
                            case "S":
                                payer.setPaymentMethod("Stop order");
                                break;
                            default:
                                payer.setPaymentMethod("Debit order");
                        }

                        payer.setBankDetails(bank);
                        payer.setDeductionDay(corepolicy.getPremiumPayer().getDeductionDay());

                        policy.setPaymentDetails(payer);


                        //// get contract
                        PolicyContractList policyContracts = null;
                        policyContracts = metRetailCoreService.GetFuneralListByPolicy(policy.getPolicyNumber());

                        if (policyContracts != null)
                            policy.setContractUrls(policyContracts.links);

                        ProductsDto prod = new ProductsDto();
                        prod.setPolicyUGC(mfpUGC);
                        prod.setMembersCovered(String.valueOf(count));
                        prod.setTotalMonthlyPremium(policy.getBundledPremium());
                        prod.setProductName(policy.getPackageName());
                        prod.setTotalCoverAmount(policy.getCoverAmount());


                        prod.setPolicyDetails(policy);

                        myproducts.add(prod);
                 //   }


                }

            }
                ////

                }

            }



        return myproducts;

  }


}
