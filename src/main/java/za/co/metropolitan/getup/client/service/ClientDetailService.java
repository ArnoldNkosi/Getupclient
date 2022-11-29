package za.co.metropolitan.getup.client.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerDetailsDto;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerMarketConsentDetailsDto;
import za.co.metropolitan.getup.client.modelRepository.client.*;
import za.co.metropolitan.getup.client.modelRepository.product.ProductLifeHackLookupRepository;
import za.co.metropolitan.getup.client.dto.*;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;
import za.co.metropolitan.getup.client.model.client.*;
import za.co.metropolitan.getup.client.model.product.ProductLifeHackLookup;

@Service
public class ClientDetailService {

 private static final Logger log = LoggerFactory.getLogger(ClientDetailService.class);

 @Autowired
 private KnownConsumerService knownConsumerService;

 @Autowired
 private ClientPrefInboundMediumRepository clientPrefInboundMediumRepository;

 @Autowired
 private PersonClientRepository clientRepository;

 @Autowired
 private ClientContactRepository clientContactRepository;

 @Autowired
 private ContactTypeLookupRepository contactTypeRepository;

 @Autowired
 private ClientEmploymentDetailsRepository clientEmploymentDetailsRepository;

 @Autowired
 private EmploymentTypeLookupRepository employmentTypeRepository;

 @Autowired
 private IndustryLookupRepository industryRepository;

 @Autowired
 private OccupationLookupRepository occupationRepository;

 @Autowired
 private ClientMarketConsentRepository marketConsentRepository;

 @Autowired
 private MarketingConsentLookupRepository consentLookupRepository;

 @Autowired
 private ClientPrefContactTimeRepository clientPrefContactTimeRepository;

 @Autowired
 private ClientPrefContactTimeHistoryRepository clientPrefContactTimeHistoryRepository;

 @Autowired
 private ClientLifeHackRepository lifeHackRepository;

 @Autowired
 private ProductLifeHackLookupRepository lifeHackProductLookupRepository;

 @Autowired
 private LifeHackLookupRepository lifeHackLookupRepository;

 @Autowired
 private ClientPrefOutboundMediumHistoryRepository clientPrefOutboundMediumHistoryRepository;

 @Autowired
 private ClientMarketConsentHistoryRepository clientMarketConsetHistoryRepository;

 @Autowired
 private ClientPrefContactTimeHistoryRepository clientPrefContactHistoryRepository;

 @Autowired
 private ClientPrefOutboundMediumRepository clientPrefOutboundMediumRepository;

 @Autowired
 private ClientLifeHackHistoryRepository lifeHackHistoryRepository;

 @Autowired
 private ClientEmploymentDetailsHistoryRepository clientEmploymentDetailsHistoryRepository;

 @Autowired
 private PersonClientHistoryRepository personClientHistoryRepository;

 @Autowired
 private PersonClientRepository personClientRepository;

 @Autowired
 private ClientContactHistoryRepository clientContactHistoryRepository;

 @Autowired
 private ClientImageRepository clientImageRepository;

 @Autowired
 private ValidationService validationService;

 @Autowired
 private SharedService sharedService;

 @Autowired
 private ClientPrefInboundMediumHistoryRepository clientPrefInboundMediumHistoryRepository;

 @Autowired
 private  ClientBankDetailRepository clientBankDetailRepository;

 @Autowired
 private  ClientBankDetailHistoryRepository clientBankDetailHistoryRepository;


 public Boolean doesClientExistByClientId(String clientID) throws Exception {
  Boolean exists = false;
  PersonClient client = clientRepository.findById(BigInteger.valueOf(Long.parseLong(clientID)));
  if (client != null) {
   exists = true;
  }

  return exists;
 }

 public EmploymentDetailsDto findEmployment(BigInteger clientId) {

    EmploymentDetailsDto empDetails = null;
    ClientEmploymentDetails foundEmploymentDetails = clientEmploymentDetailsRepository.findByClientId(clientId);
     if (foundEmploymentDetails != null) {
         empDetails =  new EmploymentDetailsDto();
        empDetails.setId(foundEmploymentDetails.getId());
        empDetails.setClientId(foundEmploymentDetails.getClientId());
        empDetails.setOccupation(foundEmploymentDetails.getOccupation());
        empDetails.setIndustry(foundEmploymentDetails.getIndustry());
        empDetails.setGrossSalaryAmount(foundEmploymentDetails.getGrossSalaryAmount());
        empDetails.setEmploymentType(foundEmploymentDetails.getEmploymentType());
        empDetails.setEmployerName(foundEmploymentDetails.getEmployerName());
     }

     return empDetails;
 }


public  List<ClientLifeHackDetailsDto> findLifeHacks(BigInteger clientId){

     List<ClientLifeHackDetailsDto> lifeHackDetails = new ArrayList<ClientLifeHackDetailsDto>();
     List<ClientLifeHack> foundLifeHackList = lifeHackRepository.findByClientId(clientId);
       if (foundLifeHackList != null || foundLifeHackList.size() > 0) {
          for (ClientLifeHack h : foundLifeHackList) {
              if(h.getEndedAt() == null){
               LifeHackLookup lookupLifeHack = lifeHackLookupRepository.findByLifeHack(h.getLifeHack());
               if(lookupLifeHack.getEndedAt() == null){
                ClientLifeHackDetailsDto hack = new ClientLifeHackDetailsDto();
                hack.setId(h.getId());
                hack.setClientId(h.getClientId());
                hack.setProduct(h.getProduct());
                hack.setLifeHack(h.getLifeHack());
                hack.setOptedInDate(h.getCreatedAt());
                hack.setEndedAt(h.getEndedAt());
                hack.setUserSignOn(h.getUserSignOn());
                hack.setLifeHackScore(h.getLifeHackScore());
                hack.setLifeHackScoreAt(h.getLifeHackScoreAt());
                lifeHackDetails.add(hack);

               }

              }
          }
       }

    return lifeHackDetails;
}

 public PersonClient findPersonbyIdnumber(String idnumber) {

     return clientRepository.findByIdNumber(idnumber);
 }

 public List<ClientContactDetailsDto> findContactInfo(BigInteger clientId){

  log.info("######## findContactInfo ######");

  List<ClientContactDetailsDto> contactDetails = new ArrayList<ClientContactDetailsDto>();

   List<ClientContact> foundContactList = clientContactRepository.findByClientId(clientId);

   for (ClientContact c : foundContactList) {
    ClientContactDetailsDto clientContact = new ClientContactDetailsDto();
    clientContact.setContactValue(c.getContactValue());
    clientContact.setContactType(c.getContactType());
    clientContact.setClientId(c.getClientId());
    clientContact.setId(c.getId());
    clientContact.setUserSignOn(c.getUserSignOn());
    contactDetails.add(clientContact);
   }

  return contactDetails ;
  }

    public UpdateResponseDto saveContactPreference(ClientPreferenceDetailsDto request) {

        UpdateResponseDto response = new UpdateResponseDto();

        try {

            this.upsertContactPreferenceTime(request.getContactTimeOfDay());

            this.upsertContactPreferenceMediumInbound(request.getInboundMedium());


            this.upsertContactPreferenceMediumOutbound(request.getOutboundMedium());


        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! Cannot save contact preferences. Please fix the errors and retry." );
        }

        return response;

    }

    public UpdateResponseDto saveGetupConsentAndPreference( List<ClientMarketConsentDetailsDto> consent , ClientPreferenceDetailsDto pref) {

        UpdateResponseDto response = new UpdateResponseDto();

        try {

            this.upsertMarketingConsent(consent);

            this.upsertContactPreferenceTime(pref.getContactTimeOfDay());

            this.upsertContactPreferenceMediumInbound(pref.getInboundMedium());

            this.upsertContactPreferenceMediumOutbound(pref.getOutboundMedium());


        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! Cannot save contact preferences. Please fix the errors and retry." );
        }

        return response;

    }


    public UpdateResponseDto saveContactPrefMediums(ContactPrefMediumDto request) {

        UpdateResponseDto response = new UpdateResponseDto();
        try {

            this.upsertContactPreferenceMediumInbound(request.getInboundMedium());


            this.upsertContactPreferenceMediumOutbound(request.getOutboundMedium());


        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! Cannot save contact preferences. Please fix the errors and retry.");
        }

        return response;

    }

    public UpdateResponseDto saveMarketingConsent(List<ClientMarketConsentDetailsDto> request) {
        UpdateResponseDto response = new UpdateResponseDto();
        try {

                this.upsertMarketingConsent(request);

        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! Cannot save marketing consent. Please fix the errors and retry.");
        }

        return response;

    }

    @Transactional(rollbackFor = Exception.class)
    public ClientDetailsDto saveGetupClient(ClientDetailsDto request) throws Exception {

        ClientDetailsDto clientDetails = new ClientDetailsDto();

        // create person client
        PersonClient createdClient = this.createPersonClient(request);
        log.info("CREATE CLIENT STEP 1: Create Person " + createdClient.getId());

        request.setClientID(createdClient.getId());
        request.setGetupClientNumber(createdClient.getGetupClientNumber());

        if(request.getUserSignOn() == null || request.getUserSignOn() == "")
            throw new InvalidRequestException(
                    "UserSignOn is required");


        // create contact details
        List<ClientContactDetailsDto> createdContacts = new ArrayList<ClientContactDetailsDto>();
        if (request.getContactDetails() != null) {
            for(ClientContactDetailsDto c : request.getContactDetails() ){
                    c.setClientId(request.getClientID());
                    c.setUserSignOn(request.getUserSignOn());
            }
            createdContacts = this.createClientContacts(request.getContactDetails());
            log.info("CREATE CLIENT STEP 2: Create Contact/s ");
        }

        // create life hacks
        List<ClientLifeHackDetailsDto> createdHacks = new ArrayList<ClientLifeHackDetailsDto>();
        if (request.getLifeHackDetails() != null) {
            for(ClientLifeHackDetailsDto h : request.getLifeHackDetails() ){
                    h.setClientId(request.getClientID());
                    h.setUserSignOn(request.getUserSignOn());
            }
            createdHacks = this.createLifeHacks(request.getLifeHackDetails());
            log.info("CREATE CLIENT STEP 3: Life Hacks ");
        }

        // create employment details
        EmploymentDetailsDto createdEmploymentDetails = new EmploymentDetailsDto();
        if (request.getEmploymentDetails() != null) {
            createdEmploymentDetails = this.createEmploymentDetails(request.getEmploymentDetails());
            log.info("CREATE CLIENT STEP 4: Create Employment details ");
        }

        // create consent
        List<ClientMarketConsentDetailsDto> createdConsent = new ArrayList<>();
        if (request.getMarketingConsentDetails() != null) {
            for(ClientMarketConsentDetailsDto m : request.getMarketingConsentDetails() ){
                m.setClientId(request.getClientID());
                m.setUserSignOn(request.getUserSignOn());
            }
            createdConsent = this.createMarketingConsent(request.getMarketingConsentDetails());
            log.info("CREATE CLIENT STEP 5: Create Consent ");
        }

        //check if known consumer has been created, if yes then find consent optedin = true;

            List<ClientMarketConsentDetailsDto> existingConsent = marketingConsentList(request,createdClient.getId());
            if(existingConsent.size()>0){
                createdConsent = this.createMarketingConsent(existingConsent);

        }

        // set client preferences
        ClientPreferenceDetailsDto createdPreferences = new ClientPreferenceDetailsDto();
        if (request.getClientPreferenceDetails() != null) {

            request.getClientPreferenceDetails().getContactTimeOfDay().setClientId(request.getClientID());
            request.getClientPreferenceDetails().getContactTimeOfDay().setUserSignOn(request.getUserSignOn());

            for(PreferenceInboundMediumDTO m : request.getClientPreferenceDetails().getInboundMedium() ){
                m.setClientId(request.getClientID());
                m.setUserSignOn(request.getUserSignOn());
            }

            for(PreferenceOutboundMediumDTO m : request.getClientPreferenceDetails().getOutboundMedium() ){
                m.setClientId(request.getClientID());
                m.setUserSignOn(request.getUserSignOn());
            }

            createdPreferences = this.createPreferences(request.getClientPreferenceDetails());
            log.info("CREATE CLIENT STEP 6: Client Preferences created");
        }


        /// call create User Profile ////



        /////////////////////////////////

        // set return object
        clientDetails.setClientID(createdClient.getId());
        clientDetails.setGetupClientNumber(createdClient.getGetupClientNumber());
        clientDetails.setIdNumber(createdClient.getIdNumber());
        clientDetails.setIdType(createdClient.getIdType());
        clientDetails.setDateOfBirth(createdClient.getDateOfBirth().toString());
        clientDetails.setFirstName(createdClient.getFirstName());
        clientDetails.setSurname(createdClient.getSurname());
        clientDetails.setInitials(createdClient.getInitials());
        clientDetails.setTitle(createdClient.getTitle());
        clientDetails.setGender(createdClient.getGender());
        clientDetails.setMaritalStatus(createdClient.getMaritalStatus());
        clientDetails.setEthnicity(createdClient.getEthnicity());
        clientDetails.setEducationLevel(createdClient.getEducationLevel());
        clientDetails.setUserSignOn(createdClient.getUserSignon());
//        clientDetails.setCreatedAt(createdClient.getCreatedAt());
        clientDetails.setContactDetails(createdContacts);
        clientDetails.setEmploymentDetails(createdEmploymentDetails);
        clientDetails.setMarketingConsentDetails(createdConsent);
        clientDetails.setClientPreferenceDetails(createdPreferences);
        clientDetails.setLifeHackDetails(createdHacks);

        log.info("CREATE CLIENT COMPLETE!");
        return clientDetails;

    }

public  List<ClientMarketConsentDetailsDto> marketingConsentList(ClientDetailsDto request,BigInteger clientId)throws Exception  {
    List<ClientMarketConsentDetailsDto> details = new ArrayList<>();

    KnownConsumerDetailsDto knownConsumerDetails =  knownConsumerService.SelectKnownConsumerUsingIdNumber(request.getIdNumber());

   if(knownConsumerDetails == null) {
       KnownConsumerDetailsDto newDto = new KnownConsumerDetailsDto();
       newDto.setId_number(request.getIdNumber());
       newDto.setFirst_name(request.getFirstName());
       newDto.setSurname(request.getSurname());
       newDto.setMarital_status(request.getMaritalStatus());
       newDto.setTitle(request.getTitle());
       newDto.setEthnicity(request.getEthnicity());
       newDto.setInitials(request.getInitials());
       newDto.setId_type(request.getIdType());
       String createKnownConsumer =  knownConsumerService.createKnownConsumerIntoDb(newDto);
   }
    else{
        List<KnownConsumerMarketConsentDetailsDto> listOfConsent = knownConsumerService.findMarketConsent(knownConsumerDetails.getKnown_consumer_number());
        if (listOfConsent.size() > 0) {
            for (KnownConsumerMarketConsentDetailsDto marketDetails : listOfConsent) {
                ClientMarketConsentDetailsDto clientMarketConsentDetailsDto = new ClientMarketConsentDetailsDto();
                clientMarketConsentDetailsDto.setConsentType(marketDetails.getConsentType());
                clientMarketConsentDetailsDto.setOptedIn(marketDetails.getOptedIn());
                clientMarketConsentDetailsDto.setUserSignOn(request.getUserSignOn());
                clientMarketConsentDetailsDto.setClientId(clientId);
                details.add(clientMarketConsentDetailsDto);
            }
        }
    }

    return details;
}
 public Boolean saveFaceToClient(BigInteger clientID, byte[] image) throws Exception {
  Boolean updated = false;
  ClientImage clientImage = new ClientImage();
  clientImage.setClientId(clientID);
  clientImage.setClientImage(image);

  ClientImage createdImage = clientImageRepository.saveAndFlush(clientImage);

  if (createdImage != null) {
   updated = true;
  }
  return updated;

 }

 public Boolean isClientAlreadyRegistered(BigInteger clientID) throws Exception {
  Boolean registered = false;

  ClientImage client = clientImageRepository.findByClientId(clientID);

  if (client != null) {
   registered = true;
  }
  return registered;

 }

 public PersonClient findClientByImage(byte[] image) throws Exception {
  PersonClient client = null;

  ClientImage img = clientImageRepository.findByClientImage(image);
  if (img != null) {
   client = new PersonClient();
   client = clientRepository.findById(img.getClientId());
  }

  return client;

 }



   //// findPreferenceOutbound by clientid
   public List<PreferenceOutboundMediumDTO> findPreferenceOutbound(BigInteger clientId){

       log.info("findPreferenceOutbound");

       List<PreferenceOutboundMediumDTO> response = new ArrayList<>();

       if(clientId == null)
           throw new InvalidRequestException("Please create client first!");

       List<ClientPrefOutboundMedium> outboundMedium = null;

       outboundMedium = clientPrefOutboundMediumRepository.findByClientId(clientId);

       for ( ClientPrefOutboundMedium c : outboundMedium) {

           PreferenceOutboundMediumDTO entry = new PreferenceOutboundMediumDTO();

           entry.setMedium(c.getMedium());
           entry.setClientId(c.getClientId());
           entry.setUserSignOn(c.getUserSignOn());
           entry.setOptedIn(c.getOptedIn());

           response.add(entry);

       }

       return response;
   }



     ///     findPreferencesTime  by idnumber ///
     public List<PreferenceInboundMediumDTO> findPreferenceInbound(BigInteger clientId){

        log.info("findPreferenceInbound");

         List<PreferenceInboundMediumDTO> response = new ArrayList<>();

         if(clientId == null)
             throw new InvalidRequestException("Please create client first!");

         List<ClientPrefInboundMedium> inboundMedium = null;

         inboundMedium = clientPrefInboundMediumRepository.findByClientId(clientId);

         /// return getup consent ///
         for ( ClientPrefInboundMedium c : inboundMedium) {

             PreferenceInboundMediumDTO entry = new PreferenceInboundMediumDTO();

             entry.setMedium(c.getMedium());
             entry.setClientId(c.getClientId());
             entry.setUserSignOn(c.getUserSignOn());
            entry.setOptedIn(c.getOptedIn());

             response.add(entry);

         }

         return response;
     }



    ///     findPreferencesTime  by idnumber ///
    public ClientPrefContactTimeDto findPreferencesTime(BigInteger clientId){
        log.info("findPreferencesTime");
        ClientPrefContactTimeDto response = null;

        if(clientId == null)
            throw new InvalidRequestException("Please create client first!");

        ClientPrefContactTime contacttime = new ClientPrefContactTime();

        contacttime = clientPrefContactTimeRepository.findByClientId(clientId);

        if(contacttime != null) {
            response = new ClientPrefContactTimeDto();
            response.setContactTime(contacttime.getContactTime());
            response.setClientId(contacttime.getClientId());
            response.setUserSignOn(contacttime.getUserSignOn());
        }

        return response;
    }

  /// client consent settings by idnumber ///
  public List<ClientMarketConsentDetailsDto> findMarketConsent(BigInteger clientId){
   log.info("getMarketConsent");
   List<ClientMarketConsentDetailsDto> response = new ArrayList<>();

    if(clientId == null)
        throw new InvalidRequestException("Please create client first!");


     List<ClientMarketConsent> getupConsentlist = marketConsentRepository.findByClientId(clientId);

        /// return getup consent ///
         for ( ClientMarketConsent c : getupConsentlist) {

          ClientMarketConsentDetailsDto entry = new ClientMarketConsentDetailsDto();

          entry.setClientId(c.getClientId());
          entry.setConsentType(c.getConsentType());
          entry.setUserSignOn(c.getUserSignOn());
          entry.setId(c.getId());
          entry.setOptedIn(c.getOptedIn());

          response.add(entry);

         }

      return response;
  }

  /// client Preference settings by idnumber ///
  public ClientPreferenceDetailsDto findPreferences(BigInteger clientId){
       log.info("getPreferences");

       ClientPreferenceDetailsDto response = new ClientPreferenceDetailsDto();

          /////create time
          response.setContactTimeOfDay(this.findPreferencesTime(clientId));

          /// create inbound medium
          response.setInboundMedium(this.findPreferenceInbound(clientId));

          /// create outbound medium
          response.setOutboundMedium(this.findPreferenceOutbound(clientId));

//      log.info("details = "+ response.toString());

     return response;
  }



 ////////////////////// delete mothods ///////////////////////////////////

 @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
 public UpdateResponseDto deleteUnselectedMarketConsent(List<ClientMarketConsentDetailsDto> consentSet) {
     String step = "";
     UpdateResponseDto response = new UpdateResponseDto();
     try {
         List<ClientMarketConsent> currentConsent = marketConsentRepository.findByClientId(consentSet.get(0).getClientId());
            if(!currentConsent.isEmpty()) {
                for (ClientMarketConsent c : currentConsent) {

                    if (consentSet.get(0).getConsentType().isEmpty() || consentSet.get(0).getConsentType().equals("")) {
                        this.CreateMarketConsentHistory(c);

                        log.info("##### DELETE FROM  currentConsent####" + c.toString());
                        marketConsentRepository.delete(c);
                    } else {
                        step = c.getConsentType();
                        if (!consentSet.stream().anyMatch(o -> o.getConsentType().equals(c.getConsentType()))) {
                            this.CreateMarketConsentHistory(c);

                            log.info("##### DELETE FROM  currentConsent####" + c.toString());
                            marketConsentRepository.delete(c);
                        }
                    }
                }
            }

     } catch (Exception e) {
         log.info(e.getMessage());
         response.setErrorStatus(Boolean.TRUE);
         response.setResponseMessage("Error! could not delete consent type, '" + step + "'. Please fix the errors and retry.");
     }
     return response;
 }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto deleteUnselectedOutboundPref(List<PreferenceOutboundMediumDTO> pref) {
        String step = "";
        UpdateResponseDto response = new UpdateResponseDto();
        try {
            List<ClientPrefOutboundMedium> currentMedium = clientPrefOutboundMediumRepository.findByClientId(pref.get(0).getClientId());

            for (ClientPrefOutboundMedium c : currentMedium) {

                if (currentMedium.get(0).getMedium().isEmpty() || currentMedium.get(0).getMedium().equals("")) {
                    this.CreateContactPreferenceOutboundHistory(c);

                    log.info("##### DELETE FROM  currentConsent####" + c.toString());
                    clientPrefOutboundMediumRepository.delete(c);
                } else {

                    step = c.getMedium();
                    if (!pref.stream().anyMatch(o -> o.getMedium().equals(c.getMedium()))) {
                        this.CreateContactPreferenceOutboundHistory(c);
                        clientPrefOutboundMediumRepository.delete(c);
                    }
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! could not delete contact Pref Outbound medium, '" + step + "'. Please fix the errors and retry.");
        }
        return response;

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto deleteUnselectedInboundPref(List<PreferenceInboundMediumDTO> pref) {
        String step = "";
        UpdateResponseDto response = new UpdateResponseDto();
        try {
            List<ClientPrefInboundMedium> currentinboundMedium = clientPrefInboundMediumRepository.findByClientId(pref.get(0).getClientId());

            for (ClientPrefInboundMedium c : currentinboundMedium) {

                if (pref.get(0).getMedium().isEmpty() || pref.get(0).getMedium().equals("")) {
                    this.CreateContactPreferenceInboundHistory(c);
                    log.info("##### DELETE FROM  currentinboundMedium####" + c.toString());
                    clientPrefInboundMediumRepository.delete(c);
                } else {

                    step = c.getMedium();
                    if (!pref.stream().anyMatch(o -> o.getMedium().equals(c.getMedium()))) {
                        this.CreateContactPreferenceInboundHistory(c);
                        log.info("##### DELETE FROM  currentinboundMedium####" + c.toString());
                        clientPrefInboundMediumRepository.delete(c);
                    }
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! could not delete contact Pref Inbound medium, '" + step + "'. Please fix the errors and retry.");
        }
        return response;

    }



     @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
     public UpdateResponseDto deleteUnselectedContactType(List<ClientContactDetailsDto> contact) {
         String step = "";
         UpdateResponseDto response = new UpdateResponseDto();
         try {
             List<ClientContact> currentContacts = clientContactRepository.findByClientId(contact.get(0).getClientId());

             for (ClientContact c : currentContacts) {
                 step = c.getContactType();
                 if (!contact.stream().anyMatch(o -> o.getContactType().equals(c.getContactType()))) {
                     this.CreateClientContactHistory(c);
                     clientContactRepository.delete(c);
                 }
             }
         } catch (Exception e) {
             log.info(e.getMessage());
             response.setErrorStatus(Boolean.TRUE);
             response.setResponseMessage("Error! could not delete contact type, '" + step + "'. Please fix the errors and retry.");
         }

         return response;

     }



 //////////////////////// create methods //////////////////////////////////

    public ClientPreferenceDetailsDto createPreferences(ClientPreferenceDetailsDto request) {

        ClientPreferenceDetailsDto response = new ClientPreferenceDetailsDto();

        if (request != null) {

            /////create time
            this.createPreferenceTime( request.getContactTimeOfDay());

            /// create inbound medium
            this.createPreferenceInbound(request.getInboundMedium());

            /// create outbound medium
            this.createPreferenceOutbound(request.getOutboundMedium());


            response = request;
        }


        return response;
    }

 @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
 public EmploymentDetailsDto createEmploymentDetails(EmploymentDetailsDto request) {

     EmploymentDetailsDto createdEmploymentDetails = new EmploymentDetailsDto();
     if (request != null && request.getEmploymentType() != null) {

         log.info("ABOUT TO CREATE EMPLOYMENT DETAILS " + request.toString());

         EmploymentTypeLookup foundEmpType = employmentTypeRepository
                 .findByEmploymentType(request.getEmploymentType());
         if (foundEmpType == null) {
             throw new InvalidRequestException(
                     "Employment type  must be 'Permanently Employed', 'Self-Employed' or 'Contractor'");
         }

         IndustryLookup industry = industryRepository.findByMetIndustryName(request.getIndustry());
         if (industry == null) {
             throw new InvalidRequestException(
                     "Industry is invalid, please call the getIndustryList API for valid industries");
         }

//            OccupationLookup occupation = occupationRepository.findByOccupationType(request.getOccupation());
//            if(occupation == null){
//                throw new InvalidRequestException("Occupation is invalid, please call the getOccupationList API for valid occupations");
//            }

         ClientEmploymentDetails empDetails = new ClientEmploymentDetails();
         empDetails.setClientId(request.getClientId());
         empDetails.setEmployerName(request.getEmployerName());
         empDetails.setEmploymentType(request.getEmploymentType());
         empDetails.setGrossSalaryAmount(request.getGrossSalaryAmount());
         empDetails.setIndustry(request.getIndustry());
         empDetails.setOccupation(request.getOccupation());
         empDetails.setUserSignOn(request.getUserSignOn());

         ClientEmploymentDetails dbEmploymentDetails = clientEmploymentDetailsRepository.saveAndFlush(empDetails);
         createdEmploymentDetails.setEmployerName(dbEmploymentDetails.getEmployerName());
         createdEmploymentDetails.setEmploymentType(dbEmploymentDetails.getEmploymentType());
         createdEmploymentDetails.setGrossSalaryAmount(dbEmploymentDetails.getGrossSalaryAmount());
         createdEmploymentDetails.setIndustry(dbEmploymentDetails.getIndustry());
         createdEmploymentDetails.setOccupation(dbEmploymentDetails.getOccupation());

     }
     return createdEmploymentDetails;
 }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PersonClient createPersonClient(ClientDetailsDto request) throws Exception {

        PersonClient client = clientRepository.findByIdNumber(request.getIdNumber());
        if (client != null) {
            return client;
        } else {

            // create person client
            client = new PersonClient();
            client.setGetupClientNumber(clientRepository.getNextClientNumber());
            client.setIdNumber(request.getIdNumber());
            client.setIdType(request.getIdType());
            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDateOfBirth());
            client.setDateOfBirth(dob);
            client.setFirstName(request.getFirstName());
            client.setSurname(request.getSurname());
            client.setInitials(request.getInitials());
            client.setTitle(request.getTitle());
            client.setGender(request.getGender());
            client.setMaritalStatus(request.getMaritalStatus());
            client.setEthnicity(request.getEthnicity());
            client.setEducationLevel(request.getEducationLevel());
            client.setUserSignon(request.getUserSignOn());

            log.info("ABOUT TO SAVE THIS CLIENT " + client.toString());

            PersonClient createdClient = clientRepository.saveAndFlush(client);

            return createdClient;

        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ClientBankDetail createClientBankDetail(ClientBankDetailDto request) throws Exception {

        ClientBankDetail clientBankDetail = clientBankDetailRepository.findByClientID(request.getClientID());

        if (clientBankDetail != null) {

            ClientBankDetailHistory clientBankDetailHistory = new ClientBankDetailHistory();
            clientBankDetailHistory.setClientID(clientBankDetail.getClientID());
            clientBankDetailHistory.setBankName(clientBankDetail.getBankName());
            clientBankDetailHistory.setAccountNumber(clientBankDetail.getAccountNumber());
            clientBankDetailHistory.setAccountType(clientBankDetail.getAccountType());
            clientBankDetailHistory.setBranchCode(clientBankDetail.getBranchCode());
            clientBankDetailHistory.setAccountHolderName(clientBankDetail.getAccountHolderName());
            clientBankDetailHistory.setCreatedAt(clientBankDetail.getCreatedAt());
            clientBankDetailHistory.setUpdatedAt(clientBankDetail.getUpdatedAt());
            clientBankDetailHistory.setValidationAt(clientBankDetail.getValidationAt());
            clientBankDetailHistory.setVerificationAt(clientBankDetail.getVerificationAt());
            log.info("Create client bank detail history");
            clientBankDetailHistoryRepository.saveAndFlush(clientBankDetailHistory);

            clientBankDetail.setClientID(request.getClientID());
            clientBankDetail.setBankName(request.getBankName());
            clientBankDetail.setAccountNumber(request.getAccountNumber());
            clientBankDetail.setAccountType(request.getAccountType());
            clientBankDetail.setBranchCode(request.getBranchCode());
            clientBankDetail.setAccountHolderName(request.getAccountHolderName());
            clientBankDetail.setUpdatedAt(request.getUpdatedAt());
            clientBankDetail.setValidationAt(request.getValidationAt());
            clientBankDetail.setVerificationAt(request.getVerificationAt());

            log.info("Update client bank detail " + clientBankDetail.getId());
            clientBankDetail = clientBankDetailRepository.saveAndFlush(clientBankDetail);


            return clientBankDetail;

        } else {
            clientBankDetail = new ClientBankDetail();
            clientBankDetail.setClientID(request.getClientID());
            clientBankDetail.setBankName(request.getBankName());
            clientBankDetail.setAccountNumber(request.getAccountNumber());
            clientBankDetail.setAccountType(request.getAccountType());
            clientBankDetail.setBranchCode(request.getBranchCode());
            clientBankDetail.setAccountHolderName(request.getAccountHolderName());
            clientBankDetail.setUpdatedAt(request.getUpdatedAt());
            clientBankDetail.setValidationAt(request.getValidationAt());
            clientBankDetail.setVerificationAt(request.getVerificationAt());

            log.info("create new client bank detail");
            clientBankDetail = clientBankDetailRepository.saveAndFlush(clientBankDetail);
        }

        return clientBankDetail;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createClientBankDetailHistory(ClientBankDetailHistory  clientBankDetailHistory){
        clientBankDetailHistoryRepository.saveAndFlush(clientBankDetailHistory);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientContactDetailsDto> createClientContacts(List<ClientContactDetailsDto> request) {
        List<ClientContactDetailsDto> createdContacts = new ArrayList<ClientContactDetailsDto>();
        for (ClientContactDetailsDto c : request) {

            ContactTypeLookup cType = contactTypeRepository.findByContactType(c.getContactType());
            if (cType == null) {
                throw new InvalidRequestException("Contact type  must be 'Cellphone' or 'Email Address'");
            }

            ClientContact  exists = clientContactRepository.findByContactTypeAndClientId(c.getContactType(), c.getClientId());

                if(exists != null) {

                    ClientContactDetailsDto dbContactDetails = new ClientContactDetailsDto();
                    dbContactDetails.setClientId(exists.getClientId());
                    dbContactDetails.setContactType(exists.getContactType());
                    dbContactDetails.setContactValue(exists.getContactValue());
                    createdContacts.add(dbContactDetails);

            }else {

                ClientContact contact = new ClientContact();
                contact.setClientId(c.getClientId());
                contact.setContactType(c.getContactType());
                contact.setContactValue(c.getContactValue());
                contact.setUserSignOn(c.getUserSignOn());

                ClientContact createdContact = clientContactRepository.save(contact);

                ClientContactDetailsDto dbContactDetails = new ClientContactDetailsDto();
                dbContactDetails.setClientId(createdContact.getClientId());
                dbContactDetails.setContactType(createdContact.getContactType());
                dbContactDetails.setContactValue(createdContact.getContactValue());
                createdContacts.add(dbContactDetails);
            }


        }
        return createdContacts;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientLifeHackDetailsDto> createLifeHacks(List<ClientLifeHackDetailsDto> request) {

        List<ClientLifeHackDetailsDto> createdLifeHacks = new ArrayList<ClientLifeHackDetailsDto>();
        for (ClientLifeHackDetailsDto h : request) {

            ProductLifeHackLookup foundLifeHack = lifeHackProductLookupRepository
                    .findByProductAndLifehack(h.getProduct(), h.getLifeHack());
            if (foundLifeHack == null) {
                throw new InvalidRequestException(
                        "Invalid product/lifehack combination, please call the getProductLifeHacks API for valid combinations");
            }

            ClientLifeHack hack = new ClientLifeHack();
            hack.setClientId(h.getClientId());
            hack.setLifeHack(h.getLifeHack());
            hack.setProduct(h.getProduct());
            hack.setUserSignOn(h.getUserSignOn());

            ClientLifeHack createdLifeHack = lifeHackRepository.saveAndFlush(hack);

            ClientLifeHackDetailsDto dbDetails = new ClientLifeHackDetailsDto();
            dbDetails.setLifeHack(createdLifeHack.getLifeHack());
            dbDetails.setProduct(createdLifeHack.getProduct());

            createdLifeHacks.add(dbDetails);

        }
        return createdLifeHacks;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientMarketConsentDetailsDto> createMarketingConsent(List<ClientMarketConsentDetailsDto> request) {
     log.info("CONSENT  "  + request.toString());

        List<ClientMarketConsentDetailsDto> createdConsentList = new ArrayList<ClientMarketConsentDetailsDto>();

        for (ClientMarketConsentDetailsDto consent : request) {

            if(!consent.getConsentType().equals("")){

                MarketingConsentLookup foundConsentType = consentLookupRepository
                        .findByConsentType(consent.getConsentType());

                if (validationService.isValidMarketingConsent(foundConsentType.getConsentType())) {

                    ClientMarketConsent clientMarketConsent = new ClientMarketConsent();
                    clientMarketConsent.setClientId(consent.getClientId());
                    clientMarketConsent.setConsentType(consent.getConsentType());
                    clientMarketConsent.setUserSignOn(consent.getUserSignOn());
                    clientMarketConsent.setOptedIn(consent.getOptedIn());

                    marketConsentRepository.saveAndFlush(clientMarketConsent);

                    createdConsentList.add(consent);
                }
            }
        }

        return createdConsentList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ClientPrefContactTime createPreferenceTime(ClientPrefContactTimeDto contactPrefTimeOfDay){
        log.info("### INSERT CONTACT PREF TIME OF DAY ###");
        ClientPrefContactTime contactTime = new ClientPrefContactTime();
        if (validationService.isValidContactTimeLookup(contactPrefTimeOfDay.getContactTime()))
            contactTime.setContactTime(contactPrefTimeOfDay.getContactTime());
        contactTime.setClientId(contactPrefTimeOfDay.getClientId());
        contactTime.setUserSignOn(contactPrefTimeOfDay.getUserSignOn());

        return clientPrefContactTimeRepository.saveAndFlush(contactTime);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientPrefInboundMedium> createPreferenceInbound(List<PreferenceInboundMediumDTO> mediums){
        log.info("### INSERT CONTACT PREF INBOUND MEDIUM ###");
        List<ClientPrefInboundMedium>  inboundmediums = new ArrayList<>();

        for (PreferenceInboundMediumDTO s : mediums) {
            ClientPrefInboundMedium inbound = new ClientPrefInboundMedium();
            if (validationService.isValidPrefMediumLookup(s.getMedium())) {

                inbound.setClientId(s.getClientId());
                inbound.setUserSignOn(s.getUserSignOn());
                inbound.setMedium(s.getMedium());
                inbound.setOptedIn(s.getOptedIn());

                inboundmediums.add(inbound);
            }
        }

        return clientPrefInboundMediumRepository.saveAll(inboundmediums);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientPrefOutboundMedium> createPreferenceOutbound(List<PreferenceOutboundMediumDTO> mediums){
        log.info("### INSERT CONTACT PREF OUTBOUND MEDIUM ###");
        List<ClientPrefOutboundMedium>  outboundmediums = new ArrayList<>();

        for (PreferenceOutboundMediumDTO s : mediums) {
            ClientPrefOutboundMedium outbound = new ClientPrefOutboundMedium();
            if (validationService.isValidPrefMediumLookup(s.getMedium())) {

                outbound.setClientId(s.getClientId());
                outbound.setUserSignOn(s.getUserSignOn());
                outbound.setMedium(s.getMedium());
                outbound.setOptedIn(s.getOptedIn());

                outboundmediums.add(outbound);
            }
        }

        return clientPrefOutboundMediumRepository.saveAll(outboundmediums);
    }



    ////////////////////// CREATE HISTORY //////////////////////////////////////////////////////

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto CreatePersonHistory(PersonClientDto personClientHistory){
        UpdateResponseDto response = new UpdateResponseDto();
        try {
                    //// SAVE HISTORY
                    PersonClientHistory personHistory = new PersonClientHistory();
                    personHistory.setMarital_status(personClientHistory.getMaritalStatus());
                    personHistory.setId_type(personClientHistory.getIdType());
                    personHistory.setGender(personClientHistory.getGender());
                    personHistory.setEthnicity(personClientHistory.getEthnicity());
                    personHistory.setDateOfBirth(personClientHistory.getDateOfBirth());
                    personHistory.setFirstName(personClientHistory.getFirstName());
                    personHistory.setGetupClientNumber(personClientHistory.getGetupClientNumber());
                    personHistory.setEducationLevel(personClientHistory.getEducationLevel());
                    personHistory.setIdNumber(personClientHistory.getIdNumber());
                    personHistory.setInitials(personClientHistory.getInitials());
                    personHistory.setSurname(personClientHistory.getSurname());
                    personHistory.setTitle(personClientHistory.getTitle());
                    personHistory.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    personHistory.setUserSignon(personClientHistory.getUserSignon());

                    log.info("### CreatePersonHistory ###");
                    personClientHistoryRepository.saveAndFlush(personHistory);
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! could not Create Person History. Please fix the errors and retry.");
        }

        return response;

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto CreateMarketConsentHistory(ClientMarketConsent consentEntry){
        UpdateResponseDto response = new UpdateResponseDto();
        try {
                //// SAVE HISTORY
                ClientMarketConsentHistory consentHistory = new ClientMarketConsentHistory();
                consentHistory.setClientId(consentEntry.getClientId());
                consentHistory.setConsentType(consentEntry.getConsentType());
                consentHistory.setUserSignOn(consentEntry.getUserSignOn());
                consentHistory.setOptedIn(consentEntry.getOptedIn());
                consentHistory.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                log.info("### CreateConsentHistory - write to HISTORY table ###");
                clientMarketConsetHistoryRepository.saveAndFlush(consentHistory);
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! could not Create Market Consent History. Please fix the errors and retry.");
        }

        return response;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto CreateContactPreferenceOutboundHistory(ClientPrefOutboundMedium prefEntry){
            UpdateResponseDto response = new UpdateResponseDto();
            try {
                    //// SAVE HISTORY
                    ClientPrefOutboundMediumHistory history = new ClientPrefOutboundMediumHistory();
                    history.setClientId(prefEntry.getClientId());
                    history.setMedium(prefEntry.getMedium());
                    history.setUserSignOn(prefEntry.getUserSignOn());
                    history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    history.setOptedIn(prefEntry.getOptedIn());

                    log.info("#### insert CreatePreferenceOutboundHistory ####");
                    clientPrefOutboundMediumHistoryRepository.saveAndFlush(history);
            } catch (Exception e) {
                log.info(e.getMessage());
                response.setErrorStatus(Boolean.TRUE);
                response.setResponseMessage("Error! could not Create Contact Preference Outbound History. Please fix the errors and retry.");
            }

        return response;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto CreateContactPreferenceInboundHistory(ClientPrefInboundMedium prefEntry){
                UpdateResponseDto response = new UpdateResponseDto();
                try {
                        //// SAVE HISTORY
                        ClientPrefInboundMediumHistory history = new ClientPrefInboundMediumHistory();
                        history.setClientId(prefEntry.getClientId());
                        history.setMedium(prefEntry.getMedium());
                        history.setUserSignOn(prefEntry.getUserSignOn());
                        history.setOptedIn(prefEntry.getOptedIn());
                        history.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                        log.info("#### insert "+prefEntry.getMedium()+" CreatePreferenceInboundHistory ####");
                        clientPrefInboundMediumHistoryRepository.saveAndFlush(history);
                } catch (Exception e) {
                    log.info(e.getMessage());
                    response.setErrorStatus(Boolean.TRUE);
                    response.setResponseMessage("Error! could not Create Contact Preference Inbound History. Please fix the errors and retry.");
                }

        return response;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto CreateContactPreferenceTimeHistory(ClientPrefContactTime prefEntry){
        UpdateResponseDto response = new UpdateResponseDto();
        try {
                //// SAVE HISTORY
                ClientPrefContactTimeHistory history = new ClientPrefContactTimeHistory();
                history.setClientId(prefEntry.getClientId());
                history.setContact_time(prefEntry.getContactTime());
                history.setUserSignOn(prefEntry.getUserSignOn());
                history.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                log.info("#### insert ClientPrefContactTimeHistory ####");
                clientPrefContactTimeHistoryRepository.saveAndFlush(history);
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! could not Create Contact Preference Time History. Please fix the errors and retry.");
        }

        return response;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto CreateClientContactHistory(ClientContact contactEntry){
        UpdateResponseDto response = new UpdateResponseDto();
        try {
                //// SAVE HISTORY
                ClientContactHistory contactHistory = new ClientContactHistory();
                contactHistory.setClientId(contactEntry.getClientId());
                contactHistory.setContactType(contactEntry.getContactType());
                contactHistory.setContactValue(contactEntry.getContactValue());
                contactHistory.setUserSignOn(contactEntry.getUserSignOn());
                contactHistory.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                log.info("### CreateContactHistory - write to HISTORY table ###");
                clientContactHistoryRepository.saveAndFlush(contactHistory);
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! could Create Client Contact History. Please fix the errors and retry.");
        }

        return response;
    }


  ////////////////////// Upsert methods //////////////////////////////////

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PersonClient upsertPersonClient(PersonClientDto request) throws Exception {

        PersonClient exists = clientRepository.findByIdNumber(request.getIdNumber());
        PersonClient client = null;

        if(exists != null) {

            //// insert history
            this.CreatePersonHistory(request);

            /// update
            exists.setIdNumber(request.getIdNumber());
            exists.setIdType(request.getIdType());
            exists.setDateOfBirth(request.getDateOfBirth());
            exists.setFirstName(request.getFirstName());
            exists.setSurname(request.getSurname());
            exists.setInitials(request.getInitials());
            exists.setTitle(request.getTitle());
            exists.setGender(request.getGender());
            exists.setMaritalStatus(request.getMaritalStatus());
            exists.setEthnicity(request.getEthnicity());
            exists.setEducationLevel(request.getEducationLevel());
            exists.setUserSignon(request.getUserSignon());

            log.info("#### update PersonClient ####");
            client = personClientRepository.saveAndFlush(exists);

        }else {

            // create person client
            client = new PersonClient();
            client.setGetupClientNumber(clientRepository.getNextClientNumber());
            client.setIdNumber(request.getIdNumber());
            client.setIdType(request.getIdType());
            client.setDateOfBirth(request.getDateOfBirth());
            client.setFirstName(request.getFirstName());
            client.setSurname(request.getSurname());
            client.setInitials(request.getInitials());
            client.setTitle(request.getTitle());
            client.setGender(request.getGender());
            if(null != request.getMaritalStatus()) {
                client.setMaritalStatus(request.getMaritalStatus());
            }else {
                client.setMaritalStatus("Unknown");
            }
            if(null != request.getEthnicity()) {
                client.setEthnicity(request.getEthnicity());
            }
            if(null != request.getEducationLevel()) {
                client.setEducationLevel(request.getEducationLevel());
            }
            client.setUserSignon(request.getUserSignon());
            clientRepository.saveAndFlush(client);

            log.info("#### insert PersonClient ####");

        }

        return client;
    }



  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public List<ClientMarketConsentDetailsDto> upsertMarketingConsent(List<ClientMarketConsentDetailsDto> request) {

//      //delete from Getup if type is false
  //    this.deleteUnselectedMarketConsent(request);

      List<ClientMarketConsentDetailsDto> createdConsentList = new ArrayList<ClientMarketConsentDetailsDto>();

      if(!request.isEmpty() && (!request.get(0).getConsentType().isEmpty() || !request.get(0).getConsentType().equals(""))) {

          /////update
          for (ClientMarketConsentDetailsDto consent : request) {

                    ClientMarketConsentDetailsDto set = new ClientMarketConsentDetailsDto();

                    ClientMarketConsent exists = marketConsentRepository.findByConsentTypeAndClientId(consent.getConsentType(), consent.getClientId());

                    if (exists != null) {

                        //// insert history
                        this.CreateMarketConsentHistory(exists);

                        /// update
                        exists.setClientId(consent.getClientId());
                        exists.setConsentType(consent.getConsentType());
                        exists.setUserSignOn(consent.getUserSignOn());
                        exists.setOptedIn(consent.getOptedIn());

                        log.info("#### update ClientMarketConsent ####");
                        marketConsentRepository.saveAndFlush(exists);

                        set.setOptedIn(exists.getOptedIn());
                        set.setClientId(exists.getClientId());
                        set.setConsentType(exists.getConsentType());
                        set.setUserSignOn(exists.getUserSignOn());

                    } else {

                        ClientMarketConsent clientMarketConsent = new ClientMarketConsent();
                        clientMarketConsent.setClientId(consent.getClientId());
                        clientMarketConsent.setConsentType(consent.getConsentType());
                        clientMarketConsent.setUserSignOn(consent.getUserSignOn());
                        clientMarketConsent.setOptedIn(consent.getOptedIn());

                        log.info("#### insert ClientMarketConsent ####");
                        marketConsentRepository.saveAndFlush(clientMarketConsent);

                        set.setClientId(consent.getClientId());
                        set.setConsentType(consent.getConsentType());
                        set.setUserSignOn(consent.getUserSignOn());
                        set.setOptedIn(consent.getOptedIn());
                    }

                    createdConsentList.add(set);

          }
      }

      return createdConsentList;
  }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientContactDetailsDto> upsertClientContacts(List<ClientContactDetailsDto> request) {

//        this.deleteUnselectedContactType(request);

        List<ClientContactDetailsDto> createdContacts = new ArrayList<ClientContactDetailsDto>();

        for (ClientContactDetailsDto c : request) {
            ClientContactDetailsDto set = new ClientContactDetailsDto();
            ContactTypeLookup cType = contactTypeRepository.findByContactType(c.getContactType());
            if (cType == null) {
                throw new InvalidRequestException("Contact type  must be 'Cellphone' or 'Email Address'");
            }

            ClientContact  exists = clientContactRepository.findByContactTypeAndClientId(c.getContactType(), c.getClientId());

            if(exists != null) {

                //// insert history
                ClientContactHistory history = new ClientContactHistory();
                history.setClientId(exists.getClientId());
                history.setContactType(exists.getContactType());
                history.setContactValue(exists.getContactValue());
                history.setUserSignOn(exists.getUserSignOn());
                history.setCreatedAt(new Timestamp(System.currentTimeMillis()));


                /// update
                exists.setContactValue(c.getContactValue());
                exists.setContactType(c.getContactType());
                exists.setUserSignOn(c.getUserSignOn());

                log.info("#### insert ClientContactHistory ####");
                clientContactHistoryRepository.saveAndFlush(history);

                log.info("#### update ClientContact ####");
                clientContactRepository.saveAndFlush(exists);

                set.setClientId(exists.getClientId());
                set.setContactValue(exists.getContactValue());
                set.setContactType(exists.getContactType());
                set.setUserSignOn(exists.getUserSignOn());

            }else {

                // insert contact
                ClientContact contact = new ClientContact();
                contact.setClientId(c.getClientId());
                contact.setContactType(c.getContactType());
                contact.setContactValue(c.getContactValue());
                contact.setUserSignOn(c.getUserSignOn());

                log.info("#### insert ClientContact ####");
                clientContactRepository.saveAndFlush(contact);

                set.setClientId(c.getClientId());
                set.setContactValue(c.getContactValue());
                set.setContactType(c.getContactType());
                set.setUserSignOn(c.getUserSignOn());
            }

            createdContacts.add(set);
        }

        return createdContacts;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ClientLifeHackDetailsDto> upsertLifeHacks(List<ClientLifeHackDetailsDto> request) {

        List<ClientLifeHackDetailsDto> createdLifeHacks = new ArrayList<ClientLifeHackDetailsDto>();
        for (ClientLifeHackDetailsDto h : request) {

            ProductLifeHackLookup foundLifeHack = lifeHackProductLookupRepository
                    .findByProductAndLifehack(h.getProduct(), h.getLifeHack());
            if (foundLifeHack == null) {
                throw new InvalidRequestException(
                        "Invalid product/lifehack combination, please call the getProductLifeHacks API for valid combinations");
            }

            ClientLifeHack hack = new ClientLifeHack();
            hack.setClientId(h.getClientId());
            hack.setLifeHack(h.getLifeHack());
            hack.setProduct(h.getProduct());
            hack.setUserSignOn(h.getUserSignOn());

            ClientLifeHack createdLifeHack = lifeHackRepository.saveAndFlush(hack);

            ClientLifeHackDetailsDto dbDetails = new ClientLifeHackDetailsDto();
            dbDetails.setLifeHack(createdLifeHack.getLifeHack());
            dbDetails.setProduct(createdLifeHack.getProduct());

            createdLifeHacks.add(dbDetails);

        }
        return createdLifeHacks;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public EmploymentDetailsDto upsertEmploymentDetails(EmploymentDetailsDto request) {
        log.info("### ClientDetailService.updateEmploymentDetails ###");

        ClientEmploymentDetails exists = clientEmploymentDetailsRepository.findByClientId(request.getClientId());
        EmploymentDetailsDto emp = null;
        if(exists != null) {

            //// insert history
            ClientEmploymentDetailsHistory history = new ClientEmploymentDetailsHistory();
            history.setClientId(exists.getClientId());
            history.setEmploymentType(exists.getEmploymentType());
            history.setEmployerName(exists.getEmployerName());
            history.setIndustry(exists.getIndustry());
            history.setOccupation(exists.getOccupation());
            history.setGrossSalaryAmount(exists.getGrossSalaryAmount());
            history.setUserSignOn(exists.getUserSignOn());
            history.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            /// update
            exists.setClientId(request.getClientId());
            exists.setEmploymentType(request.getEmploymentType());
            exists.setEmployerName(request.getEmployerName());
            exists.setIndustry(request.getIndustry());
            exists.setOccupation(request.getOccupation());
            exists.setGrossSalaryAmount(request.getGrossSalaryAmount());
            exists.setUserSignOn(request.getUserSignOn());

            log.info("#### insert ClientEmploymentDetailsHistory ####");
            clientEmploymentDetailsHistoryRepository.saveAndFlush(history);

            log.info("#### update ClientEmploymentDetails ####");
            clientEmploymentDetailsRepository.saveAndFlush(exists);

            emp.setClientId(exists.getClientId());
            emp.setEmploymentType(exists.getEmploymentType());
            emp.setEmployerName(exists.getEmployerName());
            emp.setIndustry(exists.getIndustry());
            emp.setOccupation(exists.getOccupation());
            emp.setGrossSalaryAmount(exists.getGrossSalaryAmount());
            emp.setUserSignOn(exists.getUserSignOn());

        }else {

            // create Employment client
            ClientEmploymentDetails newEmp = new ClientEmploymentDetails();
            newEmp.setClientId(request.getClientId());
            newEmp.setEmploymentType(request.getEmploymentType());
            newEmp.setEmployerName(request.getEmployerName());
            newEmp.setIndustry(request.getIndustry());
            newEmp.setOccupation(request.getOccupation());
            newEmp.setGrossSalaryAmount(request.getGrossSalaryAmount());
            newEmp.setUserSignOn(request.getUserSignOn());

            log.info("#### insert ClientEmploymentDetails ####");
            clientEmploymentDetailsRepository.saveAndFlush(newEmp);

            emp.setClientId(exists.getClientId());
            emp.setEmploymentType(exists.getEmploymentType());
            emp.setEmployerName(exists.getEmployerName());
            emp.setIndustry(exists.getIndustry());
            emp.setOccupation(exists.getOccupation());
            emp.setGrossSalaryAmount(exists.getGrossSalaryAmount());
            emp.setUserSignOn(exists.getUserSignOn());
        }

        return emp;

    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<PreferenceOutboundMediumDTO> upsertContactPreferenceMediumOutbound(List<PreferenceOutboundMediumDTO> request) throws Exception {
//        log.info("#### update ContactPreferenceMediumOutbound ####");
//        this.deleteUnselectedOutboundPref(request);

        List<PreferenceOutboundMediumDTO> response = new ArrayList<>();

        if(!request.isEmpty()) {

            for (PreferenceOutboundMediumDTO outbound : request) {
                ClientPrefOutboundMedium exists = clientPrefOutboundMediumRepository.findByMediumAndClientId(outbound.getMedium(), outbound.getClientId());
                PreferenceOutboundMediumDTO instance = new PreferenceOutboundMediumDTO();
                if (exists != null) {
                    //// insert history
                    this.CreateContactPreferenceOutboundHistory(exists);

                    /// update
                    exists.setMedium(outbound.getMedium());
                    exists.setClientId(outbound.getClientId());
                    exists.setUserSignOn(outbound.getUserSignOn());
                    exists.setOptedIn(outbound.getOptedIn());

                    log.info("#### update "+outbound.getMedium()+" ContactPreferenceMediumOutbound ####");
                    clientPrefOutboundMediumRepository.saveAndFlush(exists);

                } else {
                    /// insert
                    ClientPrefOutboundMedium set = new ClientPrefOutboundMedium();

                    set.setMedium(outbound.getMedium());
                    set.setClientId(outbound.getClientId());
                    set.setUserSignOn(outbound.getUserSignOn());
                    set.setOptedIn(outbound.getOptedIn());

                    log.info("#### insert  "+outbound.getMedium()+" ContactPreferenceMediumOutbound ####");
                    clientPrefOutboundMediumRepository.saveAndFlush(set);
                }

                /// set response object
                instance.setMedium(outbound.getMedium());
                instance.setClientId(outbound.getClientId());
                instance.setUserSignOn(outbound.getUserSignOn());
                instance.setOptedIn(outbound.getOptedIn());
                response.add(instance);
            }
        }


        return response;

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<PreferenceInboundMediumDTO> upsertContactPreferenceMediumInbound(List<PreferenceInboundMediumDTO> request) throws Exception{

        List<PreferenceInboundMediumDTO> response = new ArrayList<>();

        if(!request.isEmpty()) {

            for (PreferenceInboundMediumDTO inbound : request) {
                ClientPrefInboundMedium exists = clientPrefInboundMediumRepository.findByMediumAndClientId(inbound.getMedium(), inbound.getClientId());
                PreferenceInboundMediumDTO instance = new PreferenceInboundMediumDTO();
                if (exists != null) {
                    //// insert history
                    this.CreateContactPreferenceInboundHistory(exists);

                    /// update
                    exists.setMedium(inbound.getMedium());
                    exists.setClientId(inbound.getClientId());
                    exists.setUserSignOn(inbound.getUserSignOn());
                    exists.setOptedIn(inbound.getOptedIn());

                    log.info("#### update "+inbound.getMedium()+" ContactPreferenceMediumInbound ####");
                    clientPrefInboundMediumRepository.saveAndFlush(exists);

                } else {
                    /// insert
                    ClientPrefInboundMedium set = new ClientPrefInboundMedium();

                    set.setMedium(inbound.getMedium());
                    set.setClientId(inbound.getClientId());
                    set.setUserSignOn(inbound.getUserSignOn());
                    set.setOptedIn(inbound.getOptedIn());

                    log.info("#### insert "+inbound.getMedium()+" ContactPreferenceMediumInbound ####");
                    clientPrefInboundMediumRepository.saveAndFlush(set);
                }



                /// set response object
                instance.setMedium(inbound.getMedium());
                instance.setClientId(inbound.getClientId());
                instance.setUserSignOn(inbound.getUserSignOn());
                instance.setOptedIn(inbound.getOptedIn());
                response.add(instance);
            }
        }

        return response;

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ClientPrefContactTimeDto upsertContactPreferenceTime(ClientPrefContactTimeDto request) throws Exception {

        ClientPrefContactTimeDto response = new ClientPrefContactTimeDto();

        ClientPrefContactTime exists = clientPrefContactTimeRepository.findByClientId(request.getClientId());

        if(request != null) {

            if (exists != null) {
                //// insert history
                this.CreateContactPreferenceTimeHistory(exists);

                /// update
                exists.setContactTime(request.getContactTime());
                exists.setClientId(request.getClientId());
                exists.setUserSignOn(request.getUserSignOn());

                log.info("#### update ClientPrefContactTime ####");
                clientPrefContactTimeRepository.saveAndFlush(exists);

                /// set response object
                response.setContactTime(exists.getContactTime());
                response.setClientId(exists.getClientId());
                response.setUserSignOn(exists.getUserSignOn());

            } else {
                /// insert
                ClientPrefContactTime set = new ClientPrefContactTime();

                set.setContactTime(request.getContactTime());
                set.setClientId(request.getClientId());
                set.setUserSignOn(request.getUserSignOn());

                log.info("#### update ClientPrefContactTime ####");
                clientPrefContactTimeRepository.saveAndFlush(set);

                /// set response object
                response.setContactTime(exists.getContactTime());
                response.setClientId(exists.getClientId());
                response.setUserSignOn(exists.getUserSignOn());
            }
        }

        return response;

    }


    public ClientBankDetailDto saveClientBankDetails(ClientBankDetailDto request) throws Exception {

        ClientBankDetail clientBankDetail =  this.createClientBankDetail(request);
        ClientBankDetailDto clientBankDetailDto = new ClientBankDetailDto();

        clientBankDetailDto.setId(clientBankDetail.getId());
        clientBankDetailDto.setClientID(clientBankDetail.getClientID());
        clientBankDetailDto.setBankName(clientBankDetail.getBankName());
        clientBankDetailDto.setAccountNumber(clientBankDetail.getAccountNumber());
        clientBankDetailDto.setAccountType(clientBankDetail.getAccountType());
        clientBankDetailDto.setBranchCode(clientBankDetail.getBranchCode());
        clientBankDetailDto.setAccountHolderName(clientBankDetail.getAccountHolderName());
        clientBankDetailDto.setUpdatedAt(clientBankDetail.getUpdatedAt());
        clientBankDetailDto.setValidationAt(clientBankDetail.getValidationAt());
        clientBankDetailDto.setVerificationAt(clientBankDetail.getVerificationAt());

        return clientBankDetailDto;
    }




}
