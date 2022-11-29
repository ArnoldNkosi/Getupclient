package za.co.metropolitan.getup.client.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.metropolitan.getup.client.dto.*;
import za.co.metropolitan.getup.client.dto.client.ContactPrefRequestDto;
import za.co.metropolitan.getup.client.dto.client.CreateClientBankDetailRequestDto;
import za.co.metropolitan.getup.client.dto.client.CreateClientRequestDto;
import za.co.metropolitan.getup.client.dto.client.MarketingConsentRequestDto;
import za.co.metropolitan.getup.client.dto.external.ContactPreferenceResponse;
import za.co.metropolitan.getup.client.dto.external.MarketingConsentDetailsResponse;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;
import za.co.metropolitan.getup.client.exception.ResourceNotFoundException;
import za.co.metropolitan.getup.client.model.client.ClientBankDetail;
import za.co.metropolitan.getup.client.model.client.ClientContact;
import za.co.metropolitan.getup.client.model.client.PersonClient;
import za.co.metropolitan.getup.client.modelRepository.client.ClientBankDetailRepository;
import za.co.metropolitan.getup.client.modelRepository.client.PersonClientRepository;
import za.co.metropolitan.getup.client.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/details")
public class ClientDetailController {

 private static final Logger log = LoggerFactory.getLogger(ClientDetailController.class);

 @Autowired
 private ClientDetailService clientService;

 @Autowired
 private SharedService sharedService;

 @Autowired
 private ValidationService validationService;

 @Autowired
 private GetupSecurityService getupSecurityService;


    @Autowired
    private ClientBankDetailRepository clientBankDetailRepository;

@Value("${thirdparty_role}")
private String thirdpartyRole;
@Value("${email_contact_type}")
private String emailtype;

@Value("${cell_contact_type}")
private String celltype;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private zendeskManagementService zendeskManagementService;


    @Autowired
    private PersonClientRepository clientRepository;

    private final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };

    /***     * Get the client ip address (which can penetrate reverse proxy)* @param request     * @return     */
    private String getClientIpAddress() {
        for (String header : HEADERS_TO_TRY) {
            String ip = servletRequest.getHeader(header);

            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }

        return servletRequest.getRemoteAddr();
    }

    @GetMapping(value = "findGetupClient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable(value = "id") @NotNull String id) throws Exception {

        return ResponseEntity.ok(this.sharedService.findClientDetails(id));
    }

    @CrossOrigin
     @RequestMapping(value = "/findClientByIdNumber", method = RequestMethod.GET, produces = "application/json")
     public ClientDetailsDto findClientByIdNumber(@RequestParam("clientIdNumber") String clientNumber) throws Exception {


        ClientDetailsDto client = new ClientDetailsDto();

      if (clientNumber == null || clientNumber.equals(""))
       throw new InvalidRequestException("Client Number cannot be null or empty.");

           client = sharedService.findClientDetails(clientNumber);

           return client;
     }


    @CrossOrigin
    @RequestMapping(value = "/fetchClientInfo", method = RequestMethod.GET, produces = "application/json")
    public ClientDetailsDto fetchClientInfo( @RequestParam("clientNumber") String clientNumber ) throws Exception {

        ClientDetailsDto client = new ClientDetailsDto();

        if (clientNumber == null || clientNumber.equals(""))
            throw new InvalidRequestException("Client Number cannot be null or empty.");

        client = sharedService.fetchGetupClient(clientNumber);

        return client;
    }


    @CrossOrigin
    @RequestMapping(value = "/findClientProfile", method = RequestMethod.GET, produces = "application/json")
    public ClientDetailsDto getClientProfile( @RequestParam("clientNumber") String clientNumber ) throws Exception {

        ClientDetailsDto client = new ClientDetailsDto();

        if (clientNumber == null || clientNumber.equals(""))
            throw new InvalidRequestException("Client Number cannot be null or empty.");

        client = sharedService.findClientDetails(clientNumber);

        return client;
    }

    @CrossOrigin
    @ApiOperation(value = "Create Client profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 400, message = "Missing body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/createGetupClient", method = RequestMethod.POST, consumes = "application/json")
    public ClientDetailsDto createGetupClient(@RequestBody CreateClientRequestDto request) throws Exception {

        log.info("ClientDetailController.createGetupClient");

        log.info("ClientDetailsDto: " + request);

        ClientDetailsDto client = new ClientDetailsDto();

        /** validation */
        if (validationService.isValidPersonClient(sharedService.mappingClientDetailsRequest(request))) {

            client = clientService.saveGetupClient(sharedService.mappingClientDetailsRequest(request));

        }

        if (client != null && request.getAutoCreateUserProfile()) {
            ///// create user profile ////
            CreateUserProfileDto userPROFILE = new CreateUserProfileDto();

            for (ClientContactDetailsDto contacts : client.getContactDetails()) {
                if (contacts.getContactType().equals(emailtype)) {
                    userPROFILE.setEmail(contacts.getContactValue());
                }

                if (contacts.getContactType().equals(celltype)) {
                    userPROFILE.setCellNumber(contacts.getContactValue());
                }
            }

            userPROFILE.setIdNumber(request.getIdNumber());
            userPROFILE.setName(request.getFirstName());
            userPROFILE.setSurname(request.getSurname());
            userPROFILE.setIpAddress(this.getClientIpAddress());
            userPROFILE.setRole(thirdpartyRole);

            if (!StringUtils.isEmpty(userPROFILE.getIdNumber())) {
                userPROFILE.setUsername(userPROFILE.getIdNumber());
            }



                boolean created = getupSecurityService.autoCreateUser(userPROFILE);

               if (created)
                {
              client.setUsername(userPROFILE.getIdNumber());
                }

             //   else {
                    //create zendesk ticket.
           //        ResponseDto responseDto = zendeskManagementService.createClientVerificationError(userPROFILE);
              //  }

                log.info("User profile created: " + created);
            }
        return client;
    }


    @CrossOrigin
    @RequestMapping(value = "/saveClientContacts", method = RequestMethod.POST, consumes = "application/json")
    public List<ClientContactDetailsDto> saveClientContacts(@RequestBody List<ClientContactDetailsDto> request) throws Exception {

        log.info("ClientDetailController.saveClientContacts");

        log.info("ClientContact: " + request.toString());

        return clientService.upsertClientContacts(request);

    }


    @ApiOperation(value = "does exists clientId or clientIdNumber or getupClientNumber on getup or core")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "ClientId not found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/doesClientExist", method = RequestMethod.GET)
    public Boolean  doesClientExist(    @RequestParam(value = "clientId") Optional<String> clientId,
                                        @RequestParam(value = "clientIdNumber") Optional<String> clientIdNumber,
                                        @RequestParam(value = "getupClientNumber") Optional<String> getupClientNumber
                                                         ) throws Exception {

        return sharedService.doesClientExist(clientIdNumber.orElse(null),
                                                clientId.orElse(null),
                                                getupClientNumber.orElse(null));

    }


    @CrossOrigin
    @ApiOperation(value = "find client marketing consent")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Client not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/findMarketingConsent", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> findMarketingConsent(@RequestParam("idnumber") String idnumber){

        log.info("get client consent settings: " + idnumber);
        if(idnumber == null || idnumber.isEmpty())
            return new ResponseEntity<>("Id number required", HttpStatus.BAD_REQUEST);

        List<ClientMarketConsentDetailsDto> hasConsent = new ArrayList<>();

        try {
            hasConsent = sharedService.findMarketingConsent(idnumber);

            if(!hasConsent.isEmpty()) {
                log.info("Consent data exists: " + idnumber);
                return new ResponseEntity<>(hasConsent, HttpStatus.OK);
            }else {
                log.info("Consent data does NOT exist: " + idnumber);
                return new ResponseEntity<>(hasConsent, HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
     @ApiOperation(value = "save client market consent")
     @ApiResponses({
             @ApiResponse(code = 200, message = "OK"),
             @ApiResponse(code = 204, message = "Client not found"),
             @ApiResponse(code = 400, message = "Bad Request"),
             @ApiResponse(code = 404, message = "Not found")
     })
     @RequestMapping(value = "/saveMarketingConsent", method = RequestMethod.POST, produces = "application/json")
     public ResponseEntity<MarketingConsentDetailsResponse> saveMarketingConsent(@RequestBody MarketingConsentRequestDto request) throws Exception {
         log.info("ClientDetailController.saveMarketingConsent");
         log.info("MarketingConsentDetails: " + request.toString());

         try {

             MarketingConsentDetailsResponse response = sharedService.saveMarketingConsent(sharedService.mappingMarketingConsentRequest(request));

             if(!response.getUpdateResponse().isErrorStatus() ) {
                 return new ResponseEntity<>(response, HttpStatus.OK);
             }else {
                 return new ResponseEntity<>(response, HttpStatus.CONFLICT);
             }

         } catch (Exception e) {
             log.error(e.getMessage());
             return new ResponseEntity<MarketingConsentDetailsResponse>(HttpStatus.BAD_REQUEST);
         }

     }


    @CrossOrigin
    @ApiOperation(value = "find Preference by Idnumber")
    @ApiResponses({
           @ApiResponse(code = 200, message = "OK"),
           @ApiResponse(code = 204, message = "No Preference data"),
           @ApiResponse(code = 400, message = "Missing body"),
           @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/findContactPreference", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ClientPreferenceDetailsDto> getPreference(@RequestParam("idnumber") String idnumber){
      log.info("get client Preference settings: " + idnumber);
      if(idnumber == null)
       return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

      else if(idnumber.isEmpty())
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    ClientPreferenceDetailsDto hasPreference;

      try {
       hasPreference = sharedService.findContactPref(idnumber);

       if(hasPreference != null) {
          log.info("Preference data exists: " + idnumber);
          return new ResponseEntity<>(hasPreference, HttpStatus.OK);
       }else {
          log.info("Preference data does NOT exist: " + idnumber);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

      } catch (Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    }

    @CrossOrigin
    @ApiOperation(value = "save client contact medium preferences")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/saveContactPreferences", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ContactPreferenceResponse> saveContactPreferences(@RequestBody ContactPrefRequestDto request) {
        log.info("ClientDetailController.saveContactPreferences");
        log.info("ContactPrefRequestDto: " + request.toString());

        try {

            validationService.isValidContactTimeLookup(request.getBestTimeToContact());

            ContactPreferenceResponse response = sharedService.saveContactPreferences(sharedService.mappingContactPrefRequest(request));

            if(!response.getUpdateResponse().isErrorStatus() ) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<ContactPreferenceResponse>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/saveConsentAndPreferences", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UpdateResponseDto> saveConsentAndPreferences(@RequestBody ConsentAndPrefDTO request) {
        log.info("ClientDetailController.saveConsentAndPreferences");
        log.info("ConsentAndPrefDTO: " + request.toString());

        try {

            validationService.isValidContactTimeLookup(request.getBestTimeToContact());

            MarketingConsentRequestDto consent = new MarketingConsentRequestDto();
            consent.setClientId(request.getClientId());
            consent.setUserSignOn(request.getUserSignOn());
            consent.setMarketingResearch(request.getMarketingResearch());
            consent.setShareInfoInternally(request.getShareInfoInternally());
            consent.setNewSolutions(request.getNewSolutions());

            ContactPrefRequestDto pref = new ContactPrefRequestDto();
            pref.setBestTimeToContact(request.getBestTimeToContact());
            pref.setClientId(request.getClientId());
            pref.setInboundMedium(request.getInboundMedium());
            pref.setOutboundMedium(request.getOutboundMedium());
            pref.setUserSignOn(request.getUserSignOn());

            UpdateResponseDto response = clientService.saveGetupConsentAndPreference( sharedService.mappingMarketingConsentRequest(consent),
                    sharedService.mappingContactPrefRequest(pref));

            if(!response.isErrorStatus() ) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<UpdateResponseDto>(HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin
    @ApiOperation(value = "Create Client Bank Details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 400, message = "Missing body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/createClientBankDetail", method = RequestMethod.POST, consumes = "application/json")
    public ClientBankDetailDto createClientBankDetail(@RequestBody CreateClientBankDetailRequestDto request) throws Exception {

        ClientBankDetailDto bankDetailDto = new ClientBankDetailDto();
        bankDetailDto.setClientID(request.getClientID());
        bankDetailDto.setBankName(request.getBankName());
        bankDetailDto.setAccountNumber(request.getAccountNumber());
        bankDetailDto.setAccountType(request.getAccountType());
        bankDetailDto.setBranchCode(request.getBranchCode());
        bankDetailDto.setAccountHolderName(request.getAccountHolderName());
        bankDetailDto.setUpdatedAt(request.getUpdatedAt());
        bankDetailDto.setValidationAt(request.getValidationAt());
        bankDetailDto.setVerificationAt(request.getVerificationAt());

        bankDetailDto = clientService.saveClientBankDetails(bankDetailDto);

        return bankDetailDto;
    }

    @CrossOrigin
    @ApiOperation(value = "GET Client Bank Details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 400, message = "Missing body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/clientBankDetails/{clientID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> clientBankDetails(@PathVariable(value = "clientID") @NotNull BigInteger clientID) throws Exception {

        ClientBankDetail clientBankDetail = null;

        try {

            clientBankDetail = clientBankDetailRepository.findByClientID(clientID);

            if(clientBankDetail == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no banking details found");
            }

        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            return ResponseEntity.badRequest().body(dataIntegrityViolationException.getMessage());
        }catch (Exception e)
        { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());}

        return ResponseEntity.ok().body(clientBankDetail);

    }

}
