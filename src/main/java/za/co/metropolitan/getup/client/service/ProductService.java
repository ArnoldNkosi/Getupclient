package za.co.metropolitan.getup.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.dto.PolicyDetailsDto;
import za.co.metropolitan.getup.client.dto.ProductsDto;
import za.co.metropolitan.getup.client.helper.ServiceHelper;

import javax.net.ssl.SSLContext;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Value("${atomPolicyInfo.url}")
    private String atomPolicyInfoUrl;

    @Value("${cvpBasicAuth.name}")
    private String basicAuthUser;

    @Value("${cvpBasicAuth.password}")
    private String basicAuthPsw;

    @Value("${atomPolicyDoc.url}")
    private String policyScheduleUrl;

    public HttpHeaders createHttpBasicAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBasicAuth(basicAuthUser , basicAuthPsw);
        return headers;
    }


    public PolicyDetailsDto findAtomPolicyByIdNumber(String idNumber) {

        log.info("### ProductService.findAtomPolicyByIdNumber ###");
        ResponseEntity<String> res = null;
        PolicyDetailsDto prodinfo = null;
        HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpBasicAuthHeaders());
        log.info("### header info : " + httpEntity + " ###");
        String url = atomPolicyInfoUrl.concat(idNumber);
        RestTemplate restTemplate = new RestTemplate();
        log.info("### rest call : " + url + " ###");

        try {
            res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                log.info("############# atom response: " + res.getBody().toString());

                prodinfo = new ObjectMapper().readValue(res.getBody().toString(), PolicyDetailsDto.class);


            }
        }
        catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            log.error("core error STATUS CODE: " + e.getRawStatusCode());
            log.error("core error MESSAGE: " + e.getMessage());
        } catch (JsonMappingException e) {
            log.error("Json Mapping Error: " + e.getMessage());
        } catch (JsonProcessingException e) {
            log.error("Json Processing Error: " + e.getMessage());
        }

        return prodinfo;
    }

    public ResponseEntity<?> findAtomPolicySchedule(String policyDocUrl) throws Exception{
        ResponseEntity<?>  policydoc = null;
        try {
            HttpHeaders headers = this.createHttpBasicAuthHeaders();

            String url = policyScheduleUrl;

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("documentURL", policyDocUrl);
            log.info("JSON Body : " + jsonRequest.toString());

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            log.info("URL " + url);
            ResponseEntity<?> entityResponse = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            if (entityResponse != null) {
                log.info("ATOM POLICY : " + entityResponse.getBody());
                policydoc = (ResponseEntity<?>) entityResponse.getBody();
            }
        }catch(Exception ex){
            log.info("CANNOT GET FIND POLICY DOC"  + ex.getMessage()) ;
            // ex.printStackTrace();
        }

        return policydoc;
    }

//    public List<String> findAtomPolicySchedule(String policyDocUrl) {
//
//        log.info("### ProductService.findAtomPolicyByIdNumber ###");
//        ResponseEntity<String> res = null;
//        PolicyDetailsDto prodinfo = null;
//        HttpEntity<String> httpEntity = new HttpEntity<>(this.createHttpBasicAuthHeaders());
//        log.info("### header info : " + httpEntity + " ###");
//        String url = policyScheduleUrl;
//        RestTemplate restTemplate = new RestTemplate();
//        log.info("### rest call : " + url + " ###");
//
//        try {
//            res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
//            if (res.getStatusCode().equals(HttpStatus.OK)) {
//                log.info("############# atom response: " + res.getBody().toString());
//
//                prodinfo = new ObjectMapper().readValue(res.getBody().toString(), PolicyDetailsDto.class);
//
//
//            }
//        }
//        catch (HttpClientErrorException e) {
//            log.error(e.getResponseBodyAsString());
//            log.error("core error STATUS CODE: " + e.getRawStatusCode());
//            log.error("core error MESSAGE: " + e.getMessage());
//        } catch (JsonMappingException e) {
//            log.error("Json Mapping Error: " + e.getMessage());
//        } catch (JsonProcessingException e) {
//            log.error("Json Processing Error: " + e.getMessage());
//        }
//
//        return prodinfo;
//    }


}
