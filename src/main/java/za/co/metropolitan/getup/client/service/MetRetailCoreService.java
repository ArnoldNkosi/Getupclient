package za.co.metropolitan.getup.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.dto.external.*;
import za.co.metropolitan.getup.client.dto.external.ClientDetail;

import java.io.IOException;
import java.util.*;

@Service
public class MetRetailCoreService {

 private static final Logger log = LoggerFactory.getLogger(MetRetailCoreService.class);

 @Value("${metClientPolicy.url}")
 private String metPolicySearchURL;

    @Value("${source.system}")
    private String sourceSystem;

    @Value("${source.value}")
    private String sourceValue;

 @Value("${metkey.url}")
 private String metPartySearchURL;

    @Value("${mfpPolicyDetail.url}")
    private String metPolicyDetailUrl;

 @Value("${marketingInfo.url}")
 private String marketingInfoByMetKeyURL;

 @Value("${client.detail.url}")
 private String clientDetailURL;

 @Value("${metBusinessType}")
 private Integer businessType;

 @Value("${funeralDoc.url}")
 private String funeralContractListUrl;

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(sourceSystem, sourceValue);
        return headers;
    }

 public ClientDetail getClientDetail(String metKey){
    log.info("### MetRetailCoreService.searchClientDetail ###");
      ClientDetail clientSearchResponse = null;

      HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
      log.info("### header info : " + httpEntity + " ###");
      String url = String.format(clientDetailURL, businessType, metKey);
      RestTemplate restTemplate = new RestTemplate();
      log.info("### rest call : " + url + " ###");

       try {
          ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
          if (response.getStatusCode().equals(HttpStatus.OK)) {
             log.info("############# response: " + response.getBody());
             clientSearchResponse = new ObjectMapper().readValue(response.getBody(), ClientDetail.class);
          }
       }
       catch (HttpClientErrorException e) {
        log.error(e.getResponseBodyAsString());
        log.error("STATUS CODE: " + e.getRawStatusCode());
        log.error("MESSAGE: " + e.getMessage());

       } catch (IOException e) {
        log.error("IO EXCEPTION: " + e.getMessage());

       } catch (Exception e) {
        log.error("EXCEPTION: " +e.getMessage());

       }

    return clientSearchResponse ;
 }

 public SearchPartyResponse searchPartyByIdnumber(String idNumber){
  log.info("### MetRetailCoreService.searchPartyByIdnumber ###");
     SearchPartyResponse idNumberSearchResponse = new SearchPartyResponse();

     HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
     log.info("### header info : " + httpEntity + " ###");
     String url = String.format(metPartySearchURL, new Object[] {idNumber});
     RestTemplate restTemplate = new RestTemplate();
     log.info("### rest call : " + url + " ###");

  try {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
          log.info("############# response: " + response.getBody());
          idNumberSearchResponse = new ObjectMapper().readValue(response.getBody(), SearchPartyResponse.class);
        }
      }
      catch (HttpClientErrorException e) {
        log.error(e.getResponseBodyAsString());
        log.error("STATUS CODE: " + e.getRawStatusCode());
        log.error("MESSAGE: " + e.getMessage());

      } catch (IOException e) {
        log.error(e.getMessage());

      } catch (Exception e) {
        log.error(e.getMessage());

      }

   return idNumberSearchResponse ;
 }

    public PolicyResults findPolicyHolderbyIdNumber(String idNumber){
        log.info("### MetRetailCoreService.findPolicyHolderbyIdNumber ###");
        PolicyResults response = null;
        HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
        log.info("### header info : " + httpEntity + " ###");
        String url = String.format(metPolicySearchURL, new Object[] {idNumber});
        RestTemplate restTemplate = new RestTemplate();
        log.info("### rest call : " + url + " ###");

        try {

            ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                log.info("############# response: " + res.getBody());
                response =  new ObjectMapper().readValue(res.getBody(), PolicyResults.class);

            }
        }
        catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            log.error("STATUS CODE: " + e.getRawStatusCode());
            log.error("MESSAGE: " + e.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage());

        }

        return response;
    }

    public FuneralPolicyInfo findMfpFuneralByPolicy(String policy){
        log.info("### MetRetailCoreService.findPolicyHolderbyIdNumber ###");
        FuneralPolicyInfo response = null;
        HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
        log.info("### header info : " + httpEntity + " ###");
        String url = String.format(metPolicyDetailUrl, new Object[] {policy});
        RestTemplate restTemplate = new RestTemplate();
        log.info("### rest call : " + url + " ###");

        try {

            ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                log.info("############# response: " + res.getBody());
                response =  new ObjectMapper().readValue(res.getBody(), FuneralPolicyInfo.class);
                log.info("############# response: " + response.toString());

            }
        }
        catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            log.error("STATUS CODE: " + e.getRawStatusCode());
            log.error("MESSAGE: " + e.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage());

        }

        return response;
    }

    public String findMetKeyForClientIdNumber(String idNumber) {
    log.info("### MetRetailCoreService.findMetKeyForClientIdNumber ###");
    String businessKey = null;

        List<Party> searchResponse = this.searchPartyByIdnumber(idNumber).getParties();
        if(searchResponse != null) {
            for (Attribute attribute : searchResponse.get(0).getAttributes()) {
                if (attribute.getLink().getBusinessType() == businessType) {
                    businessKey = attribute.getLink().getBusinessKey();
                }
            }
        }

    log.info("### businessKey : " + businessKey);
    return businessKey;
 }

    public CoreContactPref getContactPrefForMetKey(String businessKey) {
        log.info("### MetRetailCoreService.getContactPrefForMetKey ###");
        CoreContactPref coreContactPref = new CoreContactPref();

        HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
        log.info("### header info : " + httpEntity + " ###");
        String url = String.format(marketingInfoByMetKeyURL.concat("/contactPreference"), businessKey);
        RestTemplate restTemplate = new RestTemplate();
        log.info("### rest call : " + url + " ###");
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                log.info("############# response: " + response.getBody());
                coreContactPref = new ObjectMapper().readValue(response.getBody(), CoreContactPref.class);
            }
        } catch (HttpClientErrorException e) {
            // marketing consent entry does not exist
            log.error(e.getResponseBodyAsString());
            log.error("STATUS CODE: " + e.getRawStatusCode());
            log.error("MESSAGE: " + e.getMessage());
        } catch (Exception e) {
            log.error("MESSAGE: " + e.getMessage());
        }
        log.info("############# coreContactPref = " + coreContactPref);
        return coreContactPref;
    }

 public CoreMarketingConsent getMarketingConsentForMetKey(String businessKey) {
  log.info("### MetRetailCoreService.getMarketingConsentForMetKey ###");
  CoreMarketingConsent coreMarketingConsent = new CoreMarketingConsent();

  HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
  log.info("### header info : " + httpEntity + " ###");
  String url = String.format(marketingInfoByMetKeyURL.concat("/consent"), businessKey);
  RestTemplate restTemplate = new RestTemplate();
  log.info("### rest call : " + url + " ###");
  try {
   ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
   if (response.getStatusCode().equals(HttpStatus.OK)) {
    log.info("############# response: " + response.getBody());
    coreMarketingConsent = new ObjectMapper().readValue(response.getBody(), CoreMarketingConsent.class);
   }
  } catch (HttpClientErrorException e) {
   // marketing consent entry does not exist
   log.error(e.getResponseBodyAsString());
   log.error("STATUS CODE: " + e.getRawStatusCode());
   log.error("MESSAGE: " + e.getMessage());
  } catch (Exception e) {
   log.error("MESSAGE: " + e.getMessage());
  }
  log.info("############# coreMarketingConsent = " + coreMarketingConsent);
  return coreMarketingConsent;

 }

    public CorePreferencesResponse updateCorePreferencesForMetKey(String businessKey, CoreContactPref coreContactPref) {
        log.info("### MetRetailCoreService.updateCorePreferences ###");
        CorePreferencesResponse corePreferencesResponse = new CorePreferencesResponse();
        String jsonRequest = null;
        if(coreContactPref.getContactMethod() == null) {
            jsonRequest = new Gson().toJson(coreContactPref.getBestContactTime());
        }
        else {
            jsonRequest = new Gson().toJson(coreContactPref);
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonRequest, this.createHttpHeaders());
        log.info("### header info : " + httpEntity + " ###");
        String url = String.format(marketingInfoByMetKeyURL.concat("/contactPreference"), businessKey);
        RestTemplate restTemplate = new RestTemplate();
        log.info("### rest call : " + url + " ###");
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                log.info("############# response: " + response.getBody());
                corePreferencesResponse.setErrorStatus(Boolean.FALSE);
                corePreferencesResponse.setResponseMessage(response.getBody());
            }
        } catch (HttpClientErrorException e) {
            // marketing consent entry does not exist

            corePreferencesResponse.setResponseMessage(e.getMessage());
            corePreferencesResponse.setErrorStatus(Boolean.TRUE);
            log.error(e.getResponseBodyAsString());
            log.error("STATUS CODE: " + e.getRawStatusCode());
            log.error("MESSAGE: " + e.getMessage());
        } catch (Exception e) {

            corePreferencesResponse.setResponseMessage(e.getMessage());
            corePreferencesResponse.setErrorStatus(Boolean.TRUE);
            log.error("MESSAGE: " + e.getMessage());
        }
        log.info("############# corePreferencesResponse = " + corePreferencesResponse);
        return corePreferencesResponse;
    }

 public CoreMarketingConsentResponse updateMarketingConsentForMetKey(String businessKey, CoreMarketingConsent coreMarketingConsent) {
      log.info("### MetRetailCoreService.findMarketingConsentForMetKey ###");
      CoreMarketingConsentResponse coreMarketingConsentResponse = null;
      String jsonRequest = new Gson().toJson(coreMarketingConsent);
      HttpEntity<String> httpEntity = new HttpEntity<>(jsonRequest, this.createHttpHeaders());
      log.info("### header info : " + httpEntity + " ###");
      String url = String.format(marketingInfoByMetKeyURL.concat("/consent"), businessKey);
      RestTemplate restTemplate = new RestTemplate();
      log.info("### rest call : " + url + " ###");
      try {
       ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
       if (response.getStatusCode().equals(HttpStatus.OK)) {
        log.info("############# response: " + response.getBody());
        coreMarketingConsentResponse = new ObjectMapper().readValue(response.getBody(), CoreMarketingConsentResponse.class);
       }
      } catch (HttpClientErrorException e) {
       // marketing consent entry does not exist
       log.error(e.getResponseBodyAsString());
       log.error("STATUS CODE: " + e.getRawStatusCode());
       log.error("MESSAGE: " + e.getMessage());
      } catch (Exception e) {
       log.error("MESSAGE: " + e.getMessage());
      }
      log.info("############# marketConsentResponse = " + coreMarketingConsentResponse);
      return coreMarketingConsentResponse;
 }

 public CoreContactPref findCoreContactPref(String idNumber) {
     log.info("### MetRetailCoreService.findCoreContactPref ###");
     String metKey = this.findMetKeyForClientIdNumber(idNumber);

     log.info("metKey "+ metKey);

     CoreContactPref coreContactPref = null;
     if (metKey != null) {
         coreContactPref = this.getContactPrefForMetKey(metKey);
     }
     return coreContactPref;

 }

 public CoreMarketingConsent findCoreMarketingConsent(String idNumber) {
  log.info("### MetRetailCoreService.findCoreMarketingConsent ###");

  String metKey = this.findMetKeyForClientIdNumber(idNumber);

//  // TEST
  log.info("metKey "+ metKey);

    CoreMarketingConsent coreMarketingConsent = new CoreMarketingConsent();
    if (metKey != null) {
     coreMarketingConsent = this.getMarketingConsentForMetKey(metKey);
    }
    return coreMarketingConsent;

 }

 public CoreMarketingConsentResponse updateCoreMarketingConsent(String idNumber, CoreMarketingConsent request) {
  log.info("### MetRetailCoreService.updateCoreMarketingConsent ###");
  String metKey = this.findMetKeyForClientIdNumber(idNumber);

  // TEST
  //metKey = "MET1447009";

  CoreMarketingConsentResponse coreMarketingConsentResponse = null;
  if (metKey != null) {
   coreMarketingConsentResponse = this.updateMarketingConsentForMetKey(metKey, request);
  }
//  else{
//     /// create party
//
//  }
  return coreMarketingConsentResponse;
 }

    public CorePreferencesResponse updateCorePreferences(String idnumber, CoreContactPref clientPref) {
        log.info("### MetRetailCoreService.updateCorePreferences ###");
        String metKey = this.findMetKeyForClientIdNumber(idnumber);

        CorePreferencesResponse corePreferencesResponse = null;
        if (metKey != null) {
            corePreferencesResponse = this.updateCorePreferencesForMetKey(metKey, clientPref);
        }
//  else{
//     /// create party
//
//  }
        return corePreferencesResponse;
    }


    public PolicyContractList GetFuneralListByPolicy(String policyNumber) {

        String url = funeralContractListUrl.concat(policyNumber);

        log.info("### MetRetailCoreService.GetFuneralListByPolicy ###");
        PolicyContractList response = null;
        HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpHeaders());
        log.info("### header info : " + httpEntity + " ###");
        RestTemplate restTemplate = new RestTemplate();
        log.info("### rest call : " + url + " ###");

        try {

            ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                log.info("############# response: " + res.getBody());
                response =  new ObjectMapper().readValue(res.getBody(), PolicyContractList.class);
                log.info("############# response: " + response.toString());

            }
        }
        catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            log.error("STATUS CODE: " + e.getRawStatusCode());
            log.error("MESSAGE: " + e.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage());

        }

        return response;
    }

}
